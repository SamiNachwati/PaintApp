import javafx.scene.paint.Color;
/**
 * parent class used to draw GeometricObjects
 * @author Sami Nachwati
 */
public abstract class GeometricObject implements Drawable
{
    /** x and y coordinate values for GeometricShapes **/
    private double x, y;

    /** color property for the GeometricShape object **/
    private Color fillColor;

    /**
     * constructor used to make a shape, making checks to ensure that users enters proper range for x and y coordinates
     * @param x position on canvas horizontally
     * @param y position on canvas vertically
     * @param color property used for the color fill of the object
     */
    public GeometricObject(double x, double y, Color color)
    {
        if(x < 0 || x > 990){
            throw new IllegalArgumentException("X value must be in range(0, 990), inclusive");
        }
        else {
            this.x = x;
        }
        if(y > 580 || y < 0) {
            throw new IllegalArgumentException("Y value must be in range(0, 580), inclusive");
        }
        else{
            this.y = y;
        }

        this.fillColor = color;
    }

    /**
     * getter for getting the x variable
     * @return x
     */
    public double getX()
    {
        return this.x;
    }

    /**
     * getter for getting the y variable
     * @return y
     */
    public double getY()
    {
        return this.y;
    }

    /**
     * getter for getting the color fill variable
     * @return fillColor
     */
    public Color getFillColor()
    {
        return this.fillColor;
    }
}
