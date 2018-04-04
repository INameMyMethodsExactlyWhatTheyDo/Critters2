package assignment5;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class ResizableCanvas extends Canvas {
	public List<Circle> drawQueue = new java.util.ArrayList<Circle>();
    public ResizableCanvas() {
      // Redraw canvas when size changes.
    	//super(i,j);
      widthProperty().addListener(evt -> draw());
      heightProperty().addListener(evt -> draw());
    }
 
    private void draw() {

      double width = getWidth();
      double height = getHeight();
 
      GraphicsContext gc = getGraphicsContext2D();
      gc.clearRect(0, 0, width, height);
      //gc.fillRect(40, 40, width/2, height/2);
      gc.setStroke(Color.RED);
      gc.strokeLine(0, 0, width, height);
      gc.strokeLine(0, height, width, 0);
      gc.setFill(Color.GREEN);
      gc.setFill(Color.GREEN);

      gc.fillOval(0, 0, 30, 30);
    }
 
    @Override
    public boolean isResizable() {
      return true;
    }
 
    @Override
    public double prefWidth(double height) {
      return getWidth();
    }
 
    @Override
    public double prefHeight(double width) {
      return getHeight();
    }
  }