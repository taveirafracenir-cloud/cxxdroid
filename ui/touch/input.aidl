// CXXDROID Touch Input AIDL
// -------------------------
// Defines the interface for communicating with the touch input service.
// Original code for CXXDROID.

package ui.touch;

interface ITouchInput {
    /**
     * Add a touch event to the system
     * @param x X coordinate
     * @param y Y coordinate
     * @param type Type of touch event (TAP, SWIPE_UP, etc.)
     */
    void addTouchEvent(int x, int y, int type);

    /**
     * Process all pending touch events
     */
    void processEvents();

    /**
     * Clear all pending events
     */
    void clearEvents();
}
