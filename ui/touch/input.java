package ui.touch;

import java.util.ArrayList;
import java.util.List;

/**
 * CXXDROID Touch Input Module
 * ---------------------------
 * Handles basic touch input events for the system UI.
 * Original code for CXXDROID.
 */
public class Input {

    public static class TouchEvent {
        public enum Type { TAP, SWIPE_UP, SWIPE_DOWN, SWIPE_LEFT, SWIPE_RIGHT, LONG_PRESS }

        public int x, y;
        public Type type;

        public TouchEvent(int x, int y, Type type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    private List<TouchEvent> eventQueue;

    public Input() {
        eventQueue = new ArrayList<>();
    }

    /**
     * Add a touch event to the queue
     */
    public void addEvent(TouchEvent event) {
        eventQueue.add(event);
        System.out.println("[Input] Event added: " + event.type + " at (" + event.x + "," + event.y + ")");
    }

    /**
     * Process all queued touch events
     */
    public void processEvents() {
        for (TouchEvent e : eventQueue) {
            System.out.println("[Input] Processing event: " + e.type + " at (" + e.x + "," + e.y + ")");
            // Here you can add code to interact with UI components
        }
        eventQueue.clear();
    }

    /**
     * Clear all pending events
     */
    public void clearEvents() {
        eventQueue.clear();
        System.out.println("[Input] Event queue cleared.");
    }

    // Example test method
    public static void main(String[] args) {
        Input input = new Input();
        input.addEvent(new TouchEvent(100, 200, TouchEvent.Type.TAP));
        input.addEvent(new TouchEvent(50, 50, TouchEvent.Type.SWIPE_LEFT));
        input.processEvents();
    }
}
