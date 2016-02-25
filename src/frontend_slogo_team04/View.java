package frontend_slogo_team04;

import java.util.List;

import backend_slogo_team04.Action;
import backend_slogo_team04.Controller;
import constants.DisplayConstants;
import interfaces_slogo_team04.IState;
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

public class View extends ModularPane implements VisualizationAction {

	private Controller myController;
	private VisualTurtle turtle;
	private TestingState state;
	private boolean penDown = true;
	private boolean isShowing = true;
	private double scaleFactor = 1;

	public View(TestingState state) {
		this.state = state;
		setUp();
		addListeners();
	}

	private void addListeners() {
		state.getColorProperty().addListener((a, b, newValue) -> updateColor(newValue));
	}

	public void draw() {

	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		Pane newDisplay = new Pane();
		newDisplay.setStyle(state.getBackgroundColor());
		newDisplay.setPrefSize(DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		setPane(newDisplay);
		setTurtleImage(new Image(getClass().getClassLoader().getResourceAsStream("/slogo_team04/images/turtle.jpg")));
		add(turtle, getMaxWidth()/2, getMaxHeight()/2);
	}

	@Override
	public IState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object updateColor(Color newValue) {
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

	@Override
	public double forward(double pixels) {
		// TODO Auto-generated method stub
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		KeyValue kv = new KeyValue(turtle.xProperty(), turtle.xProperty().get() - pixels, Interpolator.LINEAR);
		KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		return 0;
	}

	public void scale(double scaleFactor) {
		this.scaleFactor = scaleFactor;
		turtle.setScaleX(getScaleFactor());
		turtle.setScaleY(getScaleFactor());
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	@Override
	public double back(double pixels) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double left(double degrees) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPenDown() {
		// TODO Auto-generated method stub
		return penDown;
	}

	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return isShowing;
	}

	@Override
	public void updateHistory(List<Action> history) {
		// TODO Auto-generated method stub

	}

	@Override
	public double right(double pixels) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setHeading(double degrees) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double towards(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setxy(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double penDown() {
		// TODO Auto-generated method stub
		penDown = true;
		return 1;
	}

	@Override
	public double penUp() {
		// TODO Auto-generated method stub
		penDown = false;
		return 0;
	}

	@Override
	public double showTurtle() {
		turtle.showTurtle();
		return 1;
	}

	@Override
	public double hideTurtle() {
		turtle.hideTurtle();
		return 0;
	}

	@Override
	public double home() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double clearScreen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double xCor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double yCor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double heading() {
		// TODO Auto-generated method stub
		return 0;
	}

}

