package backend.slogo.team04;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import model.ModelLine;
import properties.ImageProperty;
import utilities.Angle;

/**
 * Class for manipulating the Turtle in the model
 * 

 * @author Ryan St Pierre 
 * @author Sophie Guo

 *
 */

public class Actor {

	public static final String DEFAULT_PATH = "images/slogoTurtle.png";
	private DoubleProperty xLocation;
	private BooleanProperty active;
	private double yLocation;
	private double heading;
	private boolean penIsDown;
	private boolean showing;
	private ImageProperty image;
	private Stack<ModelLine> myLines;

	public Actor(double x, double y, double heading, boolean penIsDown) {
		xLocation = new SimpleDoubleProperty();
		xLocation.set(x);
		yLocation = y;
		showing = true;
		this.heading = heading;
		this.penIsDown = penIsDown;
		image = new ImageProperty();
		image.set(getDefaultImage());
		active = new SimpleBooleanProperty(true);
		myLines = new Stack<ModelLine>();
	}
	
	public Actor(Actor save) {
		this(save.getXLocation(), save.getYLocation(), save.getHeading(), save.penIsDown);
	}

	private Image getDefaultImage() {
		return new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_PATH));
	}

	public ModelLine forward(double pixels) {
		double angle = getHeadingInRadians();
		ModelLine newLine = setxy(getXLocation() + Math.cos(angle) * pixels,
				getYLocation() + Math.sin(angle) * pixels);
		if (penIsDown) {
			myLines.add(newLine);
			return newLine;		
		}
		return null;
	}
	
	public void setXLocation(double x){
		xLocation.set(x);
	}
	
	public void setYLocation(double y){
		yLocation = y;
	}
	
	public ModelLine setxy(double x, double y) {
		if(penIsDown){
			ModelLine newLine = new ModelLine(getXLocation(), getYLocation(), x, y);
			xLocation.set(x);;
			yLocation = y;
			return newLine;
		}else{
			return null;
		}
	}

	public double getXLocation() {
		return xLocation.get();
	}
	
	public DoubleProperty getXProperty() {
		return xLocation;
	}

	public double getYLocation() {
		return yLocation;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = Angle.mod360(heading);
	}

	public void rotateClockwise(double degrees) {
		this.heading = Angle.mod360(heading - degrees);
	}

	public void rotateCounterClockwise(double degrees) {
		this.heading = Angle.mod360(heading + degrees);
	}

	public double getHeadingInRadians() {
		return Math.toRadians(getHeading());
	}

	public void setPenDown(boolean down) {
		// TODO Auto-generated method stub
		penIsDown = down;
	}

	public void setShowing(boolean showing) {
		this.showing  = showing;
		
	}

	public int getPenDown() {
		return (penIsDown) ? 1: 0;
	}

	public boolean getVisible() {
		return showing;
	}

	public ImageProperty getImageProperty() {
		return image;
	}
	
	public String toString() {
		return  "Actor" + xLocation;
		
	}

	public void toggleActive() {
		active.set(!active.get());
	}
	
	public BooleanProperty getActive() {
		return active;
	}

	public List<ModelLine> getMyLines() {
		return myLines;
	}
}

