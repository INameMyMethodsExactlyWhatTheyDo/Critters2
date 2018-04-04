package assignment5;

import java.util.*;
import javafx.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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

    @Override
    public void start(Stage stage){
    	Circle circEx = new Circle();
    	circEx.setCenterX(150.0f); 
    	circEx.setCenterY(135.0f); 
    	circEx.setRadius(100.0f);
    	
    	Pane p1 = new Pane();
    	Pane p2 = new Pane();
    	GridPane test = new GridPane();
  
//    	Scene s1 = new Scene(p1, 800, 600);
    	Scene s1 = new Scene(test, 800, 600);
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
    	List<Circle> testPop = new java.util.ArrayList<Circle>();
    	
    	test.setGridLinesVisible(true);
    	
    	for (int i = 0; i < Params.world_height; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / Params.world_height);
            test.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < Params.world_width; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / Params.world_width);
            test.getRowConstraints().add(rowConst);         
        }
        
        test.add(b1, 1, 1);
        
    	//button adds and moves the circles
        //we can make this step
    	b1.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			//stage.setScene(s2);
    			Circle circEx = new Circle();
    	    	circEx.setCenterX(Math.random()*1600); 
    	    	circEx.setCenterY(Math.random()*800); 
    	    	circEx.setRadius(10.0f);
    			test.add(circEx, 0, 4);
    			testPop.add(circEx);
    			
    			for(int i = 0; i < testPop.size(); i++) {
    				int x = Critter.getRandomInt(2);
    				int y = Critter.getRandomInt(2);

    				int xCo = test.getRowIndex(testPop.get(i));
    				int yCo = test.getColumnIndex(testPop.get(i));

    				test.setColumnIndex(testPop.get(i), yCo + y );
    				test.setRowIndex(testPop.get(i), xCo + x);

    	    		
    	    	}
    		}
    	});
    	
    	b2.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	stage.setScene(s1);
    	    
    	    }
    	});
    	
    	b3.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	
    	    	//stage.setScene(s1);
    	    
    	    }
    	});
    	
        stage.setTitle("My app");
        stage.setScene(s1);
        //stage.show();
        
        
        
        /////////////////////////canvas stuff///////////

        
        //primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);


      // test.add(canvas, 5, 5);
       // stage.show();
        
        ///////////////////////////////
//        ResizableCanvas can = new ResizableCanvas();
//        
//        GridPane resizePane = new GridPane();
//        resizePane.getChildren().add(can);
//     
//        // Bind canvas size to stack pane size.
//        can.widthProperty().bind(resizePane.widthProperty());
//        can.heightProperty().bind(resizePane.heightProperty());
//     
//        stage.setScene(new Scene(resizePane));
//        stage.setTitle("Tip 1: Resizable Canvas");
//        stage.show();
       ResizableCanvas can = new ResizableCanvas();
       
       GridPane resizePane = new GridPane();
       test.getChildren().add(can);
    
       // Bind canvas size to stack pane size.
       can.widthProperty().bind(test.widthProperty());
       can.heightProperty().bind(test.heightProperty());
    
       stage.setScene(s1);
       stage.setTitle("Tip 1: Resizable Canvas");
       stage.show();
        
    }
    
    public static void main(String[] args){
        Application.launch(args);

    }


    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(5);
//        gc.strokeLine(40, 10, 10, 40);
        //gc.fillOval(w*5, h*5, 30, 30);
        gc.fillOval(10, 60, 30, 30);
       // gc.fillOval
//        gc.strokeOval(60, 60, 30, 30);
//        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
//        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
//        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
//        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
//        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
//        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
//        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
//        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40},
//                       new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90},
//                         new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140},
//                          new double[]{210, 210, 240, 240}, 4);
    }
    class ResizableCanvas extends Canvas {
    	 
        public ResizableCanvas() {
          // Redraw canvas when size changes.
          widthProperty().addListener(evt -> draw());
          heightProperty().addListener(evt -> draw());
        }
     
        private void draw() {
          double width = getWidth();
          double height = getHeight();
     
          GraphicsContext gc = getGraphicsContext2D();
          gc.clearRect(0, 0, width, height);
     
          gc.setStroke(Color.RED);
          gc.strokeLine(0, 0, width, height);
          gc.strokeLine(0, height, width, 0);
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
}
