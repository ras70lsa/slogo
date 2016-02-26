package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import constants.DisplayConstants;
import frontend_features.StaticPane;
import interfaces_slogo_team04.IView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class View extends StaticPane implements Observer {

	private static final double INTERVAL_LENGTH = 1000;
	private Stack<Line> lineManager;
	private VisualTurtle turtle;
	private boolean penDown = true;
	private boolean isShowing = true;
	private double scaleFactor = 1;
	private ViewUIState visuals;
	private IView model;
	
	
	public View(IView model) {
		visuals = new ViewUIState();
		this.model = model;
		addCSS("visual_resources/DefaultView.css");
		addListeners();
		model.addObserver(this);
	}

	 private void addListeners() {
		 visuals.getImageProperty().addListener((a, b, newValue) -> setTurtleImage(newValue));
	 }
	 
	public void moveTurtle(double endX, double endY) {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.getKeyFrames().add(createKeyFrame(turtle.xProperty(), turtle.yProperty(), endX, endY));
		timeline.play();
	}
	
	public void setUp() {
		Pane newDisplay = new Pane();
		newDisplay.setPrefSize(DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		setPane(newDisplay);
		setTurtleImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif")));
		add(turtle, getCenterXCor(), getCenterYCor());
	}

	public void draw(double endX, double endY) {
		Line line = new Line();
		line.setStartX(turtle.getTranslateX());
		line.setStartY(turtle.getTranslateY());
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.getKeyFrames().add(createKeyFrame(line.startXProperty(), line.startYProperty(), endX, endY));
		timeline.play();
		lineManager.add(line);
	}

	public KeyFrame createKeyFrame(DoubleProperty startX, DoubleProperty startY, double endX, double endY) {
		KeyValue kv_x = new KeyValue(startX, endX, Interpolator.LINEAR);
		KeyValue kv_y = new KeyValue(startY, endY, Interpolator.LINEAR);
		KeyFrame kf = new KeyFrame(Duration.millis(INTERVAL_LENGTH), kv_x, kv_y);
		return kf;
	}

	public void turn(double newHeading) {
		RotateTransition rt = new RotateTransition(Duration.millis(INTERVAL_LENGTH), turtle);
		rt.setByAngle(newHeading - getHeading());
		rt.play();
	}

	public double setHeading(double degrees) {
		turtle.setHeading(degrees);
		return 0;
	}

	public double getHeading() {
		return turtle.getHeading();
	}
	
	public boolean isPenDown() {
		return penDown;
	}


	public boolean isShowing() {
		return isShowing;
	}

	public State getState() {
		return visuals;
	}

	public double setxy(double x, double y) {
		turtle.setTranslateX(x);
		turtle.setTranslateY(y);
		return 0;
	}

	public double showTurtle() {
		turtle.showTurtle();
		return 1;
	}

	public double hideTurtle() {
		turtle.hideTurtle();
		return 0;
	}

	public double home() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double clearScreen() {
		resetTurtlePosition();

		return 0;
	}


	public double xCor() {
		return turtle.getTranslateX() - getCenterXCor();
	}

	
	public void resetTurtlePosition() {
		turtle.setTranslateX(getCenterXCor());
		turtle.setTranslateY(getCenterYCor());
		lineManager.forEach(l -> l.setVisible(false));
		lineManager.clear();
	}

	public double getCenterXCor() {
		return DisplayConstants.VIEW_WIDTH / 2;
	}

	public double getCenterYCor() {
		return DisplayConstants.VIEW_HEIGHT / 2;
	}

	public double heading() {
		return turtle.getHeading();
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

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(visuals.getImageProperty(), "Testing"));
		return list;
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

