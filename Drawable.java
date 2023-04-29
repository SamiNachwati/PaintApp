import javafx.scene.canvas.GraphicsContext;

/**
 * the drawable interface for classes implementing this interface's methods
 * @author Sami Nachwati
 */
public interface Drawable
{
    /**
     * draw method for drawing elements on the screen
     * @param gc the GraphicsContext element
     */
    public void draw(GraphicsContext gc);
}
