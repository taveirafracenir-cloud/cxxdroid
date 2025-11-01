package hal.memory_RAM;

import android.os.RemoteException;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * RamService - implementation of the IRamService AIDL interface.
 *
 * This class provides a thread-safe RAM controller service:
 *  - init(sizeInMB)
 *  - write(address, data)
 *  - read(address)
 *  - testPattern()
 *  - clear()
 *  - getUsage()
 *  - shutdown()
 *
 * To expose this service on Android, bind an instance of RamService
 * in your Service onBind() or register it with a ServiceManager variant.
 */
public class RamService extends IRamService.Stub {
    private static final String TAG = "CXXDROID.RAMService";

    // Backing memory buffer
    private byte[] memory = null;
    private int sizeBytes = 0;

    // State
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final Object lock = new Object();

    public RamService() {
        Log.i(TAG, "RamService constructed.");
    }

    @Override
    public boolean init(int sizeInMB) throws RemoteException {
        if (sizeInMB <= 0) {
            Log.e(TAG, "init: invalid sizeInMB=" + sizeInMB);
            return false;
        }

        synchronized (lock) {
            try {
                int newSize = sizeInMB * 1024 * 1024;
                // avoid reallocation if the same size and already initialized
                if (initialized.get() && memory != null && newSize == sizeBytes) {
                    Log.i(TAG, "init: already initialized with same size: " + sizeInMB + " MB");
                    return true;
                }

                memory = new byte[newSize];
                sizeBytes = newSize;
                Arrays.fill(memory, (byte)0);
                initialized.set(true);

                Log.i(TAG, "init: RAM initialized -> " + sizeInMB + " MB (" + sizeBytes + " bytes)");
                return true;
            } catch (OutOfMemoryError oom) {
                Log.e(TAG, "init: OutOfMemoryError while allocating " + sizeInMB + " MB", oom);
                memory = null;
                sizeBytes = 0;
                initialized.set(false);
                return false;
            }
        }
    }

    @Override
    public void write(int address, int data) throws RemoteException {
        if (!checkInitialized()) return;

        synchronized (lock) {
            if (!isValidAddress(address)) {
                Log.e(TAG, "write: invalid address " + address);
                return;
            }
            memory[address] = (byte) (data & 0xFF);
        }
    }

    @Override
    public int read(int address) throws RemoteException {
        if (!checkInitialized()) return 0;

        synchronized (lock) {
            if (!isValidAddress(address)) {
                Log.e(TAG, "read: invalid address " + address);
                return 0;
            }
            return memory[address] & 0xFF;
        }
    }

    @Override
    public boolean testPattern() throws RemoteException {
        if (!checkInitialized()) return false;

        final int testSize = Math.min(1024, sizeBytes); // test first 1 KB or less
        final byte pattern = (byte) 0xAA;

        synchronized (lock) {
            // write pattern
            for (int i = 0; i < testSize; i++) memory[i] = pattern;

            // verify
            for (int i = 0; i < testSize; i++) {
                if (memory[i] != pattern) {
                    Log.e(TAG, "testPattern: mismatch at offset " + i + " expected=0xAA found=0x" + Integer.toHexString(memory[i] & 0xFF));
                    return false;
                }
            }

            // restore to zero (clean)
            for (int i = 0; i < testSize; i++) memory[i] = 0x00;
            Log.i(TAG, "testPattern: passed for first " + testSize + " bytes.");
            return true;
        }
    }

    @Override
    public void clear() throws RemoteException {
        if (!checkInitialized()) return;

        synchronized (lock) {
            Arrays.fill(memory, (byte)0x00);
            Log.i(TAG, "clear: memory cleared.");
        }
    }

    @Override
    public String getUsage() throws RemoteException {
        if (!checkInitialized()) return "RAM not initialized";

        synchronized (lock) {
            long used = 0;
            // Count non-zero bytes (simple heuristic)
            for (int i = 0; i < sizeBytes; i++) {
                if (memory[i] != 0) used++;
            }
            double percent = (sizeBytes == 0) ? 0.0 : (used * 100.0 / sizeBytes);
            DecimalFormat df = new DecimalFormat("#0.00");
            String usage = String.format("Usage: %d / %d bytes (%s%%)", used, sizeBytes, df.format(percent));
            Log.i(TAG, "getUsage: " + usage);
            return usage;
        }
    }

    @Override
    public void shutdown() throws RemoteException {
        synchronized (lock) {
            if (memory != null) {
                Arrays.fill(memory, (byte)0x00);
                memory = null;
            }
            sizeBytes = 0;
            initialized.set(false);
            Log.i(TAG, "shutdown: RAM controller shutdown.");
        }
    }

    // -------------------------
    // Helper methods
    // -------------------------
    private boolean checkInitialized() {
        if (!initialized.get()) {
            Log.e(TAG, "Operation attempted before RAM initialized.");
            return false;
        }
        return true;
    }

    private boolean isValidAddress(int address) {
        return address >= 0 && address < sizeBytes;
    }
}
