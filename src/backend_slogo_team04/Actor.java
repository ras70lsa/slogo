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
	
	public void setXLocation(double x){
		xLocation = xLocation + x;
	}
	
	public void setYLocation(double y){
		yLocation = yLocation + y;
	}
	
	public double getYLocation(){
		return yLocation;
	}
	
	public void setHeading(double h){
		heading = heading + h;
	}
	
	public void setRotation(double r){
		rotation = rotation + r;
	}
	public double getHeading(){
		return heading;
	}
	
	public double getRotation(){
		return rotation;
	}
}
