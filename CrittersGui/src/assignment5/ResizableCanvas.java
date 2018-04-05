package assignment5;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class ResizableCanvas extends Canvas {
	//private StackPane stackp;// = new StackPane();
	public List<Circle> drawQueue = new java.util.ArrayList<Circle>();
    public ResizableCanvas() {
      // Redraw canvas when size changes.
    	//super(i,j);
    	//stackp = sp;
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
    public void drawCritter(GridPane gp) {
    	//randomaly add critter
    	GraphicsContext gc = getGraphicsContext2D();
		//use to convert index (row, col) to pixel position
		double pixelX = getWidth()/Params.world_width;
	    double pixelY = getHeight()/Params.world_height;
	    
	    double posX = Critter.getRandomInt(Params.world_width);
	    double posY = Critter.getRandomInt(Params.world_height);
	    
	    posX *=pixelX;
	    posY *=pixelY;
	    gc.fillRect(posX, posY, 20, 20);
	    //
	    //for critter population time step, fillREct() for all of them

	    
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