package ui.view.animations;

/**
 * CXXDROID UI Animation Base Class
 * --------------------------------
 * Base class for all UI animations in CXXDROID.
 * Provides common properties like duration and progress.
 */
public abstract class Animation {
    protected int duration; // duration in milliseconds
    protected float progress; // 0.0 (start) to 1.0 (end)

    public Animation(int duration) {
        this.duration = duration;
        this.progress = 0.0f;
    }

    /**
     * Update animation progress
     * @param deltaTime time elapsed in milliseconds
     */
    public void update(int deltaTime) {
        progress += (float)deltaTime / duration;
        if (progress > 1.0f) progress = 1.0f;
        render();
    }

    /**
     * Render the current state of the animation
     * To be implemented by subclasses
     */
    protected abstract void render();

    /**
     * Check if animation has finished
     */
    public boolean isFinished() {
        return progress >= 1.0f;
    }

    /**
     * Reset animation to initial state
     */
    public void reset() {
        progress = 0.0f;
    }
}
