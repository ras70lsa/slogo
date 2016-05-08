package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

import frontend.features.TurtleShape;
import backend.slogo.team04.Actor;
import backend.slogo.team04.Actors;
import backend.structures.RGBColor;
import exceptions.PaletteException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;
import interfaces.slogo.team04.IView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import properties.ColorProperty;
import properties.ImageProperty;
import utilities.Angle;
import utilities.Distance;

public class ViewModel extends Observable implements IView, ISlogoModelActionsExtended {

	private static final double RGB_MAX = 255;
	private static final double RGB_INTERVAL = 255 / 2 + 1;
	private static final Boolean TELL_CMD_TURTLE_ACTIVE_DEFAULT = Boolean.FALSE;
	private static final Boolean TURTLE_CREATED_ACTIVE_DEFAULT = Boolean.TRUE;

	private Actors actors;
	private List<Actor> stamps;
	private Stack<ModelLine> lineManager;
	private ColorProperty backgroundColor;
	private ColorProperty penColor;
	private List<Boolean> toBeActive;
	private Stack<List<Boolean>> myCachedActiveTurtles;
	private ListProperty<RGBColor> colorListProperty;
	private ImageProperty currentActiveImage;
	private DoubleProperty currentPenWidth;
	private double num;

	public ViewModel() {
		actors = new Actors();
		backgroundColor = new ColorProperty();
		currentActiveImage = new ImageProperty();
		penColor = new ColorProperty();
		currentPenWidth = new SimpleDoubleProperty();
		addActor(TURTLE_CREATED_ACTIVE_DEFAULT);
		stamps = new ArrayList<Actor>();
		lineManager = new Stack<ModelLine>();
		generateColorListProperty();
		addListeners(actors.getActors().get(actors.getSize() - 1));
		toBeActive = new ArrayList<>();
		myCachedActiveTurtles = new Stack<>();
		num = 1;
	}

	@Override
	public void addActor(boolean visible) {
		Actor newActor = new Actor(0, 0, Angle.HALF_CIRCLE / 2, visible, actors.getSize());
		actors.addActor(newActor);
		addListeners(newActor);
		update();
	}

	private void addListeners(Actor actor) {
		currentActiveImage.addListener((z, b, c) -> {
			actors.actOnEachElement((a) -> a.setImageProperty(c));
			update();
		});
		penColor.addListener((z, b, c) -> {
			actors.actOnEachElement((a) -> a.setPenColor(c));
		});
		currentPenWidth.addListener((z, b, c) -> {
			actors.actOnEachElement((a) -> a.setPenWidth(c.doubleValue()));
		});
	}

	@Override
	public ColorProperty getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public double clearScreen() {
		actors.actOnEachElement((a) -> a.clearLines());
		actors.clear();
		return 0;
	}

	@Override
	public ColorProperty getPenColor() {
		return penColor;
	}

	@Override
	public List<ModelLine> getLines() {
		lineManager.clear();
		lineManager.addAll(actors.getLines());
		return lineManager;
	}

	@Override
	public void update() {
		setChanged();
		notifyObservers();

	}

	@Override
	public ImageProperty getImageProperty() {
		return currentActiveImage;
	}

	@Override
	public ListProperty<Actor> getActorProperty() {
		return actors.getActors();
	}

	public void generateColorListProperty() {
		colorListProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
		int index = 0;
		for (int r = 0; r < RGB_MAX; r += RGB_INTERVAL) {
			for (int g = 0; g < RGB_MAX; g += RGB_INTERVAL) {
				for (int b = 0; b < RGB_MAX; b += RGB_INTERVAL) {
					colorListProperty.add(new RGBColor(r / RGB_MAX, g / RGB_MAX, b / RGB_MAX, index));
					index++;
				}
			}
		}
	}

	@Override
	public ListProperty<RGBColor> getColorListProperty() {
		return colorListProperty;
	}

	public double setBackground(int index) throws PaletteException {
	        if(index >= colorListProperty.get().size()) {
	                throw new PaletteException("Out of Range");
	        }
	        RGBColor newColor = colorListProperty.get(index);
	        backgroundColor.set(newColor);
	        return index;
	    }

