package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import java.util.stream.Collectors;

import frontend.features.TurtleShape;
import backend.slogo.team04.Actor;
import backend.structures.RGBColor;
import interfaces.slogo.team04.ISlogoModelActionsExtended;
import interfaces.slogo.team04.IView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import properties.ColorProperty;
import properties.ImageProperty;
import utilities.Angle;
import utilities.Distance;

public class ViewModel extends Observable implements IView, ISlogoModelActionsExtended {

	private static final double RGB_MAX = 255;
	private static final double RGB_INTERVAL = 255 / 2 + 1;
	private static final Boolean TELL_CMD_TURTLE_ACTIVE_DEFAULT = Boolean.FALSE;
	private static final Boolean TURTLE_CREATED_ACTIVE_DEFAULT = Boolean.TRUE;
	
	private ListProperty<Actor> actors;
	private List<Actor> stamps;
	private Stack<ModelLine> lineManager;
	private ColorProperty backgroundColor;
	private ColorProperty penColor;
	
	private List<Boolean> activeStates;
	
	
	private List<Boolean> currentActiveTurtles;
	private Stack<List<Boolean>> myCachedActiveTurtles;
	
	private ListProperty<RGBColor> colorListProperty;
	private ImageProperty currentActiveImage;
	private DoubleProperty currentPenWidth;

	public ViewModel() {
		backgroundColor = new ColorProperty();
		currentActiveImage = new ImageProperty();
		penColor = new ColorProperty();
		currentPenWidth = new SimpleDoubleProperty();
		ObservableList<Actor> list = FXCollections.observableArrayList();
		actors = new SimpleListProperty<Actor>(list);
		addActor(TURTLE_CREATED_ACTIVE_DEFAULT);
		activeStates = new ArrayList<> ();
		stamps = new ArrayList<Actor>();
		lineManager = new Stack<ModelLine>();
		generateColorListProperty();
		addListeners(actors.get(actors.getSize() - 1));
		
		

		currentActiveTurtles = new ArrayList<>();
	        myCachedActiveTurtles = new Stack<>();
	}

