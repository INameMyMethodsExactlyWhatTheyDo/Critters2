package assignment5;

import java.util.*;
import javafx.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.*;
import javafx.scene.*;
import javafx.event.*;

public class Gui extends Application{

	
	//https://codereview.stackexchange.com/questions/151800/snake-in-javafx/151845
    @Override
    public void start(Stage stage){
    	Circle circEx = new Circle();
    	circEx.setCenterX(150.0f); 
    	circEx.setCenterY(135.0f); 
    	circEx.setRadius(100.0f);
    	
    	Pane p1 = new Pane();
    	Pane p2 = new Pane();
    	GridPane test = new GridPane();
    	Grid testgrid = new Grid();
    	GridPane test2 = testgrid.gridP;

        Scene s2 = new Scene(p2, 500, 300);        
    	Button b1 = new Button("Click me ");
    	Button b2 = new Button("click me again");
    	Button b3 = new Button("Stats stuff");
    	b3.setLayoutY(100);
    	p1.getChildren().add(b3);
    	Label l1 = new Label("SCence 1");
    	Label l2 = new Label("SCecnene 2");
    	p1.getChildren().add(b1);
    	p1.getChildren().add(l1);
    	l1.setFont(new Font(28));
    	l1.setLayoutY(100);
    	l1.setLayoutX(400);
    	l1.setMaxSize(200, 200);
    	b1.setFont(new Font(28));
    	b1.setMaxSize(100,100);
    	
    	p2.getChildren().add(b2);
    	p2.getChildren().add(l2);
    	l2.setFont(new Font(20));
    	l2.setLayoutY(50);
    	l2.setLayoutX(50);
    	l2.setMaxSize(200, 200);
    	b2.setFont(new Font(20));
    	b2.setLayoutX(100);
    	b2.setMaxSize(200,200);
        StackPane sp= new StackPane();

    	List<Circle> testPop = new java.util.ArrayList<Circle>();
        ResizableCanvas can = new ResizableCanvas();
        BorderPane stackp = new BorderPane();
       // StackPane sp= new StackPane();
    	//button adds and moves the circles
        //we can make this step
        GraphicsContext g = can.getGraphicsContext2D();
    	b1.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			//stage.setScene(s2);
    			Circle circEx = new Circle();

    	    	circEx.setRadius(10.0f);
    			test2.add(circEx, 0, 4);
    			double canX = circEx.getLayoutX();
    			double canY = circEx.getLayoutY();
    			
    			//this will create random critter squares
    		    can.drawCritter(test2);
    			
    			testPop.add(circEx);
    			
    			//use to convert index (row, col) to pixel position
    			double pixelX = test2.getWidth()/Params.world_width;
    		    double pixelY = test2.getHeight()/Params.world_height;

				int indexY = test2.getRowIndex(circEx);
				int indexX = test2.getColumnIndex(circEx);
				
    			g.fillRect(indexX*pixelX, indexY*pixelY, 20, 20);


    			
    			can.drawQueue.add(circEx);
    			circEx.getCenterX();
    			for(int i = 0; i < testPop.size(); i++) {
    				int x = Critter.getRandomInt(2);
    				int y = Critter.getRandomInt(2);

    				int xCo = test2.getRowIndex(testPop.get(i));
    				int yCo = test2.getColumnIndex(testPop.get(i));

    				test2.setColumnIndex(testPop.get(i), yCo + y );
    				test2.setRowIndex(testPop.get(i), xCo + x);

    	    		
    	    	}
    		}
    	});
    	
    	b2.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	stage.setScene(s2);
    	    
    	    }
    	});
    	
    	b3.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	
    	    	//stage.setScene(s1);
    	    
    	    }
    	});
    	//Scene s1 = new Scene(test, 500, 500);
        stage.setTitle("My app");

//        stackp.getChildren().add(test2);
//        stackp.getChildren().add(can);
//        stackp.getChildren().add(b1);
        
        sp.getChildren().add(test2);
        sp.getChildren().add(can);
        stackp.setRight(b2);

        stackp.setCenter(sp);
        stackp.setLeft(b1);
        //stackp.setRight(b2);

       // Bind canvas size to stack pane size.
       can.widthProperty().bind(sp.widthProperty());
       can.heightProperty().bind(sp.heightProperty());
    
    
    
    
       stage.setScene(new Scene(stackp, 700, 700));
       //stage.setScene(new Scene(bp,500, 500));
       stage.setTitle("Tip 1: Resizable Canvas");
       stage.show();
        
    }
    
    public static void main(String[] args){
        Application.launch(args);

    }


    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.BLUE);

        gc.fillOval(10, 60, 30, 30);

    }

}
