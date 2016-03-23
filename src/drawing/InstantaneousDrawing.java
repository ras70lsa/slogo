//This entire file is part of my masterpiece 
//Ryan St.Pierre

/**
 * This code handles Instantaneous Drawing (non-animation).  For discussion on why the responsibility of drawing was 
 * moved to this class reference the comments at the top of ActorView.java
 * 
 * Most of the logic found in this class is simply moved from code previously contained in the View.java class 
 * https://github.com/duke-compsci308-spring2016/slogo_team04/blob/master/src/frontend/features/View.java
 * 
 * For my masterpiece I did refactor this drawing logic. This included writing generic coordinate correction methods for x 
 * and y variables to remove duplicated code, setting defined constants, and incorporating functional programming.
 * I added the enum class ImageViewStyle as a way to better hold the dimming and fade constants in a cohesive unit.  
 * This enum class could be added upon to include more styles.
 */

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

	public static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE / 2;
	public static final int X_INDEX = 0;
	public static final int Y_INDEX = 1;
	public static final int DIMENSIONS = 2;
	
	/**
	 * If desired these values could be moved to a properties file to allow the user to change the size of the 
	 * actors being drawn.  However, the rest of our design assumes static display sizes that cannot be changed by the user.
	 * Thus, to be consistent I will keep these values constants in my masterpiece.  However, under a full refactor of the 
	 * project and not just one component I would allow for the user to set view and view component sizes
	 */
	public static final double ACTOR_WIDTH = 50;
	public static final double ACTOR_HEIGHT = 60;
	
	/**The Pane in which to draw*/
	private Pane pane;
	/**Defines how to handle boundary conditions (when the turtle goes off the view*/
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
		double[] coordinates = getPlacement(turtle, view); 
		add(view, coordinates[X_INDEX], coordinates[Y_INDEX]);
		turtle.getMyLines().stream().forEach((line) -> draw(line)); 
		
	}
	
	private double[] getPlacement(Actor turtle, ImageView view) {
		double [] xy = new double[DIMENSIONS];
		xy[X_INDEX]= correct(turtle.getXLocation(), DisplayConstants.VIEW_WIDTH) - view.getFitWidth()/2;
		xy[Y_INDEX]= correct(-turtle.getYLocation(), DisplayConstants.VIEW_HEIGHT) - view.getFitHeight()/2;
		return xy;
	}
	
	/**
	 * Algorithm to correct model coordinate to view
	 */
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

	/**
	 * @param Actor turtle
	 * @return an ImageView to represent the Actor in the Pane
	 */
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
	
	public void add(Node node, double x, double y) {
		node.setTranslateX(x);
		node.setTranslateY(y);
		pane.getChildren().add(node);
	}
	
	public void addAll(Collection<Node> list) {
		pane.getChildren().addAll(list);
	}
	
	/**
	 * Defines how an ImageView can appear
	 * @author RyanStPierre
	 */
	public enum ImageViewStyle {
		
		DIMMED(.5, -.75),
		NORMAL(1, 0);
		private double fade;
		private double darken;
		
		ImageViewStyle(double fade, double darken) {
			this.fade = fade;
			this.darken = darken;
		}
		
		public double getFade() {return fade;}
		public double getDarken() {return darken;}
	}
	
	/**
	 * Given a boolean value sets the ImageView to normal or dimmed
	 * @param ImageView view
	 * @param boolean active
	 */
	private void handleImage(ImageView view, boolean active) {
		ColorAdjust adjust = new ColorAdjust();
		if(!active) {
			adjust.setBrightness(ImageViewStyle.DIMMED.getDarken());
			view.setOpacity(ImageViewStyle.DIMMED.getFade());
		} else {
			adjust.setBrightness(ImageViewStyle.NORMAL.getDarken());
			view.setOpacity(ImageViewStyle.NORMAL.getFade());
		}
		view.setEffect(adjust);
	}
	

}
