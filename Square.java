import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * this class is a subclass of the GeometricObject parent class.
 * It is used to create Square objects
 * @author Sami Nachwati
 */
public class Square extends GeometricObject {

    /**
     * variable for the size of the square
     */
    private double size;

    /**
     * constructor used to create a Square Object.
     * This constructor also makes checks for the size of the square.
     * @param x the x coordinate of the Square object
     * @param y the y coordinate of the Square object
     * @param color the color parameter of the Square object
     * @param size the size of the Square object
     */
    public Square(double x, double y, Color color, double size)
        {
            super(x, y, color);
            if(size <= 0){
                throw new IllegalArgumentException("Size must be greater than 0!");
            }
            else{
                this.size = size;
            }
        }

    /**
     * the draw method for drawing a Square object
     * @param gc the GraphicsContext element
     */
    public void draw(GraphicsContext gc)
        {
            gc.setFill(getFillColor());
            gc.fillRect(getX(), getY(), size, size);
        }
}
