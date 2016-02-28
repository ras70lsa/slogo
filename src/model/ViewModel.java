package model;

import backend_slogo_team04.Action;
import backend_slogo_team04.Actor;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import Utilities.Angle;
import Utilities.Distance;
import interfaces_slogo_team04.ISlogoModelActions;
import interfaces_slogo_team04.IView;
import properties.ColorProperty;

public class ViewModel extends Observable implements IView, ISlogoModelActions {

	private Actor turtle;
	private boolean penIsDown = true;
	private boolean isShowing = true;
	private Stack<ModelLine> lineManager;
	private ColorProperty backgroundColor;

	public ViewModel() {
		backgroundColor = new ColorProperty();
		turtle = new Actor(0, 0, Angle.FULL_CIRCLE/2);
		lineManager = new Stack<ModelLine>();
		penIsDown = true;
		isShowing = true;
	}

	@Override
	public double forward(double pixels) {

		double angle = turtle.getHeadingInRadians();
		turtle.setxy(turtle.getXLocation() + Math.sin(angle) * pixels,
				turtle.getYLocation() + Math.cos(angle) * pixels);
		addNewLineAndNotifyObservers(turtle.getXLocation(), turtle.getYLocation());
		return pixels;

	}

	@Override
	public double back(double pixels) {
		
		double angle = turtle.getHeadingInRadians() + Angle.HALF_CIRCLE;
		turtle.setxy(turtle.getXLocation() + Math.sin(angle) * pixels,
				turtle.getYLocation() + Math.cos(angle) * pixels);
		addNewLineAndNotifyObservers(turtle.getXLocation(), turtle.getYLocation());
		return pixels;
	}

	public void addNewLineAndNotifyObservers(double x, double y) {
		ModelLine newLine = new ModelLine(x, y);
		if (penIsDown) {
			lineManager.add(newLine);
		}

		setChanged();
		notifyObservers(newLine);
	}

	@Override

	public double left(double degrees) {
		turtle.rotateCounterClockwise(degrees);
		setChanged();
		notifyObservers();
		return degrees;
	}

	@Override
	public double right(double degrees) {
		turtle.rotateClockwise(degrees);

		setChanged();
		notifyObservers();
		return degrees;
	}

	@Override
	public double setHeading(double degrees) {

		double oldHeading = turtle.getHeading();
		turtle.setHeading(degrees);
		setChanged();
		notifyObservers();
		return Angle.calculateAngleRotated(oldHeading, turtle.getHeading());
	}

	@Override
	public double towards(double x, double y) {

		double newHeading = Angle.calculateAngleBetweenPoints(turtle.getXLocation(), turtle.getYLocation(), x, y);
		turtle.setHeading(newHeading);

		setChanged();
		notifyObservers();
		return 0;
	}

	@Override
	public double setxy(double x, double y) {
		double oldX = turtle.getXLocation();
		double oldY = turtle.getYLocation();
		turtle.setxy(x, y);
		addNewLineAndNotifyObservers(turtle.getXLocation(), turtle.getYLocation());
		return Distance.calculateDistance(oldX, oldY, x, y);
	}


	public double penDown() {
		penIsDown = true;
		setChanged();
		notifyObservers();
		return 1;
	}

	public double penUp() {

		penIsDown = false;
		setChanged();
		notifyObservers();
		return 0;
	}

	@Override
	public double showTurtle() {
		isShowing = true;
		setChanged();
		notifyObservers();
		return 1;
	}

	@Override
	public double hideTurtle() {
		isShowing = false;
		setChanged();
		notifyObservers();
		return 0;
	}

	@Override
	public double home() {
		double oldX = turtle.getXLocation();
		double oldY = turtle.getYLocation();
		turtle.setxy(0, 0);
		addNewLineAndNotifyObservers(turtle.getXLocation(), turtle.getYLocation());
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

	public double getHeading() {
		return turtle.getHeading();
	}

	@Override
	public ColorProperty getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public double clearScreen() {
		setChanged();
		HashSet<ModelLine> toBeDeleted = new HashSet<ModelLine>();
		while (!lineManager.isEmpty()) {
			ModelLine next = lineManager.pop();
			toBeDeleted.add(next);
		}
		notifyObservers(toBeDeleted);
		return 0;
	}

	@Override
	public List<Action> getHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double isPenDown() {
		return (penIsDown) ? 1 : 0;
	
	}

}

