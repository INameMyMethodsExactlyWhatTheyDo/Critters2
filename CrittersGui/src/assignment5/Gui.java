package assignment5;


import java.lang.reflect.InvocationTargetException;
import java.util.*;

//import assignment4.Critter;
//import assignment4.Critter;
//import assignment4.InvalidCritterException;
import javafx.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.event.*;

public class Gui extends Application{

	
	//https://codereview.stackexchange.com/questions/151800/snake-in-javafx/151845
    @Override
    public void start(Stage stage){

    	/**
    	 * Gui Structure
    	 */
    	BorderPane mainPane = new BorderPane();
    	Pane middlePane = new Pane();
    	Pane rightPane = new Pane();
    	Pane leftPane = new Pane();
    	Pane bottomPane = new Pane();
    	Pane topPane = new Pane();
    	ResizableCanvas canvas = new ResizableCanvas();
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	
    	
    	middlePane.setStyle("-fx-background-color: lightsteelblue;");

    	/////
    	//rightPane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    	/**
    	 * Gui Actors
    	 */
    	Button buttonMake1 = new Button("Make1");
    	Button buttonMake2 = new Button("Make2");
    	
    	Button buttonStep = new Button("Step");
    	Button buttonClear = new Button("Clear");
    	Button buttonQuit = new Button("Quit ");
    	Button buttonMakeCritter = new Button("Submit");
    	Button buttonStats = new Button("stats");
    	Button buttonEnterStat = new Button("Enter");
    	Button buttonSeed = new Button("seed");

    	//Button buttonSlide = new Button("go");
    	
    	TextField name = new TextField();
    	name.setPromptText("Enter critter to make");
    	name.setPrefColumnCount(15);
    	name.getText();
    	
    	Label title = new Label();
    	title.setText("Critters Game");
    	topPane.getChildren().add(title);
    	
    	title.setFont(Font.font ("Verdana", 35));
    	title.setTextFill(Color.DARKSLATEGRAY);
    	/**
    	 * Properties
    	 */
    	canvas.widthProperty().bind(middlePane.widthProperty());
        canvas.heightProperty().bind(middlePane.heightProperty());
        
        //leftPane.getChildren().add(buttonMake1);
       // leftPane.getChildren().add(buttonMake2);
        middlePane.getChildren().add(canvas);

        leftPane.getChildren().add(buttonClear);
        leftPane.getChildren().add(buttonQuit);

        rightPane.getChildren().add(buttonStep);
    	rightPane.setPadding(new Insets(10, 10, 10, 10));
    	rightPane.getChildren().add(buttonMakeCritter);
    	rightPane.getChildren().add(buttonStats);
    	rightPane.getChildren().add(name);
    	
    	mainPane.setCenter(middlePane);
    	mainPane.setLeft(leftPane);
    	mainPane.setRight(rightPane);
    	mainPane.setBottom(bottomPane);
    	mainPane.setTop(topPane);
    	//Defining the Submit button
    	
    	/**
    	 * button layout
    	 */
    	buttonMakeCritter.setLayoutY(100);
    	buttonStats.setLayoutY(400);
    	buttonEnterStat.setLayoutY(500);
    	buttonQuit.setLayoutY(500);
    	buttonStep.setLayoutY(250);
    	buttonClear.setLayoutY(450);
    	//buttonClear.setMaxWidth(200);
    	//buttonQuit.setMaxWidth(200);

    	Font buttonFont = new Font(20);
    	int buttonX = 200;
    	
    	buttonMake1.setMaxSize(buttonX, buttonX);	
    	buttonMake1.setFont(buttonFont);
    	buttonMake2.setMaxSize(buttonX, buttonX);
		buttonMake2.setLayoutY(50);
		buttonMake2.setFont(buttonFont);
	
	//buttonStep.setMaxSize(buttonX, buttonX);
	//buttonStep.setLayoutY(250);
	//buttonStep.setFont(buttonFont);

    	//Adding a Label
    	Label labelCrit = new Label();
    	labelCrit.setLayoutY(150);
    	rightPane.getChildren().add(labelCrit);
    	
    	/**
    	 * Slider and Labels for Sliders
    	 */
    	
    	Slider makeSlider = new Slider();
    	makeSlider.setMin(1);
    	makeSlider.setMax(100);
    	makeSlider.setValue(1);
    	makeSlider.setShowTickLabels(true);
    	makeSlider.setShowTickMarks(false);
    	makeSlider.setMajorTickUnit(49);
    	makeSlider.setMinorTickCount(5);
    	makeSlider.setBlockIncrement(10);
    	rightPane.getChildren().add(makeSlider);
    	makeSlider.setLayoutY(50);

    	Label makeSliderVal = new Label(Integer.toString((int) makeSlider.getValue()));
    	makeSliderVal.setLayoutX(150);
    	makeSliderVal.setLayoutY(50);
    	rightPane.getChildren().add(makeSliderVal);
    	
    	Label critQuantity = new Label("Critters");
    	critQuantity.setLayoutX(180);
    	critQuantity.setLayoutY(50);
    	rightPane.getChildren().add(critQuantity);
    	
    	Slider stepSlider = new Slider();
    	stepSlider.setMin(1);
    	stepSlider.setMax(100);
    	stepSlider.setValue(1);
    	stepSlider.setShowTickLabels(true);
    	stepSlider.setShowTickMarks(false);
    	stepSlider.setMajorTickUnit(49);
    	stepSlider.setMinorTickCount(5);
    	stepSlider.setBlockIncrement(10);
    	rightPane.getChildren().add(stepSlider);
    	stepSlider.setLayoutY(200);

    	Label stepSliderVal = new Label(Integer.toString((int) stepSlider.getValue()));
    	stepSliderVal.setLayoutX(150);
    	stepSliderVal.setLayoutY(200);
    	rightPane.getChildren().add(stepSliderVal);
    	
    	Label stepQuantity = new Label("Steps");
    	stepQuantity.setLayoutX(180);
    	stepQuantity.setLayoutY(200);
    	rightPane.getChildren().add(stepQuantity);
    	
    	makeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    //cappuccino.setOpacity(new_val.doubleValue());
            	int val = (int) makeSlider.getValue();
            	makeSliderVal.setText(Integer.toString(val));
            }
        });
    	
    	stepSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    //cappuccino.setOpacity(new_val.doubleValue());
            	int val = (int) stepSlider.getValue();
            	stepSliderVal.setText(Integer.toString(val));
            }
        });
    	
    	Label fillerLabel = new Label();
    	fillerLabel.setLayoutY(100);
    	bottomPane.getChildren().add(fillerLabel);
    	
    	Label labelStats = new Label();
    	labelStats.setLayoutX(100);
    	labelStats.setLayoutY(20);

    	labelStats.setFont(new Font("Cambria", 22));
    	labelStats.setMaxWidth(500);
    	labelStats.setWrapText(true);
    	bottomPane.getChildren().add(labelStats);
    	labelStats.setText("Here is where stats are put");    	
    	
		//Defining the Name text field
		TextField statCrit = new TextField();
		statCrit.setPromptText("stats critter");
		statCrit.setPrefColumnCount(15);
		statCrit.getText();
		statCrit.setLayoutY(450);

    	
    	//this button will quit the terminal
	    buttonQuit.setOnAction(new EventHandler<ActionEvent>() {

	       	@Override
	            public void handle(ActionEvent e) {
	       	 		System.exit(0);
	       	     }
	    });	 		
	 		
	 		
    	//this button will show another button [ENTERSTAT] to enter critters into
	    buttonStats.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	    public void handle(ActionEvent e) {
	    	 		rightPane.getChildren().add(statCrit);
	    	 		rightPane.getChildren().add(buttonEnterStat);
	
	    	     }
    	 });
    	
	    //this button will run all stats
	    buttonEnterStat.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	    public void handle(ActionEvent e) {
        	
        			try {

						Object obj = null;
						String runStats = "runStats";
	
						String pkg = "assignment5.";
	
						pkg = pkg + statCrit.getText();
	
						Class c =   Class.forName(pkg);//.newInstance();
						Critter crit = (Critter) c.newInstance();
						Class cd = crit.getClass();
	
						java.lang.reflect.Method method = c.getMethod("runStats", java.util.List.class);
						String stringStats = (String) method.invoke(c, Critter.getInstances(pkg));
						
				    	labelStats.setText(stringStats);
				 

				
        			} 
        			catch (ClassNotFoundException e9) {
        				System.out.println("Invalid Critter Class: " + statCrit.getText());
        			} catch (IllegalAccessException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (IllegalArgumentException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (InvocationTargetException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (InvalidCritterException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (NoSuchMethodException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (SecurityException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (InstantiationException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		
        	 		System.out.println("hello");
        	 		rightPane.getChildren().remove(statCrit);
        	 		rightPane.getChildren().remove(buttonEnterStat);
        	 		statCrit.clear();
        	     }
        	 });
    	
    	
    	
    	//Setting an action for the Submit button
    	buttonMakeCritter.setOnAction(new EventHandler<ActionEvent>() {

    	@Override
    	    public void handle(ActionEvent e) {
    		
				try {
					int makeNum = (int)makeSlider.getValue();
					String pkg = "assignment5.";
					pkg = pkg+name.getText();
					for(int i = 0; i<makeNum; i++) {
						Critter.makeCritter(pkg);
						canvas.setX(Critter.getPopX());
	    	    		canvas.setY(Critter.getPopY());
	    	    		///sdfsdfsdfsd
	    	    	
	    	    		canvas.setS(Critter.getPopType());
	    	    		canvas.setShape(Critter.getShapeType());
	    	    		canvas.setC(Critter.getCritterPop());
	    	    		canvas.drawCritters();
					}
					System.out.println("weeee " + name.getText());
					labelCrit.setText(name.getText() + " was made!");

				}

				catch(InvalidCritterException d) {
					String none = "";
					if(name.getText().equals("")) {
						labelCrit.setText("Enter a critter to make!");
    				}
					else {
						System.out.println("Invalid Critter Class: " + name.getText());
						labelCrit.setText("Invalid Critter Class: " + name.getText());
			    	//rightPane.getChildren().add(labelCrit);
					}

				}
				name.clear();
    	     }
    	 });
    
//    	buttonMake1.setOnAction(new EventHandler<ActionEvent>() {
//    	    @Override public void handle(ActionEvent e) {
//    	    	//Critter.makeCritter(critter_class_name);
//    	    	canvas.drawCritter("F", 0, 0);
//    	    	try {
//					Critter.makeCritter("assignment5.MyCritter1");
//				} catch (InvalidCritterException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//    	    }
//    	});
    	
    	
    	//this button runs how many steps you want it to
    	buttonStep.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	int stepNum = (int)stepSlider.getValue();
    	    	for(int i = 0; i < stepNum; i++) {
    	    		Critter.worldTimeStep();
    	    		Critter.displayWorld();
    	    		canvas.setX(Critter.getPopX());
    	    		canvas.setY(Critter.getPopY());
    	    		///sdfsdfsdfsd
    	    	
    	    		canvas.setS(Critter.getPopType());
    	    		canvas.setShape(Critter.getShapeType());
    	    		canvas.setC(Critter.getCritterPop());
    	    		canvas.drawCritters();
    	    		System.out.print(Critter.getPopX().size());
    	    	}
    	    }
    	});
    	
    	//clears the screen and stuff
    	buttonClear.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {

    	    	Critter.clearWorld();
    	    	canvas.eraseScreen();
    	    }
    	});
    	
    	
    	
    	
    	/*
    	 * Show
    	 */
    	stage.setScene(new Scene(mainPane, 1000, 700));
    	stage.setTitle("Critters");
    	stage.show();
    	/*
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
        Pane sp= new Pane();
    	List<Circle> testPop = new java.util.ArrayList<Circle>();
        ResizableCanvas can = new ResizableCanvas();
        BorderPane stackp = new BorderPane();
        //StackPane sp= new StackPane();
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
    			/*
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
    	Scene s1 = new Scene(test, 500, 500);
        stage.setTitle("My app");

//        stackp.getChildren().add(test2);
//        stackp.getChildren().add(can);
//        stackp.getChildren().add(b1);
        
        //sp.getChildren().add(test2);
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
        */
    }
    
    public static void main(String[] args){
        Application.launch(args);

    }


    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillOval(10, 60, 30, 30);

    }

}
