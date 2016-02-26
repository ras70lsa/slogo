package frontend_slogo_team04;

import java.util.List;

import backend_slogo_team04.Action;
import backend_slogo_team04.Controller;
import constants.DisplayConstants;
import interfaces_slogo_team04.State;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class View extends StaticPane{

	private Controller myController;
	private VisualTurtle turtle;
	private TestingState state;
	private boolean penDown = true;
	private boolean isShowing = true;

	public View(TestingState state) {
		this.state = state;
		addListeners();
	}

	private void addListeners() {
		state.getColorProperty().addListener((a, b, newValue) -> updateColor(newValue));
	}

	public void setUp() {
		// TODO Auto-generated method stub
		Pane newDisplay = new Pane();
		newDisplay.setStyle(state.getBackgroundColor());
		newDisplay.setPrefSize(DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		setPane(newDisplay);
		setTurtleImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle.jpg")));
		add(turtle, getCenterXCor(),  getCenterYCor());
	}


	@Override
	public void setUp() {
		Pane newDisplay = new Pane();
		newDisplay.setStyle(state.getBackgroundColor());
		newDisplay.setPrefSize(DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		setPane(newDisplay);
		setTurtleImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif")));
		add(turtle, getCenterXCor(), getCenterYCor());
		// add(new Circle(10), getCenterXCor(), getCenterYCor());
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

	public void moveTurtle(double endX, double endY) {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.getKeyFrames().add(createKeyFrame(turtle.xProperty(), turtle.yProperty(), endX, endY));
		timeline.play();
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

	public double setxy(double x, double y) {
		turtle.setTranslateX(x);
		turtle.setTranslateY(y);
		return 0;
	}

	public double penDown() {
		penDown = true;
		return 1;
	}

	public double penUp() {
		penDown = false;
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

	public void resetTurtlePosition() {
		turtle.setTranslateX(getCenterXCor());
		turtle.setTranslateY(getCenterYCor());
		lineManager.forEach(l -> l.setVisible(false));
		lineManager.clear();
	}

	public double xCor() {
		return turtle.getTranslateX() - getCenterXCor();
	}

	public double yCor() {
		return getCenterYCor() - turtle.getTranslateY();
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

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
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

	public boolean isPenDown() {
		return penDown;
	}

	public boolean isShowing() {
		return isShowing;
	}
}


