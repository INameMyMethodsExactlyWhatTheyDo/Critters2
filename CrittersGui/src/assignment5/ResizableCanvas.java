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
	public List<Critter.CritterShape> drawQueueShape = new java.util.ArrayList<Critter.CritterShape>();
	public List<Critter> drawQueueC = new java.util.ArrayList<Critter>();

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
    	//gc.set(Color.DARKGREEN);
    	double pixelX = getWidth()/Params.world_width;
        double pixelY = getHeight()/Params.world_height; 
    	posX *=pixelX;
	    posY *=pixelY;
	    gc.setFont(new Font(15));
	    gc.setStroke(Color.DARKGREEN);
	    gc.strokeText(name, posX, posY+(pixelY/2));
    }
    
    public void eraseScreen() {
    	gc.clearRect(0, 0, getWidth(), getHeight());

    }
    public void drawCritters() {
    	gc.clearRect(0, 0, getWidth(), getHeight());
//    	for(int i = 0; i < this.drawQueueS.size(); i++) {
//    		drawCritter(this.drawQueueS.get(i),this.drawQueueX.get(i),this.drawQueueY.get(i));
//    	}
    	
    	for(int i = 0; i < this.drawQueueShape.size(); i++) {
//    		boolean draw = true;
//    		for(int j = 0; j < this.drawQueueShape.size(); j++) {
//    			if(this.drawQueueX.get(j) == this.drawQueueX.get(i) && this.drawQueueY.get(j) == this.drawQueueY.get(i)) {
//    				draw = false;
//    			}
//    		}
    		//if(draw == true) {
	    		int posX = this.drawQueueX.get(i);
	    		int posY = this.drawQueueY.get(i);
	
	        	double pixelX = getWidth()/Params.world_width;
	            double pixelY = getHeight()/Params.world_height; 
	        	posX *=pixelX;
	    	    posY *=pixelY;
	    	   // gc.setFill(BLACK);
	    	    gc.setFill(this.drawQueueC.get(i).viewColor());
	    	    if(this.drawQueueS.get(i).equals("@")) {
	        		drawCritter(this.drawQueueS.get(i),this.drawQueueX.get(i),this.drawQueueY.get(i));
	
	    	    }
	    	    else {
		    	    switch(this.drawQueueShape.get(i)) {
		    	    	case DIAMOND: 
		    	    		gc.fillOval(posX, posY, 7, 20);
		    	    	case TRIANGLE:
		    	    		gc.fillRect(posX, posY, 10, 10);
		    	    	case CIRCLE: 
		    	    		gc.fillOval(posX, posY, 10, 10);
		    	    	case SQUARE:
		    	    		gc.fillRect(posX, posY, 10, 10);
		    	    	case STAR: 
		    	    		gc.fillOval(posX, posY, 15, 5);
		    	    		
		    	    }
	    	    }
    		//}
    	    //gc.setFont(new Font(15));
    	   // gc.strokeText(name, posX, posY);
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
	public void setShape(List<Critter.CritterShape> s) {
		this.drawQueueShape = s;
	}
	public void setC(List<Critter> s) {
		this.drawQueueC = s;
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