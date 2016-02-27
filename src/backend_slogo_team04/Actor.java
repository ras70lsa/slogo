package backend_slogo_team04;

/**
 * Class for manipulating the Turtle in the model
 * @author Ryan St Pierre
 *
 */

public class Actor {

	private double xLocation;
	private double yLocation;
	private double rotation;
	private double heading;
	
	public Actor(double x, double y, double spin) {
		xLocation = x;
		yLocation = y;
		rotation = spin;
	}
	
	public double getXLocation(){
		return xLocation;
	}
	
	public double getYLocation(){
		return yLocation;
	}
	
	public double getHeading(){
		return heading;
	}
	
	public double getRotation(){
		return rotation;
	}
}
