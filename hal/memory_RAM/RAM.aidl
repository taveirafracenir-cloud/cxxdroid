// hal/memory_RAM/RAM.aidl
// CXXDROID HAL Interface Definition for RAM Controller

package hal.memory_RAM;

/**
 * Interface for the CXXDROID RAM HAL Service.
 * Provides access to read/write/verify system memory at low level.
 */
interface IRamService {
    /**
     * Initialize the RAM controller.
     * @param sizeInMB total RAM in megabytes.
     * @return true if initialized successfully.
     */
    boolean init(int sizeInMB);

    /**
     * Write a byte to memory.
     * @param address memory address.
     * @param data byte value.
     */
    void write(int address, int data);

    /**
     * Read a byte from memory.
     * @param address memory address.
     * @return the byte value.
     */
    int read(int address);

    /**
     * Perform a quick memory test pattern check.
     * @return true if passed.
     */
    boolean testPattern();

    /**
     * Clear all memory.
     */
    void clear();

    /**
     * Get RAM usage as a string (formatted).
     */
    String getUsage();

    /**
     * Shutdown the RAM controller safely.
     */
    void shutdown();
}
