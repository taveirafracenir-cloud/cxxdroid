package hal.memory_RAM;

/**
 * CXXDROID - RAMTest
 * Test program for RAM HAL (via JNI)
 */
public class RAMTest {
    // Ponte JNI (declarações nativas)
    static class NativeRamBridge {
        static {
            System.loadLibrary("ramjni"); // carrega libramjni.so
        }

        public native void init();
        public native boolean testPattern();
        public native void clear();
        public native void log(String msg);
    }

    public static void main(String[] args) {
        System.out.println("=== CXXDROID RAM HAL TEST ===");

        NativeRamBridge ram = new NativeRamBridge();
        ram.init();
        ram.log("RAM JNI Bridge initialized.");

        boolean ok = ram.testPattern();
        if (ok) {
            System.out.println("[OK] RAM pattern test passed!");
        } else {
            System.out.println("[FAIL] RAM pattern test failed!");
        }

        ram.clear();
        ram.log("RAM memory cleared.");

        System.out.println("=== Test Completed ===");
    }
}
