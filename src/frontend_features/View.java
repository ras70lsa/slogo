package frontend_features;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Stack;

import backend_slogo_team04.Action;
import backend_slogo_team04.Parser;
import constants.DisplayConstants;
import frontend_slogo_team04.GuiUserOption;
import frontend_slogo_team04.State;
import frontend_slogo_team04.TestingState;
import frontend_slogo_team04.ViewUIState;
import frontend_slogo_team04.VisualTurtle;
import frontend_slogo_team04.VisualizationAction;
import interfaces_slogo_team04.IView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Model;
import model.ViewModel;

public class View extends StaticPane implements Observer{

	private VisualTurtle turtle;
	private double scaleFactor = 1;
	private ViewUIState visuals;
	private ViewModel model;
	private Stack<Line> lineManager;
	private static final double INTERVAL_LENGTH = 1000;
	
	public View(IView model) {
		turtle = new VisualTurtle(getFirstImage());
		this.model = (ViewModel) model;
		addCSS("visual_resources/DefaultView.css");
		visuals = new ViewUIState();
		lineManager = new Stack<Line>();
		addSilentListeners();
		model.addObserver(this);
		setUp();
	}
	
	private Image getFirstImage() {
		return new Image(getClass().getClassLoader().getResourceAsStream("visual_resources/turtle.jpg"));
	}

	private void addSilentListeners() {
		visuals.getImageProperty().addListener((a,b,c) -> setTurtleImage(c));
		
	}

	public void setUp() {
		add(turtle, getCenterXCor(turtle.getFitWidth()),getCenterYCor(turtle.getFitHeight()));
	}


	private void setTurtleImage(Image i) {
		turtle.setImage(i);
		
	}

	public double getMaxWidth() {
		return DisplayConstants.VIEW_WIDTH;
	}

	public double getMaxHeight() {
		return DisplayConstants.VIEW_HEIGHT;
	}


	public double getScaleFactor() {
		return scaleFactor;
	}
	

	public State getState() {
		return null;
	}


	public double getCenterXCor(double imageWidth){
		return DisplayConstants.VIEW_WIDTH/2-(imageWidth/2);
		
	}
	
	public double getCenterYCor(double imageHeight){
		return DisplayConstants.VIEW_HEIGHT/2-(imageHeight/2);
		
	}

	@Override
	protected List<Node> getReleventProperties(GuiUserOption factory) {
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(visuals.getImageProperty(), "Testing"));
		return list;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		double modelX = model.xCor();
		double modelY = model.yCor();
		if (model.isPenDown() == 1){
			turn(model.getRotation());
			moveTurtle(modelX, modelY);
		}else{
			updateTurtleAndDraw(modelX, modelY);
		}
	}

	public double home() {
		updateTurtleAndDraw(getCenterXCor(turtle.getFitWidth()),getCenterYCor(turtle.getFitHeight()));
		return 0;
	}
	
	public double clearScreen() {
		lineManager.forEach(l -> l.setVisible(false));
		lineManager.clear();
		resetTurtlePosition();
		return 0;
	}
	
	public void resetTurtlePosition() {
		turtle.setTranslateX(getCenterXCor(turtle.getFitWidth()));
		turtle.setTranslateY(getCenterYCor(turtle.getFitHeight()));
	}
	
	public void updateTurtleAndDraw(double modelX, double modelY) {
		Line line = new Line();
		//add(line,calculateLineXCor(model.getHistory(),model.getPreviousYCor()));
		//add(line,10,10);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames()
		.add(createKeyFrame(line.startXProperty(), line.startYProperty(), translateToLineX(modelX), translateToLineY(modelY)));
		timeline.getKeyFrames()
		.add(createKeyFrame(turtle.xProperty(), turtle.yProperty(), translateToTurtleX(modelX), translateToTurtleY(modelY)));
		timeline.play();
		lineManager.add(line);
	}
	
	private Stack<Line> getLineManager(){
		return lineManager;
	}
	
	public void moveTurtle(double modelX, double modelY) {
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(createKeyFrame(turtle.xProperty(), turtle.yProperty(), translateToTurtleX(modelX), translateToTurtleY(modelY)));
		timeline.play();
	}
	
	public KeyFrame createKeyFrame(DoubleProperty startX, DoubleProperty startY, double endX, double endY) {
		KeyValue kv_x = new KeyValue(startX, endX, Interpolator.LINEAR);
		KeyValue kv_y = new KeyValue(startY, endY, Interpolator.LINEAR);
		KeyFrame kf = new KeyFrame(Duration.millis(INTERVAL_LENGTH), kv_x, kv_y);
		return kf;
	}
	
	public void turn(double rotation) {
		RotateTransition rt = new RotateTransition(Duration.millis(INTERVAL_LENGTH), turtle);
		rt.setByAngle(rotation);
		rt.play();
	}

	private double translateToLineX(double xCor){
		return xCor + getCenterXCor(turtle.getFitHeight());
	}
	
	public double translateToLineY(double yCor){
		return getCenterYCor(turtle.getFitHeight()) - yCor;
	}
	
	public double translateToTurtleX(double xCor){
		return xCor;
	}
	
	public double translateToTurtleY(double yCor){
		return -yCor;
	}
	
	public static void main(String[] args){
		
	}
	
}

