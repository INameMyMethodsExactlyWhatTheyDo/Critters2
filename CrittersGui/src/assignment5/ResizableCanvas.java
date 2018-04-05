package assignment5;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

class ResizableCanvas extends Canvas {
	//private StackPane stackp;// = new StackPane();
	public List<Integer> drawQueueX = new java.util.ArrayList<Integer>();
	public List<Integer> drawQueueY = new java.util.ArrayList<Integer>();
	public List<String> drawQueueS = new java.util.ArrayList<String>();
	GraphicsContext gc = this.getGraphicsContext2D();
	
    
    public ResizableCanvas() {
      widthProperty().addListener(evt -> drawCritters());
      heightProperty().addListener(evt -> drawCritters());
    }
 
    private void draw() {
      double width = getWidth();
      double height = getHeight();
 
      GraphicsContext gc = getGraphicsContext2D();
      gc.clearRect(0, 0, width, height);
      gc.setStroke(Color.RED);
      gc.strokeLine(0, 0, width, height);
      gc.strokeLine(0, height, width, 0);
      gc.setFill(Color.GREEN);
      gc.setFill(Color.GREEN);
      gc.fillOval(0, 0, 30, 30);
      
    }
    public void drawCritter(String name, int posX, int posY) {
    	double pixelX = getWidth()/Params.world_width;
        double pixelY = getHeight()/Params.world_height; 
    	posX *=pixelX;
	    posY *=pixelY;
	    gc.setFont(new Font(15));
	    gc.strokeText(name, posX, posY);
    }
    public void drawCritters() {
    	gc.clearRect(0, 0, getWidth(), getHeight());
    	for(int i = 0; i < this.drawQueueS.size(); i++) {
    		drawCritter(this.drawQueueS.get(i),this.drawQueueX.get(i),this.drawQueueY.get(i));
    	}
    }
    
    public void setX(List<Integer> x) {
    	this.drawQueueX = x;
    }
	public void setY(List<Integer> y) {
	    this.drawQueueY = y;
	}
	public void setS(List<String> s) {
		this.drawQueueS = s;
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