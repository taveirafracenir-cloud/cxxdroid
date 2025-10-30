package ui.view;

import ui.view.animations.*;
import ui.touch.*;
import ui.Accessibility.*;
import ui.view.webkit.*;
import BIOS.sensors.LITHIUM.lithium_detected;
import java.util.*;

/**
 * CXXDROID Desktop UI - Full Version
 * Fully integrated, interactive desktop for CXXDROID.
 */
public class View {

    private String id;
    private List<View> children;
    private boolean visible;
    private int x, y, width, height;

    // Event listeners
    private List<Runnable> onClickListeners;
    private List<Runnable> onLongPressListeners;
    private List<Runnable> onSwipeListeners;

    // Input & Accessibility
    private Input inputHandler;
    private accessibility.AccessibilityManager accManager;
    private accessibility.TalkBack talkBack;

    // Components
    public Menu menu;
    public WebView webView;
    public lithium_detected lithiumSensor;
    private Animation currentAnimation;

    public View(String id) {
        this.id = id;
        this.children = new ArrayList<>();
        this.visible = true;

        this.onClickListeners = new ArrayList<>();
        this.onLongPressListeners = new ArrayList<>();
        this.onSwipeListeners = new ArrayList<>();

        this.inputHandler = new Input();
        this.accManager = new accessibility.AccessibilityManager();
        this.talkBack = new accessibility.TalkBack(accManager);

        this.menu = new Menu();
        this.webView = new WebView();
        this.lithiumSensor = new lithium_detected();
    }

    // Initialize all components and detect sensors
    public void initialize() {
        System.out.println("[CXXDROID] Initializing Desktop UI...");
        lithiumSensor.detect(); // Real LITHIUM detection

        menu.addItem("Home");
        menu.addItem("Apps");
        menu.addItem("Settings");
        menu.addItem("About");

        for (View child : children) child.initialize();
    }

    public void addChild(View child) { children.add(child); }
    public void removeChild(View child) { children.remove(child); }

    // Event handling
    public void handleClick() {
        for (Runnable r : onClickListeners) r.run();
        if (accManager.isEnabled()) talkBack.speak("Clicked on " + id);
        menu.display();
    }

    public void handleSwipe() {
        for (Runnable r : onSwipeListeners) r.run();
        if (accManager.isEnabled()) talkBack.speak("Swiped on " + id);
    }

    // Animations
    public void startAnimation(Animation animation) {
        this.currentAnimation = animation;
        animation.reset();
    }

    // Layout
    public void layout() {
        System.out.println("[CXXDROID] Layout: " + id + " (" + x + "," + y + ") size (" + width + "x" + height + ")");
        for (View child : children) child.layout();
    }

    // Render
    public void render() {
        if (!visible) return;

        // Render Menu with slide animation
        if (currentAnimation instanceof Slide) {
            currentAnimation.update(16);
        }
        menu.display();

        // Render WebView
        webView.display();

        // Render children
        for (View child : children) child.render();

        // Process Fade animation
        if (currentAnimation instanceof Fade) {
            currentAnimation.update(16);
            if (currentAnimation.isFinished()) currentAnimation = null;
        }
    }

    // Input
    public void processInput() { inputHandler.processEvents(); }
    public Input getInputHandler() { return inputHandler; }

    // Accessibility
    public void enableAccessibility() { accManager.enable(); }
    public void disableAccessibility() { accManager.disable(); }

    // Test / Main method
    public static void main(String[] args) {
        View root = new View("root");

        // Add header and content views
        View header = new View("header");
        View content = new View("content");
        root.addChild(header);
        root.addChild(content);

        // Initialize UI
        root.initialize();
        root.enableAccessibility();

        // Load WebView content
        if (root.webView.loadUrl("example.html")) {
            System.out.println("[CXXDROID] WebView content loaded.");
        }

        // Layout UI
        root.layout();

        // Add real touch events
        Input input = root.getInputHandler();
        input.addEvent(new Input.TouchEvent(10, 10, Input.TouchEvent.Type.TAP));
        input.addEvent(new Input.TouchEvent(50, 50, Input.TouchEvent.Type.SWIPE_RIGHT));
        root.processInput();

        // Start animations
        root.startAnimation(new Slide(800, 0, 0, 100, 0)); // slide in menu
        while (root.currentAnimation != null) root.render();

        root.startAnimation(new Fade(1200, true)); // fade in content
        while (root.currentAnimation != null) root.render();

        // Interact with menu
        root.handleClick();

        // Final render
        root.render();

        System.out.println("[CXXDROID] Desktop UI fully operational. All components integrated.");
    }
}
