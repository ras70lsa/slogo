package backend_slogo_team04;

import Utilities.Angle;
import Utilities.Distance;
import javafx.scene.image.Image;
import model.ModelLine;
import properties.ImageProperty;

/**
 * Class for manipulating the Turtle in the model
 * 

 * @author Ryan St Pierre 
 * @author Sophie Guo

 *
 */

public class Actor {

	public static final String DEFAULT_PATH = "visual_resources/turtle.jpg";
	private double xLocation;
	private double yLocation;
	private double heading;
	private boolean penIsDown;
	private boolean showing;
	private ImageProperty image;

	public Actor(double x, double y, double heading, boolean penIsDown) {
		xLocation = x;
		yLocation = y;
		showing = true;
		this.heading = heading;
		this.penIsDown = penIsDown;
		image = new ImageProperty();
		image.set(getDefaultImage());
	}

	private Image getDefaultImage() {
		return new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_PATH));
	}

	public ModelLine forward(double pixels) {
		double angle = getHeadingInRadians();
		ModelLine newLine = setxy(getXLocation() + Math.cos(angle) * pixels,
				getYLocation() + Math.sin(angle) * pixels);
		if (penIsDown) {
			return newLine;		
		}
		return null;
	}
	
	public void setXLocation(double x){
		xLocation = x;
	}
	
	public void setYLocation(double y){
		yLocation = y;
	}
	
	public ModelLine setxy(double x, double y) {
		ModelLine newLine = new ModelLine(getXLocation(), getYLocation(), x, y);
		xLocation = x;
		yLocation = y;
		return newLine;
	}

	public double getXLocation() {
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
}

