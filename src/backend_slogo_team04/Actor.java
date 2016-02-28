package backend_slogo_team04;

import Utilities.Angle;
import Utilities.Distance;

/**
 * Class for manipulating the Turtle in the model
 * 
 * @author Ryan St Pierre
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
		heading = Angle.mod360(heading);
	}

	public void rotateClockwise(double degrees) {
		heading = Angle.mod360(heading - degrees);
	}

	public void rotateCounterClockwise(double degrees) {
		heading = Angle.mod360(heading + degrees);
	}

	public double getHeadingInRadians() {
		return Math.toRadians(getHeading());
	}
}
