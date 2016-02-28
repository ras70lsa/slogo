package backend_slogo_team04;

import Utilities.Angle;
import Utilities.Distance;

/**
 * Class for manipulating the Turtle in the model
 * 

 * @author Ryan St Pierre 
 * @author Sophie Guo

 *
 */

public class Actor {

	private double xLocation;
	private double yLocation;
	private double heading;

	public Actor(double x, double y, double heading) {
		xLocation = x;
		yLocation = y;
		this.heading = heading;
	}

	public void setXLocation(double x){
		xLocation = x;
	}
	
	public void setYLocation(double y){
		yLocation = y;
	}
	
	public void setxy(double x, double y) {
		xLocation = x;
		yLocation = y;
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
}

