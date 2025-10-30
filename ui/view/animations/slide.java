package ui.view.animations;

/**
 * Slide Animation
 * ---------------
 * Slides an element from start position to end position.
 */
public class Slide extends Animation {
    private int startX, startY;
    private int endX, endY;

    public Slide(int duration, int startX, int startY, int endX, int endY) {
        super(duration);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    protected void render() {
        int currentX = startX + Math.round((endX - startX) * progress);
        int currentY = startY + Math.round((endY - startY) * progress);
        System.out.println("[Slide] Position: (" + currentX + "," + currentY + ")");
        // In a real UI, this would move the element on screen
    }
}
