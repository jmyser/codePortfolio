
package ex3;
/* HTree.java
 * JavaFX application to display an h-tree fractal,
 * given an integer representing the order of the fractal, which is entered by the user.
 * Listing 18.9 in Liang, Intro to Java Programming
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class HTree extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    HPane hPane = new HPane(); 
    TextField tfOrder = new TextField(); 
    tfOrder.setOnAction(
      e -> hPane.setOrder(Integer.parseInt(tfOrder.getText())));
    tfOrder.setPrefColumnCount(4);
    tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

    // Pane to hold label, text field, and a button
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(hPane);
    borderPane.setBottom(hBox);
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 200, 210);
    primaryStage.setTitle("H Tree"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    scene.widthProperty().addListener(ov -> hPane.paint());
    scene.heightProperty().addListener(ov -> hPane.paint());
  }

  /** Pane for displaying triangles */
  static class HPane extends Pane {
    private int order = 0;

    /** Set a new order */
    public void setOrder(int order) {
      this.order = order;
      paint();
    }

    HPane() {
    }

    protected void paint() {
      
      // set center point and base size of HTree
      double xCenter = (getWidth() / 2);
      double yCenter = (getHeight() / 2);
      double length;
      if (getWidth() > getHeight())
          length = (getWidth() / 2);
      else
          length = (getHeight() / 2);
      
      this.getChildren().clear(); // Clear the pane before redisplay
      displayHtree(order, xCenter, yCenter, length);
      
    }

private void displayHtree(int order, double xCenter, double yCenter, double length) {
    // compute the coordinates of the 4 tips of the H
    double xA = xCenter - (length / 2);
    double xB = xCenter + (length / 2);
    double yA = yCenter - (length / 2);
    double yB = yCenter + (length / 2);

    // draw the 3 line segments of the H
    Line leftVerLine = new Line(xA, yA, xA, yB);
    Line rightVerLine = new Line(xB, yA, xB, yB);
    Line horizonLine = new Line(xA,  yCenter, xB,  yCenter);

    leftVerLine.setStroke(Color.BLACK);
    rightVerLine.setStroke(Color.BLACK);
    horizonLine.setStroke(Color.BLACK);
    this.getChildren().addAll(leftVerLine, rightVerLine, horizonLine);

    // recursive method to draw the fractal    
    if (order == 0) {} 
    else {
        // Recursively display H
        displayHtree(order - 1, xA, yA, length/2);
        displayHtree(order - 1, xA, yB, length/2);
        displayHtree(order - 1, xB, yA, length/2);
        displayHtree(order - 1, xB, yB, length/2);
        }
    }
}
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
