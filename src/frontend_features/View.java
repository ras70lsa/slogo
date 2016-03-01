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
import javafx.scene.image.ImageView;
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

	private double scaleFactor = 1;
	private ViewUIState visuals;
	private IView model;
	private Pen pen;
	private static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE/2;


	public View(IView model) {
		this.model = model;
		addCSS("visual_resources/DefaultView.css");
		visuals = new ViewUIState();	
		pen = new Pen(Color.BLACK);
		addSilentListeners();
		model.addObserver(this);
		
	}

	private void addSilentListeners() {
		model.getBackgroundColor().addListener((a,b,c) -> updateColor(c));
		model.getPenColor().addListener((a,b,c) -> updatePenColor(c)); 

	}

	private void updatePenColor(RGBColor c) {
		pen.setPenColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 1.0));
		
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
	
	protected List<Node> getReleventProperties(GuiUserOption factory) {
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(visuals.getImageProperty(), "Choose Actor Image"));
		list.add(factory.get(model.getBackgroundColor(), "Background Color"));
		list.add(factory.get(model.getPenColor(), "Pen Color"));
		return list;
	}

	@Override
	public void update(Observable o, Object arg) {
		clear();
		if(model.getActor().getVisible()) {
			draw(model.getActor());
		}
		for(ModelLine line: model.getLines()) {
			draw(line);
		}
		
	}
	
	private void draw(Actor turtle) {
		
		ImageView view = new ImageView(turtle.getImage());
		view.setFitWidth(50);
		view.setFitHeight(50);
		view.setTranslateX(makeXCorrection(turtle.getXLocation()) - view.getFitWidth()/ 2);
		view.setTranslateY(makeYCorrection(turtle.getYLocation()) - view.getFitHeight()/2);
		view.setRotate(translateToTurtleAngle(turtle.getHeading()));
		add(view);
//		moveTurtle(translateToTurtleX(turtle.getXLocation()), translateToTurtleY(turtle.getYLocation()));
//		turn(translateToTurtleAngle(turtle.getHeading()));
	}
	
	private void draw(ModelLine line) {
		drawLine(makeXCorrection(line.getStartX()), 
				makeYCorrection(line.getStartY()), 
				makeXCorrection(line.getEndX()), 
				makeYCorrection(line.getEndY()));
	}

	private double makeXCorrection(double x) {
		
		return x + DisplayConstants.VIEW_WIDTH /2;
	}
	
	private double makeYCorrection(double y) {
		
		return DisplayConstants.VIEW_HEIGHT /2 - y; 
	}

	public Line drawLine(double startX, double startY, double endX, double endY) {
		Line n = new Line();
		n.setStroke(pen.getPenColor());
		addLine(n ,startX, startY, endX, endY);
		return n;
	}
	
	public double translateToTurtleAngle(double angle){
		return -(angle - TURTLE_INITIAL_ANGLE);
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

