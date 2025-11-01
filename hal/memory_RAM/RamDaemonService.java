package hal.memory_RAM;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * CXXDROID - RAM Daemon Service
 * Exposes the low-level RAM HAL service via Binder (AIDL)
 */
public class RamDaemonService extends Service {
    private static final String TAG = "CXXDROID.RamDaemon";
    private RamService ramService;

    @Override
    public void onCreate() {
        super.onCreate();
        ramService = new RamService();
        Log.i(TAG, "RamDaemonService started and RAM HAL initialized.");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Client bound to RamDaemonService.");
        return ramService;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            ramService.shutdown();
        } catch (Exception e) {
            Log.e(TAG, "Error shutting down RAM HAL", e);
        }
        Log.i(TAG, "RamDaemonService destroyed.");
    }
}
