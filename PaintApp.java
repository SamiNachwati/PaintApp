import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * this is the view class which uses GUI components as well as different model components to run the drawing elements
 * @author Sami Nachwati
 */
public class PaintApp extends Application {

    // TODO: Instance Variables for View Components and Model

    /** GeometricObject array holding different shapes of circle and or square **/
    ArrayList<GeometricObject> shapes = new ArrayList<>();


    /** boolean variable to indicate if the circle button is clicked **/
    boolean CircleClicked = false;

    /** boolean variable to indicate if the square button is clicked **/
    boolean SquareClicked = false;

    /** variable used to draw GUI components **/
    GraphicsContext gc;

    /** coordinate text-boxes for x and y **/
    TextField x, y;

    /** the button for creating a Circle object **/
    Button Circle;

    /** RGB text-boxes for red, green, and blue values for color **/
    TextField firstColorField, secondColorField, thirdColorField;

    /** text-box for the size of the shape **/
    TextField size;

    /** label for the error message, indicating if the user makes an error or not **/
    Label errorMessage;
    // TODO: Private Event Handlers and Helper Methods

    /**
     * method for clicking the Circle button
     * @param c ActionEvent for handling the button of Circle
     */
    private void circleClicked(ActionEvent c){
        this.CircleClicked = true;
        this.SquareClicked = false;
    }

    /**
     * method for clicking the Square button
     * @param s ActionEvent for handling the button of Square
     */

    private void squareClicked(ActionEvent s){
        this.SquareClicked = true;
        this.CircleClicked = false;
    }

    /**
     * method that draws squares and circle via the Circle and Square buttons.
     * This method first tries and checks to ensure that the user properly enters
     * the necessary values (containing no "letters", out of range values, etc...)
     * If the user does enter an illegal argument, then the user is prompted with an error message
     * that lets them know which error they have.
     * If they fix that error, the error message goes away.
     * @param m Action Event for checking the on click event for the draw button
     */

    private void DrawHandler(ActionEvent m){
        if(CircleClicked) {
            try {
                int radius = Integer.parseInt(size.getText());
                int xV = Integer.parseInt(x.getText());
                int yV = Integer.parseInt(y.getText());
                int red = Integer.parseInt(firstColorField.getText());
                int green = Integer.parseInt(secondColorField.getText());
                int blue =  Integer.parseInt(thirdColorField.getText());
                Circle c = new Circle(xV, yV, Color.rgb(red, green, blue), radius);
                shapes.add(c);
                c.draw(gc);
                errorMessage.setText("No Errors"); // Set error message to empty string
                errorMessage.setStyle("-fx-background-color: lightgrey; -fx-text-fill: green; -fx-font-family: monospace;");
            }
            catch (IllegalArgumentException i){
                errorMessage.setText("Error " + i.getMessage());
                errorMessage.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            }
        }
        else if(SquareClicked){
            try {
                int sizeS = Integer.parseInt(size.getText());
                int xV = Integer.parseInt(x.getText());
                int yV = Integer.parseInt(y.getText());
                int red = Integer.parseInt(firstColorField.getText());
                int green = Integer.parseInt(secondColorField.getText());
                int blue =  Integer.parseInt(thirdColorField.getText());
                GeometricObject s = new Square(xV, yV, Color.rgb(red, green, blue), sizeS);
                shapes.add(s);
                s.draw(gc);
                errorMessage.setText("No Errors"); // Set error message to empty string
                errorMessage.setStyle("-fx-background-color: lightgrey; -fx-text-fill: green; -fx-font-family: monospace;");
            }
            catch (IllegalArgumentException i){
                errorMessage.setText("Error " + i.getMessage());
                errorMessage.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-family: monospace;");
            }
        }
    }


