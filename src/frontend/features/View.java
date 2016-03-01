package frontend.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import Utilities.Angle;
import backend.slogo.team04.Actor;
import backend.structures.RGBColor;
import constants.DisplayConstants;
import frontend.slogo.team04.State;
import frontend.slogo.team04.VisualTurtle;
import interfaces.slogo.team04.IView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import visual_states.GuiUserOption;
import visual_states.ViewUIState;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.ModelLine;

public class View extends StaticPane implements Observer {

	private double scaleFactor = 1;
	private ViewUIState visuals;
	private IView model;
	private Pen pen;

	
	public static final double ACTOR_WIDTH = 50;
	public static final double ACTOR_HEIGHT = 60;

	private static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE / 2;

	public View(IView model) {
		this.model = model;
		addCSS("visual_resources/DefaultView.css");
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
	
	public double getMaxWidth() {
		return DisplayConstants.VIEW_WIDTH;
	}

	public double getMaxHeight() {
		return DisplayConstants.VIEW_HEIGHT;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public double getCenterXCor(double imageWidth) {
		return DisplayConstants.VIEW_WIDTH / 2 - (imageWidth / 2);
	}

	public double getCenterYCor(double imageHeight) {
		return DisplayConstants.VIEW_HEIGHT / 2 - (imageHeight / 2);

	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(model.getImageProperty(), "Choose Actor Image"));
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
		
		ImageView view = new ImageView(turtle.getImageProperty().get());
		view.setFitWidth(ACTOR_WIDTH);
		view.setFitHeight(ACTOR_HEIGHT);
		view.setTranslateX(makeXCorrection(turtle.getXLocation()) - view.getFitWidth()/ 2);
		view.setTranslateY(makeYCorrection(turtle.getYLocation()) - view.getFitHeight()/2);
		view.setRotate(translateToTurtleAngle(turtle.getHeading()));
		add(view);
	}
	
	private void draw(ModelLine line) {
		drawLine(makeXCorrection(line.getStartX()), 
				makeYCorrection(line.getStartY()), 
				makeXCorrection(line.getEndX()), 
				makeYCorrection(line.getEndY()));
	}

	public Line drawLine(double startX, double startY, double endX, double endY) {
		Line n = new Line();
		n.setStroke(pen.getPenColor());
		addLine(n, startX, startY, endX, endY);
		return n;
	}

	private double makeXCorrection(double x) {
		
		return x + DisplayConstants.VIEW_WIDTH /2;
	}
	private double makeYCorrection(double y) {
		
		return DisplayConstants.VIEW_HEIGHT /2 - y; 
	}

	public double translateToTurtleAngle(double angle) {
		return -(angle - TURTLE_INITIAL_ANGLE);
	}

	public void getOptions() {

		List<Node> properties = getReleventProperties(new GuiUserOption());
		Stage stage = getEmptyStage();
		VBox box = new VBox();
		Group myGroup = (Group) stage.getScene().getRoot();
		myGroup.getChildren().add(box);
		for (Node node : properties) {
			box.getChildren().add(node);
		}
		stage.show();

	}
}

