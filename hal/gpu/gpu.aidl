// hal/gpu/gpu.aidl
// AIDL interface for GPU service in CXXDROID (original)
// Package name follows Java-style hal.gpu

package hal.gpu;

// A simple GPU service interface exposing essential operations.
// Implement this interface in the GPU daemon/service and provide IPC bindings.

interface IGpuService {
    /**
     * Initialize the GPU with width and height. Returns true if successful.
     */
    boolean init(int width, int height);

    /**
     * Clear the framebuffer with a 32-bit ARGB color.
     */
    void clear(int color);

    /**
     * Set one pixel (x,y) to color (32-bit ARGB)
     */
    void setPixel(int x, int y, int color);

    /**
     * Draw a filled rectangle at (x,y) with width w and height h.
     */
    void drawRect(int x, int y, int w, int h, int color);

    /**
     * Draw a short text string at (x,y) using the system font rendering.
     * The service chooses font, clipping and rendering.
     */
    void drawText(String text, int x, int y, int color);

    /**
     * Present the current framebuffer to the display (swap/push).
     */
    void present();

    /**
     * Get current resolution as an array: [width, height]
     */
    int[] getResolution();
}
