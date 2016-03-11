package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import frontend.features.TurtleShape;
import backend.slogo.team04.Actor;
import backend.structures.RGBColor;
import interfaces.slogo.team04.ISlogoModelActions;
import interfaces.slogo.team04.ISlogoModelActionsExtended;
import interfaces.slogo.team04.IView;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
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

public class newViewModel extends Observable implements IView, ISlogoModelActionsExtended {

	private static final double RGB_MAX = 255;
	private static final double RGB_INTERVAL = 255 / 2 + 1;
	private ListProperty<Actor> actors;
	private ListProperty<Actor> stamps;
	private boolean penIsDown;
	private boolean isShowing;
	private Stack<ModelLine> lineManager;
	private ColorProperty backgroundColor;
	private ColorProperty penColor;
	private ListProperty<RGBColor> colorListProperty;
	private ImageProperty currentActiveImage;
	private DoubleProperty currentPenWidth;
	private IntegerBinding numberOfTurtles;

	public newViewModel() {
		backgroundColor = new ColorProperty();
		penColor = new ColorProperty();
		currentPenWidth = new SimpleDoubleProperty();
		penIsDown = true;
		ObservableList<Actor> list = FXCollections.observableArrayList();
		actors = new SimpleListProperty<Actor>(list);
		addActor();
		lineManager = new Stack<ModelLine>();
		isShowing = true;
		generateColorListProperty();
		currentActiveImage = new ImageProperty();
		numberOfTurtles = Bindings.size(actors);
		addListeners(actors.get(actors.getSize()-1));
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


	@Override
	public ColorProperty getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public double clearScreen() {
		actors.stream().forEach((a) -> a.clearLines());
		return 0;
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

	public double setPenColor() {
		Actor activeActor = findActiveActor();
		RGBColor penColor = activeActor.getPen().getPenColor();
		return colorListProperty.indexOf(penColor);
	}

	public double setBackground(int index) {
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

	public double setPalette(int index, int r, int g, int b) {
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

	public Actor findActiveActor() {
//		ArrayList<Actor> activeActors = new ArrayList<Actor>();
//		actors.stream().filter((a) -> a.getActive().get()).forEach(activeActors::add);
		return getActiveActors().get(getActiveActors().size() - 1);
	}

	public double Shape() {
		Actor activeActors = findActiveActor();
		int listIndex = TurtleShape.valueOf(activeActors.getShape().toString()).ordinal();
		return listIndex + 1;
	}

	public double setShape(int index) {
		getActor(index).setShape(TurtleShape.values()[index - 1]);
		update();
		return (double) index;
	}

	public DoubleProperty getPenWidth() {
		return currentPenWidth;
	}

	public double setPenSize(double pixels) {
		currentPenWidth.set(pixels);
		return pixels;
	}

	public double Turtles() {
		return numberOfTurtles.get();
	}

	public void addToPalette(double r, double g, double b) {
		RGBColor newColor = new RGBColor(r, g, b, colorListProperty.size() + 1);
		colorListProperty.add(newColor);
	}

	public double stamp() {
		Actor lastActiveTurtle = findActiveActor();
		Actor stamp = new Actor(lastActiveTurtle.getXLocation(), lastActiveTurtle.getYLocation(),
				lastActiveTurtle.getHeading(), lastActiveTurtle.getPenDown() == 1);
		stamp.setStamp();
		stamps.add(stamp);
		return 0;
	}

	public double clearStamps() {
		int size = stamps.size();
		stamps.clear();
		return (size == 0) ? 0 : 1;
	}

	@Override
	public double forward(double pixels, double turtleID) {
		getActor(turtleID).forward(pixels);
		return pixels;
	}

	@Override
	public double back(double pixels, double turtleID) {
		getActor(turtleID).forward(-pixels);
		return pixels;
	}

	@Override
	public double left(double degrees, double turtleID) {
		getActor(turtleID).rotateCounterClockwise(degrees);
		return degrees;
	}

	@Override
	public double right(double degrees, double turtleID) {
		getActor(turtleID).rotateClockwise(degrees);
		return degrees;
	}

	@Override
	public double setHeading(double degrees, double turtleID) {
		if (actors.get(0) != null) {
			double oldHeading = actors.get(0).getHeading();
			getActor(turtleID).setHeading(degrees);
			actors.get(0).setHeading(degrees);
			return Angle.calculateAngleRotated(oldHeading, getActor(turtleID).getHeading());
		}
		return 0;
	}

	@Override
	public double towards(double x, double y, double turtleID) {
		double newHeading = Angle.calculateAngleBetweenPoints(
				getActor(turtleID).getXLocation(), getActor(turtleID).getYLocation(), x, y);
		getActor(turtleID).setHeading(newHeading);
		return 0;
	}

	@Override
	public double setxy(double x, double y, double turtleID) {
		if (actors.get(0) != null) {
			double oldX = actors.get(0).getXLocation();
			double oldY = actors.get(0).getYLocation();
			getActor(turtleID).setxy(x, y);
			return Distance.calculateDistance(oldX, oldY, x, y);
		}
		return 0;
	}

	@Override
	public double penDown(double turtleID) {
		penIsDown = true;
		getActor(turtleID).setPenDown(true);
		return 0;
	}

	@Override
	public double penUp(double turtleID) {
		penIsDown = false;
		getActor(turtleID).setPenDown(false);
		return 0;
	}

	@Override
	public double showTurtle(double turtleID) {
		getActor(turtleID).setShowing(true);
		return 1;
	}

	@Override
	public double hideTurtle(double turtleID) {
		getActor(turtleID).setShowing(false);
		return 0;
	}

	@Override
	public double home(double turtleID) {
		// TODO Auto-generated method stub
		return setxy(0, 0, turtleID);
	}

	@Override
	public double isPenDown(double turtleID) {
		// TODO Auto-generated method stub
		return getActor(turtleID).getPenDown();
	}

	@Override
	public double isShowing(double turtleID) {
		return (getActor(turtleID).getVisible()) ? 1 : 0;
	}

	@Override
	public double xCor(double turtleID) {
		return getActor(turtleID).getXLocation();
	}

	@Override
	public double yCor(double turtleID) {
		// TODO Auto-generated method stub
		return getActor(turtleID).getYLocation();
	}

	@Override
	public double heading(double turtleID) {
		return getActor(turtleID).getHeading();
	}

	
	public ArrayList<Actor> getActiveActors(){
		ArrayList<Actor> activeActors = new ArrayList<Actor>();
		actors.stream().filter((a) -> a.getActive().get()).forEach(activeActors::add);
		return activeActors;
	}
	
	@Override
	public boolean[] activeTurtles() {
		ArrayList<Boolean> activeTurtles = new ArrayList<Boolean>();
		actors.stream().forEach(a -> activeTurtles.add(a.getActive().getValue()));
		boolean output[] = new boolean[activeTurtles.size()];
		for (int i=0; i<output.length; i++){
			output[i] = activeTurtles.get(i);
		}
		return output;
	}


	@Override
	public double penColor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double shape() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double turtles() {
		return actors.size();
	}

	@Override
	public double tell(int[] arrayOfActiveTurtleIDs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pushCurrentActive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popCurrentActive() {
		// TODO Auto-generated method stub
		
	}
	
	public Actor getActor(double turtleID){
		return actors.get((int)turtleID);
	}

	@Override
	public double setPenColor(int index) {
		// TODO Auto-generated method stub
		return 0;
	}
}




