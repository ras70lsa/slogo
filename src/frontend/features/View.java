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
	private static final double DEFAULT = 1;
	private IView model;
	private WrapAroundDrawLine wrapAroundFunction;
	private WindowDrawLine windowDrawFunction;
	private FenceDrawLine fenceDrawFunction;
	private double num;
	private static final double ACTOR_WIDTH = 50;
	private static final double ACTOR_HEIGHT = 60;
	
	private static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE / 2;

	public View(IView model) {
		this.model = model;
		addCSS(CSSPathConstants.DEFAULT_VIEW);
		addSilentListeners();
		model.addObserver(this);
		wrapAroundFunction = new WrapAroundDrawLine();
		windowDrawFunction = new WindowDrawLine();
		fenceDrawFunction = new FenceDrawLine();
		this.num = DEFAULT;
	}
	
	public IMyDrawer determineFunction(){
		this.num = model.getFunction();
		if(num==1){
			return wrapAroundFunction;
		}else if(num==2){
			return windowDrawFunction;
		}else{
			return fenceDrawFunction;
		}
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
		drawLines(determineFunction().getLines());
		
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
		makeCorrection(view, turtle);
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
		determineFunction().clearLines();
	}
	
	private void translateLineToView(List<ModelLine> input){
		for(ModelLine line: input) {
			determineFunction().draw(line);
		}
	}
	
	private void makeCorrection(ImageView view, Actor turtle){
		view.setTranslateX(determineFunction().makeXCorrection(turtle.getXLocation()) - view.getFitWidth()/2);
		view.setTranslateY(determineFunction().makeYCorrection(turtle.getYLocation()) - view.getFitHeight()/2);
	}
	public double translateToTurtleAngle(double angle) {
		return -(angle - TURTLE_INITIAL_ANGLE);
	}
	

}