    /**
     * this method acts in a similar way to the other method of when the user clicks the draw button, however,
     * it specifically checks when the user drags and or clicks on the canvas to produce a shape.
     * It also checks other conditions, such like if the user drags out of the canvas, if the user puts a bad
     * color value or size, etc...
     * Once the user clears the error and fixes it, then, the error message goes away.
     * @param m the Action Event checking for when the user drags/clicks the mouse on the canvas to draw shapes
     */
    private void mouseMovement(MouseEvent m) {
        if (CircleClicked){
            try {
                double radius = Double.parseDouble(size.getText());
                int xV = (int) m.getX();
                int yV = (int) m.getY();
                int red = Integer.parseInt(firstColorField.getText());
                int green = Integer.parseInt(secondColorField.getText());
                int blue =  Integer.parseInt(thirdColorField.getText());
                GeometricObject c = new Circle(xV - radius / 2, yV - radius / 2, Color.rgb(red, green, blue), radius);
                shapes.add(c);
                c.draw(gc);
                errorMessage.setText("No Errors"); // Set error message to empty string
                errorMessage.setStyle("-fx-background-color: lightgrey; -fx-text-fill: green; -fx-font-family: monospace;");
            }
            catch (IllegalArgumentException i){
                errorMessage.setText("Error " + i.getMessage());
                errorMessage.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-family: monospace;");
            }
        }
        else if(SquareClicked){
            try {
                double sizeS = Double.parseDouble(size.getText());
                int xV = (int) m.getX();
                int yV = (int) m.getY();
                int red = Integer.parseInt(firstColorField.getText());
                int green = Integer.parseInt(secondColorField.getText());
                int blue = Integer.parseInt(thirdColorField.getText());
                GeometricObject s = new Square(xV - sizeS / 2, yV - sizeS / 2, Color.rgb(red, green, blue), sizeS);
                shapes.add(s);
                s.draw(gc);
                errorMessage.setText("No Errors"); // Set error message to empty string
                errorMessage.setStyle("-fx-background-color: lightgrey; -fx-text-fill: green; -fx-font-family: monospace;");
            }
            catch (IllegalArgumentException i){
                errorMessage.setText("Error " + i.getMessage());
                errorMessage.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-family: monospace;");
            }
        }
    }

    /**
     * method that removes the object once the user clicks the Undraw button.
     * This method first goes on to ensure that the shape array is not empty, then
     * as long as it is not empty, when the user clicks the Undraw button, 1 shape for every Undraw click
     * removes that shape. To ensure this works, it also redraws the canvas and draws back the shapes
     * @param m the Action Event to delete GeometricObjects
     */
    private void removeObjects(ActionEvent m){
        if(!shapes.isEmpty()){
            shapes.remove(shapes.size()-1);
        }
        gc.setFill(Color.WHEAT);
        gc.fillRect(0, 0, 1000, 590);
        for(GeometricObject o: shapes){
            o.draw(gc);
        }
    }


