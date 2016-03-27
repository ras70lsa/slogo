// This entire file is part of my masterpiece.
// Alexander Tseng

/* This class translates the current position of the actor in the backend into the front end. It is well refactored such that the
code is pretty readiable and its function is very specific such that the methods here can not be placed anywhere else. It is easily
extendible for front end features and closed for backend commands. The wraparound class has been refactored out of this class to make
it more extendible such that the user will be able to add extra border cases or any changes related to the display of the turtle 
environment.
*/

package frontend.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import backend.slogo.team04.Actor;
import constants.CSSPathConstants;
import constants.DisplayConstants;
import interfaces.slogo.team04.IView;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import model.ModelLine;
import utilities.Angle;
import visual.states.GuiUserOption;

public class View extends StaticPane implements Observer {

	private static final double scaleFactor = 1;
	private static final double DARKEN_FACTOR = -.75;
	private static final double FADE_FACTOR = .5;
	private IView model;
	private WrapAroundDrawLine wrapAroundFunction;
	private static final double ACTOR_WIDTH = 50;
	private static final double ACTOR_HEIGHT = 60;

	private static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE / 2;

	public View(IView model) {
		this.model = model;
		addCSS(CSSPathConstants.DEFAULT_VIEW);
		addSilentListeners();
		model.addObserver(this);
		wrapAroundFunction = new WrapAroundDrawLine();
	}

	private void addSilentListeners() {
		model.getBackgroundColor().addListener((a,b,c) -> updateColor(c));
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

	public List<Node> getReleventProperties() {
		GuiUserOption factory = new GuiUserOption();
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(model.getImageProperty(), "Choose Actor Image"));
		list.add(factory.get(model.getBackgroundColor(), model.getColorListProperty(), "Background Color"));
		list.add(factory.get(model.getPenColor(), model.getColorListProperty(), "Pen Color"));
		return list;
	}

	@Override
	public void update(Observable o, Object arg) {
		clear();
	
		for (Actor actor: model.getStamps()){
			draw(actor);
		}
		
		translateLineToView(model.getLines());
		drawLines(wrapAroundFunction.getLines());
		
		for(Actor actor: model.getActorProperty()) {
			if(actor.getVisible()) {
				draw(actor);
			}
		}
	}
	
	private void draw(Actor turtle) {
		
		ImageView view = new ImageView(turtle.getImage());
		view.setOnMouseClicked(e -> turtle.toggleActive());
		turtle.getActive().addListener((a,b,current) -> handleImage(view, current));
		view.setFitWidth(ACTOR_WIDTH);
		view.setFitHeight(ACTOR_HEIGHT);
		view.setTranslateX(makeXCorrection(turtle.getXLocation()) - view.getFitWidth()/2);
		view.setTranslateY(makeYCorrection(turtle.getYLocation()) - view.getFitHeight()/2);
		view.setRotate(translateToTurtleAngle(turtle.getHeading()));
		add(view);
		handleImage(view, turtle.getActive().get());
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
	
	private void drawLines(ArrayList<Line> input){
		for(Line line: input){
			addLine(line);
		}
		wrapAroundFunction.clearLines();
	}
	
	private void translateLineToView(List<ModelLine> input){
		for(ModelLine line: input) {
			wrapAroundFunction.draw(line);
		}
	}
	
	private double makeXCorrection(double x) {
		x = x - DisplayConstants.VIEW_WIDTH/2;
		double xd = Math.abs(x)%(DisplayConstants.VIEW_WIDTH);
		if(x<0){
			return DisplayConstants.VIEW_WIDTH-xd;
		}
		else if(x>DisplayConstants.VIEW_WIDTH){
			return xd;
		}
		return x;
	}

	private double makeYCorrection(double y) {
		y = -y - DisplayConstants.VIEW_HEIGHT/2;
		double yd = Math.abs(y)%(DisplayConstants.VIEW_HEIGHT);
		if(y<0){
			return DisplayConstants.VIEW_HEIGHT - yd;
		}
		else if(y>DisplayConstants.VIEW_HEIGHT){
			return yd;
		}
		return y;
	}

	public double translateToTurtleAngle(double angle) {
		return -(angle - TURTLE_INITIAL_ANGLE);
	}
	

}

