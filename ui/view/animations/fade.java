package ui.view.animations;

/**
 * Fade Animation
 * --------------
 * Fades in or out an element by changing its opacity.
 */
public class Fade extends Animation {
    private boolean fadeIn;

    public Fade(int duration, boolean fadeIn) {
        super(duration);
        this.fadeIn = fadeIn;
    }

    @Override
    protected void render() {
        float opacity = fadeIn ? progress : 1.0f - progress;
        System.out.println("[Fade] Opacity: " + String.format("%.2f", opacity));
        // In a real UI, this would adjust the element's alpha/transparency
    }
}