	@Override
	public void setPenStyle(String selectedItem) {
		actors.actOnEachElement((a) -> a.setPenStyle(selectedItem));
	}

	@Override
	public double setPalette(int index, int r, int g, int b) {
	    
	   RGBColor toAdd = new RGBColor(r/RGB_MAX, g / RGB_MAX, b / RGB_MAX, index);
	   colorListProperty.get().set(index, toAdd);
	   return index;
	}

	public Actor findActiveActor() {
		return getActiveActors().get(getActiveActors().size() - 1);
	}

	@Override
	public double setShape(int index) {
		actors.actOnEachElement(a -> a.setShape(TurtleShape.values()[index - 1]));
		update();
		return index;
	}

	@Override
	public DoubleProperty getPenWidth() {
		return currentPenWidth;
	}

	@Override
	public double setPenSize(double pixels) {
		currentPenWidth.set(pixels);
		return pixels;
	}

	public void addToPalette(double r, double g, double b) {
		RGBColor newColor = new RGBColor(r, g, b, colorListProperty.size() + 1);
		colorListProperty.add(newColor);
	}

	@Override
	public double stamp() {
		for (Actor a : actors.getActors()) {
			Actor newActor = new Actor(a.getXLocation(), a.getYLocation(), a.getHeading(), a.getPenDown() == 1,
					actors.getSize() + 1);
			newActor.setImageProperty(a.getImage());
			stamps.add(newActor);
		}
		stamps.stream().forEach((a) -> a.setStamp());
		return 0;
	}

