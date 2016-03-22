package drawing;

import java.util.Collection;
import backend.slogo.team04.Actor;
import constants.DisplayConstants;
import frontend.features.WrapAroundDrawLine;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.ModelLine;
import utilities.Angle;

public class InstantaneousDrawing implements IDrawingAlgorithm {

	public static final double ACTOR_WIDTH = 50;
	public static final double ACTOR_HEIGHT = 60;
	
	public static final int X_INDEX = 0;
	public static final int Y_INDEX = 1;
	public static final int DIMENSIONS = 2;
	
	private static final double DARKEN_FACTOR = -.75;
	private static final double FADE_FACTOR = .5;
	private static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE / 2;
	
	private Pane pane;
	private IDrawingBoundaryConditions wrapAroundFunction;

	
	public InstantaneousDrawing(Pane pane) {
		this.pane = pane;
		wrapAroundFunction = new WrapAroundDrawLine();
	}
	
	@Override
	public void draw(Actor turtle) {
		
		if(!turtle.getVisible()) {
			return;
		}
		ImageView view = turtleToImageView(turtle);
		double[] cordinates = getPlacement(turtle, view); 
		add(view, cordinates[X_INDEX], cordinates[Y_INDEX]);
		turtle.getMyLines().stream().forEach((line) -> draw(line)); 
		
	}
	
	private double[] getPlacement(Actor turtle, ImageView view) {
		double [] xy = new double[DIMENSIONS];
		xy[X_INDEX]= correct(turtle.getXLocation(), DisplayConstants.VIEW_WIDTH) - view.getFitWidth()/2;
		xy[Y_INDEX]= correct(-turtle.getYLocation(), DisplayConstants.VIEW_HEIGHT) - view.getFitHeight()/2;
		return xy;
	}
	
	private double correct(double loc, double size) {
		loc = loc - size/2;
		double xd = Math.abs(loc)%(size);
		if(loc<0){
			return size-xd;
		}
		else if(loc>size){
			return xd;
		}
		return loc;
	}

	private ImageView turtleToImageView(Actor turtle) {
		ImageView view = new ImageView(turtle.getImage());
		view.setOnMouseClicked(e -> turtle.toggleActive());
		resize(view);
		view.setRotate(translateToTurtleAngle(turtle.getHeading()));
		turtle.getActive().addListener((a,b,current) -> handleImage(view, current));
		handleImage(view, turtle.getActive().get());
		return view;
	}

	private void resize(ImageView view) {
		view.setFitWidth(ACTOR_WIDTH);
		view.setFitHeight(ACTOR_HEIGHT);	
	}

	@Override
	public void draw(ModelLine line) {
		addAll(wrapAroundFunction.draw(line));
	}
	
	public double translateToTurtleAngle(double angle) {
		return -(angle - TURTLE_INITIAL_ANGLE);
	}
	
	private void handleImage(ImageView view, Boolean active) {
		ColorAdjust adjust = new ColorAdjust();
		if(!active) {
			adjust.setBrightness(DARKEN_FACTOR);
			view.setOpacity(FADE_FACTOR);
		} else {
			adjust.setBrightness(0);
			view.setOpacity(1);
		}
		view.setEffect(adjust);
	}
	
	
	public void add(Node node, double x, double y) {
		node.setTranslateX(x);
		node.setTranslateY(y);
		pane.getChildren().add(node);
	}
	
	public void addAll(Collection<Node> list) {
		pane.getChildren().addAll(list);
	}

}
