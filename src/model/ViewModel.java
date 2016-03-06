package model;

import java.util.List;
import java.util.Observable;
import java.util.Stack;

import backend.slogo.team04.Actor;
import interfaces.slogo.team04.ISlogoModelActions;
import interfaces.slogo.team04.IView;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties.ColorProperty;
import properties.ImageProperty;
import utilities.Angle;
import utilities.Distance;

public class ViewModel extends Observable implements IView, ISlogoModelActions {

	private ListProperty<Actor> actors;
	private Actor turtle;
	private boolean penIsDown;
	private boolean isShowing;
	private Stack<ModelLine> lineManager;
	private ColorProperty backgroundColor;
	private ColorProperty penColor;

	public ViewModel() {
		backgroundColor = new ColorProperty();
		penColor = new ColorProperty();
		penIsDown = true;
		turtle = new Actor(0, 0, Angle.HALF_CIRCLE/2, penIsDown);
		ObservableList<Actor> list = FXCollections.observableArrayList();
		actors = new SimpleListProperty<Actor>(list);
		actors.add(turtle);
		lineManager = new Stack<ModelLine>();
		isShowing = true;
		addListeners(turtle);
	}

	@Override
	public void addActor() {
	
		Actor newActor = new Actor(0, 0, Angle.HALF_CIRCLE/2, penIsDown);
		actors.add(newActor);
		addListeners(newActor);
		update();
		
	}

	private void addListeners(Actor actor) {
		actor.getImageProperty().addListener(e -> update());
	}

	
	@Override
	public double forward(double pixels) {

		actors.stream()
			.filter((a) -> a.getActive().get())
			.forEach((a) -> a.forward(pixels));
		return pixels;
	}

	private void addLine(ModelLine line) {
		if(line!=null) {
			lineManager.add(line);
		}
	}

	@Override
	public double back(double pixels) {
		
		for(Actor actor: actors) {
			if(actor.getActive().get()) {
				actor.forward(-pixels);
			}
		}
		return -pixels;
	}

	@Override

	public double left(double degrees) {
		turtle.rotateCounterClockwise(degrees);
		return degrees;
	}

	@Override
	public double right(double degrees) {
		turtle.rotateClockwise(degrees);
		return degrees;
	}

	@Override
	public double setHeading(double degrees) {

		double oldHeading = turtle.getHeading();
		turtle.setHeading(degrees);
		return Angle.calculateAngleRotated(oldHeading, turtle.getHeading());
	}

	@Override
	public double towards(double x, double y) {

		double newHeading = Angle.calculateAngleBetweenPoints(turtle.getXLocation(), turtle.getYLocation(), x, y);
		turtle.setHeading(newHeading);
		return 0;
	}

	@Override
	public double setxy(double x, double y) {
		double oldX = turtle.getXLocation();
		double oldY = turtle.getYLocation();
		addLine(turtle.setxy(x, y));
		return Distance.calculateDistance(oldX, oldY, x, y);
	}

	@Override
	public double penDown() {
		penIsDown = true;
		turtle.setPenDown(true);
		return 0;
	}

	@Override
	public double penUp() {
		penIsDown = false;
		turtle.setPenDown(false);
		return 0;
	}

	@Override
	public double showTurtle() {
		turtle.setShowing(true);
		return 1;
	}

	@Override
	public double hideTurtle() {
		turtle.setShowing(false);
		return 0;
	}

	@Override
	public double home() {
		double oldX = turtle.getXLocation();
		double oldY = turtle.getYLocation();
		addLine(turtle.setxy(0, 0));
		return Distance.calculateDistance(oldX, oldY, 0, 0);
	}

	@Override
	public double xCor() {
		return turtle.getXLocation();
	}

	@Override
	public double yCor() {
		return turtle.getYLocation();
	}

	@Override
	public double heading() {
		return turtle.getHeading();
	}

	@Override
	public double isShowing() {
		return (isShowing) ? 1 : 0;
	}

	@Override
	public ColorProperty getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public double clearScreen() {
		lineManager.clear();
		return 0;
	}

	@Override
	public double isPenDown() {
		return turtle.getPenDown();
	}

	@Override
	public ColorProperty getPenColor() {
		return penColor;
	}

	public List<ModelLine> getLines() {
		for(Actor actor: actors) {
			lineManager.addAll(actor.getMyLines());
		}
		return lineManager;
	}
	
	public Actor getActor() {
		return turtle;
	}

	@Override
	public void update() {
		setChanged();
		notifyObservers();
		
	}

	public ImageProperty getImageProperty() {
		return turtle.getImageProperty();
	}

	@Override
	public ListProperty<Actor> getActorProperty() {
		return actors;
	}
	
	public int setBackgroundColor(int index){
		for (RGBColor c:colorListProperty){
			if (c.getIndex() == index){
				backgroundColor.set(c);;
			}
		}
		return index;
	}

}