	@Override
	public void addActor(boolean visible) {
		Actor newActor = new Actor(0, 0, Angle.HALF_CIRCLE / 2, visible, actors.size());
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

	public double setBackground(int index) {
		RGBColor newColor = findColorFromIndex(index);
		backgroundColor.set(newColor);
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
		// ArrayList<Actor> activeActors = new ArrayList<Actor>();
		// actors.stream().filter((a) ->
		// a.getActive().get()).forEach(activeActors::add);
		return getActiveActors().get(getActiveActors().size() - 1);
	}

	public double setShape(int index) {
		actors.stream().forEach(a -> a.setShape(TurtleShape.values()[index - 1]));
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

	public void addToPalette(double r, double g, double b) {
		RGBColor newColor = new RGBColor(r, g, b, colorListProperty.size() + 1);
		colorListProperty.add(newColor);
	}

	public double stamp() {
		for (Actor a: actors){
			Actor newActor = new Actor(a.getXLocation(), a.getYLocation(), a.getHeading(),a.getPenDown() == 1, actors.size() + 1);
			newActor.setImageProperty(a.getImage());
			stamps.add(newActor);
	
		}
		stamps.stream().forEach((a) -> a.setStamp());
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
		double newHeading = Angle.calculateAngleBetweenPoints(getActor(turtleID).getXLocation(),
				getActor(turtleID).getYLocation(), x, y);
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
		List<Actor> turtle = actors.stream().filter((a) -> (a.getID() == turtleID)).collect(Collectors.toList());
		return turtle.get(0);
	}

	@Override
	public double heading(double turtleID) {
		return getActor(turtleID).getHeading();
	}

	public ArrayList<Actor> getActiveActors() {
		ArrayList<Actor> activeActors = new ArrayList<Actor>();
		actors.stream().filter((a) -> a.getActive().get()).forEach(activeActors::add);
		return activeActors;
	}

	@Override
	public boolean[] activeTurtles() {
		ArrayList<Boolean> activeTurtles = new ArrayList<Boolean>();
		actors.stream().forEach(a -> activeTurtles.add(a.getActive().getValue()));
		boolean output[] = new boolean[activeTurtles.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = activeTurtles.get(i);
		}
		return output;
	}

	@Override
	public double penColor() {
		return findIndexFromColor(penColor.get());
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

	public double findIndexFromColor(RGBColor color) {
		double index = -1;
		for (RGBColor c : colorListProperty) {
			if (c.getRed() == color.getRed() && c.getGreen() == color.getRed() && c.getBlue() == color.getBlue()) {
				index = colorListProperty.indexOf(c);
			}
		}
		return index;
	}

	@Override
	public double shape() {
		int listIndex = TurtleShape.valueOf(actors.get(0).getShape().toString()).ordinal();
		return listIndex + 1;
	}

	@Override
	public double ID() {
		return getActiveActors().size() - 1;
	}

	@Override
	public double turtles() {
		return actors.size();
	}

	@Override
	public double tell(int[] arrayOfActiveTurtleIDs) {
		List<Integer> setToActive = convertToList(arrayOfActiveTurtleIDs);
		int max = Collections.max(setToActive);
		int oldSize = actors.get().size();
		for(int i=0; i<=max; i++) {
			if(i<oldSize) {
				if(setToActive.contains(i)) {
					actors.get().get(i).setActive(true);
				} else {
					actors.get().get(i).setActive(false);
				}
			} else {
					addActor(TURTLE_CREATED_ACTIVE_DEFAULT);
					currentActiveTurtles.add(new Boolean(true));
			}
		}
		for(int j = max; j<oldSize; j++) {
			actors.get().get(j).setActive(false);
		}
		
		return actors.size() - 1;
	}

	private List<Integer> convertToList(int[] arrayOfActiveTurtleIDs) {
		List<Integer> converted = new ArrayList<>();
		for(Integer x: arrayOfActiveTurtleIDs) {
			converted.add(x);
		}
		return converted;
	}

	public void addAndSave(int[] arrayOfActiveTurtleIDs) {
		for (int i = 0; i < arrayOfActiveTurtleIDs.length; i++) {
			while (arrayOfActiveTurtleIDs[i] > actors.size()) {
				addActor(TURTLE_CREATED_ACTIVE_DEFAULT);
			}
		}
	}

	@Override
	public void popCurrentActive() {
		// TODO Auto-generated method stub
	        List<Boolean> prevActive = currentActiveTurtles;
	        currentActiveTurtles = myCachedActiveTurtles.pop();
	        matchSizeOfTurtleActiveArrays( prevActive, currentActiveTurtles);
	    
//	    
//		for (int i = 0; i < actors.size(); i++) {
//			actors.get(i).getActive().set(activeStates.get(i));
//		}
	}

	@Override
	public void pushCurrentActive() {
	    myCachedActiveTurtles.push(currentActiveTurtles);
	    currentActiveTurtles = new ArrayList<>();
	    matchSizeOfTurtleActiveArrays(myCachedActiveTurtles.peek(), currentActiveTurtles);
//		for (int i = 0; i < activeTurtles().length; i++) {
//			activeStates.add(activeTurtles()[i]);
//		}
	}
	
	
	/**
	 * As we pop and push the active turtles, we will need to adjust the size of the boolean arrays to reflect further 
	 * turtle additions
	 * @param prevActive
	 * @param nowActive
	 */
	private void matchSizeOfTurtleActiveArrays(List<Boolean> prevActive, List<Boolean> nowActive){
	    while(prevActive.size() > nowActive.size()){
	        nowActive.add(ViewModel.TELL_CMD_TURTLE_ACTIVE_DEFAULT);
	    }
	}

	@Override
	public double setPenColor(int index) {
		RGBColor newColor = findColorFromIndex(index);
		penColor.set(newColor);
		actors.stream().forEach((a) -> a.setPenColor(newColor));
		return index;
	}

	@Override
	public List<Actor> getStamps() {
		return stamps;
	}
}