	@Override
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
		if (actors.elementIsNull(0)) {
			double oldHeading = getActor(turtleID).getHeading();
			getActor(turtleID).setHeading(degrees);
			return Angle.calculateAngleRotated(oldHeading, getActor(turtleID).getHeading());
		}
		return 0;
	}

	@Override
	public double towards(double x, double y, double turtleID) {
		double newHeading = Angle.calculateAngleBetweenPoints(getActor(turtleID).getXLocation(),
				getActor(turtleID).getYLocation(), x, y);
		getActor(turtleID).setHeading(newHeading);
		return 0;
	}

	@Override
	public double setxy(double x, double y, double turtleID) {
		if (!actors.elementIsNull((int) turtleID)) {
			double oldX = getActor(turtleID).getXLocation();
			double oldY = getActor(turtleID).getYLocation();
			getActor(turtleID).setxy(x, y);
			return Distance.calculateDistance(oldX, oldY, x, y);
		}
		return 0;
	}

	@Override
	public double penDown(double turtleID) {
		getActor(turtleID).setPenDown(true);
		return 1;
	}

	@Override
	public double penUp(double turtleID) {
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
		return setxy(0, 0, turtleID);
	}

	@Override
	public double isPenDown(double turtleID) {
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
		return getActor(turtleID).getYLocation();
	}

	public Actor getActor(double turtleID) {
		List<Actor> turtle = actors.filter((a) -> (a.getID() == turtleID));
		return turtle.get(0);
	}

	@Override
	public double heading(double turtleID) {
		return getActor(turtleID).getHeading();
	}

	public List<Actor> getActiveActors() {
		List<Actor> activeActors = actors.filter((a) -> a.getActive().get());
		return activeActors;
	}

	@Override
	public boolean[] activeTurtles() {
		ArrayList<Boolean> activeTurtles = new ArrayList<Boolean>();
		actors.actOnEachElement(a -> activeTurtles.add(a.getActive().getValue()));
		boolean output[] = new boolean[activeTurtles.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = activeTurtles.get(i);
		}
		return output;
	}

	@Override
	public double penColor() {
		return colorListProperty.indexOf(penColor.get());
	}

	public RGBColor findColorFromIndex(double index) {
		RGBColor newColor = null;
		for (RGBColor c : colorListProperty) {
			if (c.getIndex() == index) {
				newColor = c;
			}
		}
		return newColor;
	}

	@Override
	public double shape() {
		int listIndex = TurtleShape.valueOf(actors.getActors().get(0).getShape().toString()).ordinal();
		return listIndex + 1;
	}

	@Override
	public double ID() {
		return getActiveActors().size() - 1;
	}

	@Override
	public double turtles() {
		return actors.getSize();
	}

	@Override
	public double tell(int[] arrayOfActiveTurtleIDs) {
		List<Integer> setToActive = convertToList(arrayOfActiveTurtleIDs);
		int max = Collections.max(setToActive);
		int oldSize = actors.getActors().size();
		for (int i = 0; i <= max; i++) {
			if (i < oldSize) {
				actors.getActors().get(i).setActive(setToActive.contains(i));
			} else {
				addActor(TURTLE_CREATED_ACTIVE_DEFAULT);
				toBeActive.add(new Boolean(true));
			}
		}
		for (int j = max + 1; j < oldSize; j++) {
			actors.getActors().get(j).setActive(false);
		}
		return actors.getSize() - 1;
	}

	private List<Integer> convertToList(int[] arrayOfActiveTurtleIDs) {
		List<Integer> converted = new ArrayList<>();
		for (Integer x : arrayOfActiveTurtleIDs) {
			converted.add(x);
		}
		return converted;
	}

	public void addAndSave(int[] arrayOfActiveTurtleIDs) {
		for (int i = 0; i < arrayOfActiveTurtleIDs.length; i++) {
			while (arrayOfActiveTurtleIDs[i] > actors.getSize()) {
				addActor(TURTLE_CREATED_ACTIVE_DEFAULT);
			}
		}
	}

	@Override
	public void popCurrentActive() {
		List<Boolean> localToBeActive = myCachedActiveTurtles.pop();
		setActiveTurtlesUsingBooleanArray(matchSizeOfTurtleActiveArrays(getCurrentActiveTurtles(), localToBeActive));
	}

	private List<Boolean> getCurrentActiveTurtles() {
		return actors.getCurrentActiveTurtles();
	}

	private void setAllTurtlesActiveState(Boolean toSet) {
		actors.actOnEachElement(a -> a.setActive(toSet));
	}

	@Override
	public void pushCurrentActive() {
		myCachedActiveTurtles.push(getCurrentActiveTurtles());
		setAllTurtlesActiveState(TELL_CMD_TURTLE_ACTIVE_DEFAULT);
	}

	private void setActiveTurtlesUsingBooleanArray(List<Boolean> setterArray) {
		for (int i = 0; i < setterArray.size(); i++) {
			actors.getActors().get(i).setActive(setterArray.get(i));
		}
		// setting unspecified actors to be inactive
		for (int i = setterArray.size(); i < actors.getSize(); i++) {
			actors.getActors().get(i).setActive(TELL_CMD_TURTLE_ACTIVE_DEFAULT);
		}
	}

	/**
	 * As we pop and push the active turtles, we will need to adjust the size of
	 * the boolean arrays to reflect further turtle additions
	 * 
	 * @param prevActive
	 * @param nowActive
	 */
	private List<Boolean> matchSizeOfTurtleActiveArrays(List<Boolean> prevActive, List<Boolean> nowActive) {
		while (prevActive.size() > nowActive.size()) {
			nowActive.add(ViewModel.TELL_CMD_TURTLE_ACTIVE_DEFAULT);
		}
		return nowActive;
	}

	@Override
	    public double setPenColor(int index) throws PaletteException {
	        if(index >= colorListProperty.get().size()) {
	                throw new PaletteException("Out of Range");
	        }
	        RGBColor newColor = colorListProperty.get(index);
	        penColor.set(newColor);
	        actors.actOnEachElement((a) -> a.setPenColor(newColor));
	        return index;
	    }

	@Override
	public List<Actor> getStamps() {
		return stamps;
	}

	@Override
	public ImageProperty getCurrentImage() {
		return currentActiveImage;
	}

	@Override
	public double wrap() {
		num = 1;
		return 1;
	}

	@Override
	public double window() {
		num = 2;
		return 2;
	}

	@Override
	public double fence() {
		num = 3;
		return 3;
	}

	@Override
	public double getFunction(){
		return num;
	}
}