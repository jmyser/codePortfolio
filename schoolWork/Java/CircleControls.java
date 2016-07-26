/* CircleControls.java
 * JavaFX program demonstrates using UI controls
 * to change the appearance of a circle,
 * adapted from ControlCircle.java Listing 15.3 in Liang, Intro to Java Programming
 * @version December 2015
 */
package lab4;

import static java.lang.Integer.parseInt;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleControls extends Application {
  // declare instance variables to store components used by more than one method
  private Circle circle;
  private TextField tfSizeChange;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create the circle object with initial size and color
    circle = new Circle(50);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    
    // Place size controls in an HBox
    HBox sizeHBox = new HBox();
    sizeHBox.setSpacing(10);
    sizeHBox.setAlignment(Pos.CENTER);
    Button btEnlarge = new Button("Enlarge");
    Button btShrink = new Button("Shrink");
    tfSizeChange = new TextField();
    tfSizeChange.setPrefColumnCount(3);
    sizeHBox.getChildren().add(btEnlarge);
    sizeHBox.getChildren().add(btShrink);
    sizeHBox.getChildren().add(new Label("by"));
    sizeHBox.getChildren().add(tfSizeChange);
    
    // Place color controls in an HBox
    HBox colorHBox = new HBox();
    colorHBox.setSpacing(10);
    // TO DO PART B: add 3 buttons to change color
    Button btRed = new Button("Red");
    Button btWhite = new Button("White");
    Button btBlue = new Button("Blue");
    colorHBox.getChildren().add(btRed);
    colorHBox.getChildren().add(btWhite);
    colorHBox.getChildren().add(btBlue);
    
    // Create and register the button event handlers
    // 1. using an explicit handler class:
    // btEnlarge.setOnAction(new EnlargeHandler());
    // 2. using an anonymous lambda expression:
    
    
    
    btEnlarge.setOnAction(e -> {
        // TO DO PART C: use the number of pixels
        // that the user has entered in tfSizeChange
        int sizeChange = 2;
       
        if (!tfSizeChange.getText().equals("")) {
            sizeChange = parseInt(tfSizeChange.getText());
        }
         
        circle.setRadius(circle.getRadius() + sizeChange);
    });
    
    // TO DO PART A: add event handler for btShrink button
    btShrink.setOnAction(e -> {
        int sizeChange = 2;
       
        if (!tfSizeChange.getText().equals("")) {
            sizeChange = parseInt(tfSizeChange.getText());
        }
        if (circle.getRadius() - sizeChange > 0)
            circle.setRadius(circle.getRadius() - sizeChange);
    });
    // TO DO PART B: add event handlers for color buttons
    
    btRed.setOnAction(e -> {
        circle.setFill(Color.RED);
    });
    btWhite.setOnAction(e -> {
        circle.setFill(Color.WHITE);
    });
    btBlue.setOnAction(e -> {
        circle.setFill(Color.BLUE);
    });
    
    
    // Add components to main borderPane
    BorderPane borderPane = new BorderPane();
    GridPane controlPane = new GridPane();
    controlPane.setHgap(5);
    controlPane.setVgap(5);
    controlPane.add(new Label("Size"), 0, 0);
    controlPane.add(sizeHBox, 1, 0);
    controlPane.add(new Label("Color"), 0, 1);
    controlPane.add(colorHBox, 1, 1);    
    borderPane.setCenter(circle);
    borderPane.setBottom(controlPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 300, 200);
    primaryStage.setTitle("ControlCircle"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  } // end start method
  
  // inner class for event handler
  // Note: a handler class does not need to be defined for an event handler
  // that is defined by a lambda expression
  class EnlargeHandler implements EventHandler<ActionEvent> {
    @Override // Override the handle method
    public void handle(ActionEvent e) {
        circle.setRadius(circle.getRadius() + 2);
    }
  }
}
