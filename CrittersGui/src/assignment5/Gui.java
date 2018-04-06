package assignment5;


import java.lang.reflect.InvocationTargetException;
import java.util.*;

import assignment5.Critter.CritterShape;
//import assignment4.Critter;
//import assignment4.Critter;
//import assignment4.InvalidCritterException;
import javafx.*;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
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
	public Timeline time;
	static int count;
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
    	Button buttonStartAn = new Button("Start");
    	Button buttonStopAn = new Button("Stop");

    	TextField name = new TextField();
    	name.setPromptText("Enter critter to make");
    	name.setPrefColumnCount(15);
    	name.getText();
    	
    	Label title = new Label();
    	title.setText("Critters Game");
    	topPane.getChildren().add(title);
    	
    	title.setFont(Font.font ("Verdana", 35));
    	title.setTextFill(Color.DARKSLATEGRAY);
    	
    	Label stats = new Label("STATS\n");
    	stats.setLayoutY(450);
    	/**
    	 * Properties
    	 */
    	canvas.widthProperty().bind(middlePane.widthProperty());
        canvas.heightProperty().bind(middlePane.heightProperty());

        middlePane.getChildren().add(canvas);

        leftPane.getChildren().add(buttonClear);
        leftPane.getChildren().add(buttonQuit);
        leftPane.getChildren().add(buttonStartAn);

        leftPane.getChildren().add(buttonStopAn);


        rightPane.getChildren().add(buttonStep);
    	rightPane.setPadding(new Insets(10, 10, 10, 10));
    	rightPane.getChildren().add(buttonMakeCritter);
    	rightPane.getChildren().add(buttonStats);
    	rightPane.getChildren().add(name);
    	rightPane.getChildren().add(stats);
    	
    	mainPane.setCenter(middlePane);
    	mainPane.setLeft(leftPane);
    	mainPane.setRight(rightPane);
    	mainPane.setBottom(bottomPane);
    	mainPane.setTop(topPane);
    	//Defining the Submit button
    	
    	/**
    	 * labels
    	 */
    	
    	/**
    	 * button layout
    	 */
    	buttonMakeCritter.setLayoutY(100);
    	buttonStats.setLayoutY(300);
    	buttonEnterStat.setLayoutY(400);
    	buttonQuit.setLayoutY(500);
    	buttonStep.setLayoutY(250);
    	buttonClear.setLayoutY(450);
    	buttonStartAn.setLayoutY(200);
    	buttonStopAn.setLayoutY(250);

    	//buttonClear.setMaxWidth(200);
    	//buttonQuit.setMaxWidth(200);

    	Font buttonFont = new Font(20);
    	int buttonX = 200;
    	
    	buttonMake1.setMaxSize(buttonX, buttonX);	
    	buttonMake1.setFont(buttonFont);
    	buttonMake2.setMaxSize(buttonX, buttonX);
		buttonMake2.setLayoutY(50);
		buttonMake2.setFont(buttonFont);
	
    	//Adding a Label
    	Label labelCrit = new Label();
    	labelCrit.setLayoutY(150);
    	rightPane.getChildren().add(labelCrit);
    	
    	Label labelErr = new Label();
    	labelErr.setLayoutY(350);
    	rightPane.getChildren().add(labelErr);
    	
    	Label animationLabel = new Label("Animation");
    	animationLabel.setLayoutY(115);
    	leftPane.getChildren().add(animationLabel);
    	
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
    	
    	///////////////////////////////
    	Slider speedSlider = new Slider();
    	speedSlider.setMin(1);
    	speedSlider.setMax(100);
    	speedSlider.setValue(1);
    	speedSlider.setShowTickLabels(true);
    	speedSlider.setShowTickMarks(false);
    	speedSlider.setMajorTickUnit(49);
    	speedSlider.setMinorTickCount(5);
    	speedSlider.setBlockIncrement(10);
    	leftPane.getChildren().add(speedSlider);
    	speedSlider.setLayoutY(150);

    	Label speedSliderVal = new Label(Integer.toString((int)speedSlider.getValue()));
    	speedSliderVal.setLayoutX(150);
    	speedSliderVal.setLayoutY(150);
    	leftPane.getChildren().add(speedSliderVal);
    	
    	Label speedQuantity = new Label("Speed");
    	speedQuantity.setLayoutX(180);
    	speedQuantity.setLayoutY(150);
    	leftPane.getChildren().add(speedQuantity);
    	
    	//////////////////////////////////////
    	
    	makeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	int val = (int) makeSlider.getValue();
            	makeSliderVal.setText(Integer.toString(val));
            }
        });
    	
    	stepSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	int val = (int) stepSlider.getValue();
            	stepSliderVal.setText(Integer.toString(val));
            }
        });
    	
    	speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	int val = (int) speedSlider.getValue();
            	speedSliderVal.setText(Integer.toString(val));
            }
        });
    	
    	Label fillerLabel = new Label();
    	fillerLabel.setLayoutY(100);
    	bottomPane.getChildren().add(fillerLabel);
    	
    	Label labelStats = new Label();
    	labelStats.setLayoutX(100);
    	labelStats.setLayoutY(30);

    	labelStats.setFont(new Font("Cambria", 18));
    	labelStats.setMaxWidth(500);
    	labelStats.setWrapText(true);
    	bottomPane.getChildren().add(labelStats);
    	labelStats.setText("");    
    	
    	Label labelIndiv = new Label();
    	labelIndiv.setLayoutX(100);
    	labelIndiv.setLayoutY(10);

    	labelIndiv.setFont(new Font("Cambria", 18));
    	labelIndiv.setMaxWidth(500);
    	labelIndiv.setWrapText(true);
    	bottomPane.getChildren().add(labelIndiv);
    	labelIndiv.setText("Individual Stats:");   
    	
		//Defining the Name text field
		TextField statCrit = new TextField();
		statCrit.setPromptText("stats critter");
		statCrit.setPrefColumnCount(15);
		statCrit.getText();
		statCrit.setLayoutY(350);

    	//Button buttonSeed = new Button("Seed");
        leftPane.getChildren().add(buttonSeed);
    	buttonSeed.setLayoutY(400);


	    buttonSeed.setOnAction(new EventHandler<ActionEvent>() {
	       	@Override
	            public void handle(ActionEvent e) {
	       			int seedNum = Critter.getRandomInt(1000);
	       			Critter.setSeed(seedNum);
	       	     }
	    });
		
		
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
    				catch(InvalidCritterException d) {
    					String none = "";
    					if(statCrit.getText().equals("")) {
    						labelErr.setText("Enter a critter for stats!");
        				}
    					else {
    						System.out.println("Invalid Critter Class: " + statCrit.getText());
    						labelErr.setText("Invalid Critter Class: " + statCrit.getText());
    			    	//rightPane.getChildren().add(labelCrit);
    					}

    				}
        			catch (ClassNotFoundException e9) {
        				//System.out.println("Invalid Critter Class: " + statCrit.getText());
						//labelCrit.setText("Invalid Critter Class: " + statCrit.getText());
        				String none = "";
    					if(statCrit.getText().equals("")) {
    						labelErr.setText("Enter a critter for stats!");
        				}
    					else {
    						System.out.println("Invalid Critter Class: " + statCrit.getText());
    						labelErr.setText("Invalid Critter Class: " + statCrit.getText());
    			    	//rightPane.getChildren().add(labelCrit);
    					}
        			} catch (IllegalAccessException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (IllegalArgumentException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			} catch (InvocationTargetException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}  catch (NoSuchMethodException e1) {
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
					//System.out.println("weeee " + name.getText());
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
    

    	
    	//this button runs how many steps you want it to
    	buttonStartAn.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	//DISABLE ALL BUTTONS

    	    	buttonStep.setDisable(true);
    	    	buttonClear.setDisable(true);
    	    	buttonQuit.setDisable(true);
    	    	buttonMakeCritter.setDisable(true);
    	    	buttonStats.setDisable(true);
    	    	buttonEnterStat.setDisable(true);
    	    	buttonSeed.setDisable(true);
    	    	buttonStartAn.setDisable(true);
    	    	makeSlider.setDisable(true);
    	    	stepSlider.setDisable(true);
    	    	speedSlider.setDisable(true);

    	    	
    	    	int speedNum = (int)speedSlider.getValue();
    	    	
    	    	//if(speedNum != 1) {
    	    	double speed = 1;
    	    	speed= speed/speedNum;
    	    	KeyFrame keyframe = new KeyFrame(Duration.seconds(speed), e1 ->
    	    	{ updateStep(canvas, stats); });
    	    	

    	    	 Timeline timeline = new Timeline(keyframe);
    	    //	keyframe.
    	    		timeline.setCycleCount(Animation.INDEFINITE);
    	    		timeline.play();
    	    		setT(timeline);
	    		//}else {
	    		//	updateStep(canvas, stats);
	    		//}
    	    	
    	    }
    	});

    	
    	buttonStopAn.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
	    		getT().stop();

    	    	buttonStep.setDisable(false);
    	    	buttonClear.setDisable(false);
    	    	buttonQuit.setDisable(false);
    	    	buttonMakeCritter.setDisable(false);
    	    	buttonStats.setDisable(false);
    	    	buttonEnterStat.setDisable(false);
    	    	buttonSeed.setDisable(false);
    	    	buttonStartAn.setDisable(false);
    	    	makeSlider.setDisable(false);
    	    	stepSlider.setDisable(false);
    	    	speedSlider.setDisable(false);
    	    	

    	    }
    	});
    	
    	buttonStep.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	int stepNum = (int)stepSlider.getValue();
    	    	for(int i = 0; i < stepNum; i++) {
    	    	Critter.worldTimeStep();
    			//Critter.displayWorld();
    		
    	    	}
    	    	canvas.setX(Critter.getPopX());
    			canvas.setY(Critter.getPopY());
    		
    			canvas.setS(Critter.getPopType());
    			canvas.setShape(Critter.getShapeType());
    			canvas.setC(Critter.getCritterPop());
    			canvas.drawCritters();
    			stats.setText((Critter.getStats()));
    	    }
    	});
    	
    	//clears the screen and stuff
    	buttonClear.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	Critter.clearWorld();
    	    	canvas.eraseScreen();
    	    	makeSlider.setValue(1);
    	    	stepSlider.setValue(1);
    	    	speedSlider.setValue(1);
    	    }
    	});
    	/*
    	 * Show
    	 */
    	stage.setScene(new Scene(mainPane, 1000, 700));
    	stage.setTitle("Critters");
    	stage.show();
 
    }
    
    public static void main(String[] args){
        Application.launch(args);

    }
    
	public Timeline getT() {
		return time;
	}
	public void setT(Timeline t) {
		time = t;
	}
    public void updateStep(ResizableCanvas canvas, Label stats) {
    	Critter.worldTimeStep();
		Critter.displayWorld();
		canvas.setX(Critter.getPopX());
		canvas.setY(Critter.getPopY());
	
		canvas.setS(Critter.getPopType());
		canvas.setShape(Critter.getShapeType());
		canvas.setC(Critter.getCritterPop());
		canvas.drawCritters();
		stats.setText((Critter.getStats()));
		
    }

}
