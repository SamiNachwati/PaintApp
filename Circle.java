import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * this class is a subclass of the GeometricObject parent class.
 * It is used to create Circle objects
 * @author Sami Nachwati
 */
public class Circle extends GeometricObject
{

    /** radius of the Circle object **/
    private double radius;


    /**
     * constructor used to create a Circle object.
     * This constructor also makes checks for the radius
     * @param x coordinate of the Circle object
     * @param y coordinate of the Circle object
     * @param color fill property of the Circle object
     * @param radius size value for the Circle object
     */
    public Circle(double x, double y, Color color, double radius)
    {
        super(x, y, color);
        if(radius <= 0){
            throw new IllegalArgumentException("Radius must be greater than 0!");
        }
        else {
            this.radius = radius;
        }
    }

    /**
     * draw method for drawing Circle objects
     * @param gc the GraphicsContext element
     */
    public void draw(GraphicsContext gc)
    {
        gc.setFill(getFillColor());
        gc.fillOval(getX(), getY(), radius, radius);
    }
}
