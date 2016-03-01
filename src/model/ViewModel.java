package model;

import backend_slogo_team04.Actor;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import Utilities.Angle;
import Utilities.Distance;
import interfaces_slogo_team04.ISlogoModelActions;
import interfaces_slogo_team04.IView;
import properties.ColorProperty;
import properties.ImageProperty;

public class ViewModel extends Observable implements IView, ISlogoModelActions {

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
		lineManager = new Stack<ModelLine>();
		isShowing = true;
		addListeners();
	}

	private void addListeners() {
		turtle.getImageProperty().addListener(e -> update());
	}

	@Override
	public double forward(double pixels) {

		ModelLine line = turtle.forward(pixels);
		addLine(line);
		return pixels;
	}

	private void addLine(ModelLine line) {
		if(line!=null) {
			lineManager.add(line);
		}
	}

	@Override
	public double back(double pixels) {
		return forward(-pixels);
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


	public double penDown() {
		penIsDown = true;
		turtle.setPenDown(true);
		return 0;
	}

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

	public double getHeading() {
		return turtle.getHeading();
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

	
//	public static void main(String[] args) {
//		ViewModel test = new ViewModel();
//		test.forward(20);
//		test.back(20);
//		System.out.println(test.xCor());
//		System.out.println(test.yCor());
//	}

	@Override
	public ColorProperty getPenColor() {
		return penColor;
	}

	public List<ModelLine> getLines() {
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

	@Override
	public ImageProperty getImageProperty() {
		return turtle.getImageProperty();
	}
}

