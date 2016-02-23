package interfaces_slogo_team04;
import javafx.stage.Stage;

/**
 * This interface groups values that can be altered by the user or through code based on functionally who needs access
 * @author Ryan St Pierre
 *
 */
public interface State {

	/**
	 * 
	 * @return A Stage with options for the user to alter regarding the state
	 */
	public Stage getUserOptions();
	
	/**
	 * Each instance of the State interface will have the proper getters and setters to save and alter state
	 * This cannot be done generically in the State interface because there is no way of knowing which type these values 
	 * will take
	 */
}
