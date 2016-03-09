package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import java.util.function.Consumer;

import frontend.features.TurtleShape;
import backend.slogo.team04.Actor;
import backend.structures.RGBColor;
import interfaces.slogo.team04.ISlogoModelActions;
import interfaces.slogo.team04.IView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties.ColorProperty;
import properties.ImageProperty;
import utilities.Angle;
import utilities.Distance;

public class ViewModel extends Observable implements IView, ISlogoModelActions {

	private static final double RGB_MAX = 255;
	private static final double RGB_INTERVAL = 255 / 2 + 1;
	private ListProperty<Actor> actors;
	private Actor turtle;
	private boolean penIsDown;
	private boolean isShowing;
	private Stack<ModelLine> lineManager;
	private ColorProperty backgroundColor;
	private ColorProperty penColor;
	private ListProperty<RGBColor> colorListProperty;
	private ImageProperty currentActiveImage;
	private DoubleProperty currentPenWidth;

	public ViewModel() {
		backgroundColor = new ColorProperty();
		penColor = new ColorProperty();
		currentPenWidth = new SimpleDoubleProperty();
		penIsDown = true;
		turtle = new Actor(0, 0, Angle.HALF_CIRCLE / 2, penIsDown);
		ObservableList<Actor> list = FXCollections.observableArrayList();
		actors = new SimpleListProperty<Actor>(list);
		actors.add(turtle);
		lineManager = new Stack<ModelLine>();
		isShowing = true;
		generateColorListProperty();
		currentActiveImage = new ImageProperty();
		addListeners(turtle);
	}

	@Override
	public void addActor() {

		Actor newActor = new Actor(0, 0, Angle.HALF_CIRCLE / 2, penIsDown);
		actors.add(newActor);
		addListeners(newActor);
		update();

	}

	private void addListeners(Actor actor) {
		currentActiveImage.addListener((z, b, c) -> {
			actors.stream().forEach((a) -> a.setImageProperty(c));
			update();
		});
		penColor.addListener((z, b, c) -> {
			actors.stream().forEach((a) -> a.setPenColor(c));
		});
		currentPenWidth.addListener((z, b, c) -> {
			actors.stream().forEach((a) -> a.setPenWidth(c.doubleValue()));
		});
	}

	public void alterActors(Consumer<Actor> action) {
		actors.stream().filter((a) -> a.getActive().get()).forEach(action);
	}

	@Override
	public double forward(double pixels) {
		alterActors((a) -> a.forward(pixels));
		return pixels;
	}

	@Override
	public double back(double pixels) {

		alterActors((a) -> a.forward(-pixels));
		return -pixels;
	}

	@Override

	public double left(double degrees) {
		alterActors((a) -> a.rotateCounterClockwise(degrees));
		return degrees;
	}

	@Override
	public double right(double degrees) {
		alterActors((a) -> a.rotateClockwise(degrees));
		return degrees;
	}

	@Override
	public double setHeading(double degrees) {

		if (actors.get(0) != null) {
			double oldHeading = actors.get(0).getHeading();
			alterActors((a) -> a.setHeading(degrees));
			actors.get(0).setHeading(degrees);
			return Angle.calculateAngleRotated(oldHeading, turtle.getHeading());
		}
		return 0;
	}

	@Override
	public double towards(double x, double y) {

		alterActors((a) -> {
			double newHeading = Angle.calculateAngleBetweenPoints(a.getXLocation(), a.getYLocation(), x, y);
			a.setHeading(newHeading);
		});
		return 0;
	}

	@Override
	public double setxy(double x, double y) {
		if (actors.get(0) != null) {
			double oldX = actors.get(0).getXLocation();
			double oldY = actors.get(0).getYLocation();
			alterActors((a) -> a.setxy(x, y));
			return Distance.calculateDistance(oldX, oldY, x, y);
		}

		return 0;
	}

	@Override
	public double penDown() {
		penIsDown = true;
		alterActors((a) -> a.setPenDown(true));
		return 0;
	}

	@Override
	public double penUp() {
		penIsDown = false;
		alterActors((a) -> a.setPenDown(false));
		return 0;
	}

	@Override
	public double showTurtle() {
		alterActors((a) -> a.setShowing(true));
		return 1;
	}

	@Override
	public double hideTurtle() {
		alterActors((a) -> a.setShowing(true));
		return 0;
	}

	@Override
	public double home() {
		return setxy(0, 0);
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
		lineManager.clear();
		for (Actor actor : actors) {
			lineManager.addAll(actor.getMyLines());
		}
		return lineManager;
	}

	@Override
	public void update() {
		setChanged();
		notifyObservers();

	}

	public ImageProperty getImageProperty() {
		return currentActiveImage;
	}

	@Override
	public ListProperty<Actor> getActorProperty() {
		return actors;
	}

	public void generateColorListProperty() {
		colorListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
		int index = 1;
		for (int r = 0; r < RGB_MAX; r += RGB_INTERVAL) {
			for (int g = 0; g < RGB_MAX; g += RGB_INTERVAL) {
				for (int b = 0; b < RGB_MAX; b += RGB_INTERVAL) {
					colorListProperty.add(new RGBColor(r / RGB_MAX, g / RGB_MAX, b / RGB_MAX, index));
					index++;
				}
			}
		}
	}

	public ListProperty<RGBColor> getColorListProperty() {
		return colorListProperty;
	}

	public int PenColor() {
		ArrayList<Actor> activeActors = findActiveActors();
		RGBColor penColor = activeActors.get(activeActors.size() - 1).getPen().getPenColor();
		return colorListProperty.indexOf(penColor);
	}

	public int setBackgroundColor(int index) {
		for (RGBColor c : colorListProperty) {
			if (c.getIndex() == index) {
				backgroundColor.set(c);
			}
		}
		return index;
	}

	// Good place for reflection
	@Override
	public void setPenStyle(String selectedItem) {

		actors.stream().forEach((a) -> a.setPenStyle(selectedItem));

	}

	public int setPalette(int index, int r, int g, int b) {
		boolean hasColor = false;
		for (RGBColor c : colorListProperty) {
			if (c.getRed() == r / RGB_MAX && c.getGreen() == g / RGB_MAX && c.getBlue() == b / RGB_MAX) {
				c.setIndex(index);
				hasColor = true;
			}
		}

		for (RGBColor c : colorListProperty) {
			if (c.getIndex() == index) {
				if (hasColor) {

				}
			}
		}

		colorListProperty.sorted();
		return index;

	}

	public ArrayList<Actor> findActiveActors() {
		ArrayList<Actor> activeActors = new ArrayList<Actor>();
		actors.stream().filter((a) -> a.getActive().get()).forEach(activeActors::add);
		;
		return activeActors;
	}

	public int Shape() {
		ArrayList<Actor> activeActors = findActiveActors();
		int listIndex = TurtleShape.valueOf(activeActors.get(activeActors.size() - 1).getShape().toString()).ordinal();
		return listIndex + 1;
	}

	public int setShape(int index) {
		alterActors((a) -> a.setShape(TurtleShape.values()[index - 1]));
		return index;
	}

	public DoubleProperty getPenWidth() {
		return currentPenWidth;
	}

	public int setPenSize(int pixels) {
		currentPenWidth.set(pixels);
		return pixels;
	}

}


