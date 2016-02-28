package frontend_features;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import constants.DisplayConstants;
import frontend_slogo_team04.State;
import frontend_slogo_team04.VisualTurtle;
import interfaces_slogo_team04.IView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import visual_states.GuiUserOption;
import visual_states.ViewUIState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Model;
import model.ViewModel;

public class View extends StaticPane implements Observer {

	private VisualTurtle turtle;
	private double scaleFactor = 1;
	private ViewUIState visuals;
	private IView model;
	private Pen pen;
	private Map<ModelLine, Line> lineManager;
	private static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE/2;


	public View(IView model) {
		turtle = new VisualTurtle(getFirstImage());
		this.model = model;
		addCSS("visual_resources/DefaultView.css");
		visuals = new ViewUIState();
		lineManager = new Stack<Line>();
		pen = new Pen(Color.BLACK);
		lineManager = new HashMap<ModelLine, Line>();
		addSilentListeners();
		model.addObserver(this);
		setUp();
	}

	private Image getFirstImage() {
		return new Image(getClass().getClassLoader().getResourceAsStream("visual_resources/turtle.jpg"));
	}

	private void addSilentListeners() {
		visuals.getImageProperty().addListener((a, b, c) -> setTurtleImage(c));
		model.getBackgroundColor().addListener((a,b,c) -> updateColor(c));

	}

	public void setUp() {
		add(turtle, getCenterXCor(turtle.getFitWidth()), getCenterYCor(turtle.getFitHeight()));
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
	
	public double getCenterXCor(double imageWidth) {
		return DisplayConstants.VIEW_WIDTH / 2 - (imageWidth / 2);
	}

	public double getCenterYCor(double imageHeight) {
		return DisplayConstants.VIEW_HEIGHT / 2 - (imageHeight / 2);

	}

	
	protected List<Node> getReleventProperties(GuiUserOption factory) {
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(visuals.getImageProperty(), "Choose Actor Image"));
		list.add(factory.get(model.getBackgroundColor()));
		return list;
	}

	@Override
	public void update(Observable o, Object arg) {
		double modelX = model.xCor();
		double modelY = model.yCor();
		if (arg instanceof Collection){
			Collection toBeRemoved = (Collection) arg;
			toBeRemoved.forEach(l -> remove(lineManager.get(l)));
		}
		
		if (arg instanceof ModelLine){
			ModelLine modelLine = (ModelLine) arg;
			Line newLine = drawLine(turtle.getTranslateX(), turtle.getTranslateX(), translateToLineX(modelX),
					translateToLineY(modelY));
			lineManager.put(modelLine,newLine);
		}
		
		if (model.isShowing() == 1) {
			showTurtle();
		} else {
			hideTurtle();
		}
		turn(translateToTurtleAngle(model.getHeading()));
		moveTurtle(translateToTurtleX(modelX), translateToTurtleY(modelY));
	}

	public Line drawLine(double startX, double startY, double endX, double endY) {
		Line newLine = pen.createLine(startX, startY, endX, endY);
		add(newLine, startX, startY);
		return newLine;
	}

	public double home() {
		moveTurtle(getCenterXCor(turtle.getFitWidth()), getCenterYCor(turtle.getFitHeight()));
		return 0;
	}

	public double clearScreen() {
		getLineManager().forEach(l -> l.setVisible(false));
		getLineManager().clear();
		resetTurtlePosition();
		return 0;
	}

	public void resetTurtlePosition() {
		turtle.setTranslateX(getCenterXCor(turtle.getFitWidth()));
		turtle.setTranslateY(getCenterYCor(turtle.getFitHeight()));
	}

	public void moveTurtle(double endX, double endY) {
		turtle.setTranslateX(endX);
		turtle.setTranslateY(endY);
	}

	public void turn(double heading) {
		turtle.setRotate(heaing);
	}

	private double translateToLineX(double xCor) {
		return xCor + getCenterXCor(turtle.getFitHeight());
	}

	public double translateToLineY(double yCor) {
		return getCenterYCor(turtle.getFitHeight()) - yCor;
	}

	public double translateToTurtleX(double xCor) {
		return xCor;
	}

	public double translateToTurtleY(double yCor) {
		return -yCor;
	}

	public static void main(String[] args) {

	}
	
	public double translateToTurtleAngle(double angle){
		return -(angle - TURTLE_INITIAL_ANGLE);
	}
	
	public void hideTurtle(){
		turtle.setVisible(false);
	}
	
	public void showTurtle(){
		turtle.setVisible(true);
	}
	
	public void getOptions() {
		
		List<Node> properties = getReleventProperties(new GuiUserOption());
		Stage stage = getEmptyStage();
		VBox box= new VBox();
		Group myGroup = (Group) stage.getScene().getRoot();
		myGroup.getChildren().add(box);
		for(Node node: properties) {
			box.getChildren().add(node);
		}
		stage.show();
		
	}
}

