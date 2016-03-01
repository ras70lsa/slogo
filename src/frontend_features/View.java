package frontend_features;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import Utilities.Angle;
import backend_slogo_team04.Actor;
import backend_structures.RGBColor;
import constants.DisplayConstants;
import frontend_slogo_team04.State;
import frontend_slogo_team04.VisualTurtle;
import interfaces_slogo_team04.IView;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import visual_states.GuiUserOption;
import visual_states.ViewUIState;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Model;
import model.ModelLine;
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
		model.getPenColor().addListener((a,b,c) -> updatePenColor(c)); 

	}

	private void updatePenColor(RGBColor c) {
		pen.setPenColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 1.0));
		
	}

	public void setUp() {
		add(turtle, getCenterXCor(turtle.getFitWidth()), getCenterYCor(turtle.getFitHeight()));
//		System.out.println("Center X: " + getCenterXCor(turtle.getFitWidth()));
//		System.out.println("Center Y: " + getCenterYCor(turtle.getFitHeight()));
	}

	private void setTurtleImage(Image i) {
		turtle.setImage(i);
	}

	private void setPenColor(Color c){
		pen.setPenColor(c);
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
		list.add(factory.get(model.getBackgroundColor(), "Background Color"));
		list.add(factory.get(model.getPenColor(), "Pen Color"));
		return list;
	}

	@Override
	public void update(Observable o, Object arg) {
		for(ModelLine line: model.getLines()) {
			draw(line);
		}
		draw(model.getActor());
	}
	
	private void draw(Actor turtle) {
		System.out.println("this is a turle");
	}
	
	private void draw(ModelLine line) {
		System.out.println("this is a line");
	}

	public Line drawLine(double startX, double startY, double endX, double endY) {
		Line n = new Line();
		n.setStroke(pen.getPenColor());
		addLine(n ,startX, startY, endX, endY);
		return n;
	}

	public double home() {
		moveTurtle(getCenterXCor(turtle.getFitWidth()), getCenterYCor(turtle.getFitHeight()));
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
		turtle.setRotate(heading);
	}

	private double translateToLineX(double xCor) {
		return xCor + getCenterXCor(turtle.getFitHeight()) + turtle.getFitWidth()/2;
	}

	public double translateToLineY(double yCor) {
		return getCenterYCor(turtle.getFitHeight()) - yCor + turtle.getFitHeight()/2;
	}
	
	public double translateToTurtleX(double xCor) {
		return xCor + getCenterXCor(turtle.getFitWidth());
	}

	public double translateToTurtleY(double yCor) {
		return getCenterYCor(turtle.getFitHeight()) - yCor;
	}
	
	private double adjustInitialPointX(double xCor){
		return xCor + turtle.getFitWidth()/2;
	}
	
	private double adjustInitialPointY(double yCor){
		return yCor + turtle.getFitHeight()/2;
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

