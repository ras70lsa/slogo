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
	
	public Actor(double x, double y, double spin) {
		xLocation = x;
		yLocation = y;
		rotation = spin;
	}
}
