package frontend_features;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * This defines the interface for every feature in the main menu.  
 * Each feature is represented as a Pane, which internally sets up its components.
 * @author Ryan St Pierre
 *
 */
public interface Module {
	
	/**
	 * @return a Pane for the Display class to position as desired
	 */
	public Region getPane();
	
	/**
	 * This returns a State, which internally can provide a Stage for the user to select options to edit the Stage
	 * This gives the functionality of allowing the User to change given settings, such as background color.
	 * @return State 
	 */

	public void getOptions();
	
	public void position(double x, double y, double prefWidth, double prefHeight);

	
	
}