    /**
     * method used to clear all shapes present on the canvas
     * @param e Action Event that removes all the shapes and redraws the canvas
     */
    private void clearAll(ActionEvent e){
        shapes.clear();
        gc.setFill(Color.WHEAT);
        gc.fillRect(0, 0, 1000, 590);
    }


    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     */
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1000, 700); // set the size here
        stage.setTitle("Assignment 8"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here
        // 1. Create the model
        // 2. Create the GUI components
        // canvas for GUI components
        Canvas c = new Canvas(1000, 590);
        // assign gc as a GraphicsContext
        gc = c.getGraphicsContext2D();
        // set the color to Wheat for the gc component
        gc.setFill(Color.WHEAT);
        // set the coordinates for a rectangle
        gc.fillRect(0, 0, 1000, 590);
        // label for the instructions of how to draw a Circle or Square object
        Label instructions = new Label("Press Circle or Square to Draw shapes");
        // Button for when the user wants to draw a Circle object
        Circle = new Button("Circle");
        // Button for when the user wants to draw a Square object
        Button Square = new Button("Square");
        // label for the x and y coordinates if the user wants to manually set a coordinate position to draw shapes
        Label LocationText = new Label("Location:");
        // x coordinate with value set
        x = new TextField("400");
        // y coordinate with value set
        y = new TextField("200");
        // label for the size
        Label sizeText = new Label("Size:");
        // size value displayed to the user
        size = new TextField("10");
        // label for the color
        Label ColorText = new Label("Color:");
        // first RGB parameter
        firstColorField = new TextField("255");
        // second RGB parameter
        secondColorField = new TextField("128");
        // third RGB parameter
        thirdColorField = new TextField("0");
        // button used to draw shapes at specific coordinates
        Button Draw = new Button("Draw");
        // button used to un-draw shapes one by one
        Button UnDraw = new Button("UnDraw");
        // button used to completely erase all shapes on canvas
        Button Clear = new Button("Clear All");
        // message to indicate if any errors have been made
        errorMessage = new Label("No Errors");
        // 3. Add components to the root
        root.getChildren().addAll(c, instructions, Circle, Square, LocationText, x, y, sizeText,
                size, ColorText, firstColorField, secondColorField, thirdColorField, Draw,
                UnDraw, errorMessage, Clear);
        // 4. Configure the components (colors, fonts, size, location)
        // locations for the instructions message
        instructions.relocate(0, 0);
        // style property for the instructions message
        instructions.setStyle("-fx-background-color: lightGrey; -fx-font-size: 20px;");
        // the width of the message
        instructions.setPrefWidth(1000);
        // position of the message
        instructions.setAlignment(Pos.CENTER);
        // location of the canvas
        c.relocate(0, 30);
        // location of the Circle button
        Circle.relocate(0, 625);
        // styling property of the Circle button
        Circle.setStyle("-fx-padding: 2 15 2 15;");
        // location of the Square button
        Square.relocate(70, 625);
        // styling property of the Square button
        Square.setStyle("-fx-padding: 2 15 2 15");
        // location of the location text for x and y
        LocationText.relocate(150, 625);
        // x text-box location
        x.relocate(200, 625);
        // y text-box width
        x.setPrefWidth(50);
        // y text-box location
        y.relocate(260, 625);
        // y text-box width
        y.setPrefWidth(50);
        // size text location
        sizeText.relocate(320, 625);
        // size text location
        size.relocate(360, 625);
        // size text width
        size.setPrefWidth(50);
        // color label location
        ColorText.relocate(450, 625);
        // first color field location
        firstColorField.relocate(490, 625);
        // first color field width
        firstColorField.setPrefWidth(50);
        // second color field location
        secondColorField.relocate(550, 625);
        // second color field width
        secondColorField.setPrefWidth(50);
        // third color field location
        thirdColorField.relocate(610, 625);
        // third color field width
        thirdColorField.setPrefWidth(50);
        // Draw button styling
        Draw.setStyle("-fx-padding: 3 15 3 15;");
        // Draw button location
        Draw.relocate(675, 625);
        // UnDraw Button styling
        UnDraw.setStyle("-fx-padding: 3 15 3 15;");
        // UnDraw Button location
        UnDraw.relocate(750, 625);
        // Clear Button styling
        Clear.setStyle("-fx-padding: 3 15 3 15;");
        // Clear Button location
        Clear.relocate(840, 625);
        // error Message location
        errorMessage.relocate(0, 670);
        // error Message styling
        errorMessage.setStyle("-fx-background-color: lightgrey; -fx-text-fill: green; -fx-font-family: monospace;");
        // error Message error
        errorMessage.setPrefWidth(1000);
        // error Message position
        errorMessage.setAlignment(Pos.CENTER);
        // 5. Add Event Handlers and do final setup
        // action events for clicking Square
        Square.setOnAction(this::squareClicked);
        // action events for clicking Circle
        Circle.setOnAction(this::circleClicked);
        // action events for drawing
        Draw.setOnAction(this::DrawHandler);
        // action events for dragging on the canvas
        c.setOnMouseDragged(this::mouseMovement);
        // action events for clicking on the canvas
        c.setOnMouseClicked(this::mouseMovement);
        // action events for un-drawing shape elements
        UnDraw.setOnAction(this::removeObjects);
        // action events for clearing all shapes
        Clear.setOnAction(this::clearAll);
        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
