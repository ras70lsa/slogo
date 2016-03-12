package interfaces.slogo.team04;

import backend.slogo.team04.INonLinearCommand;
import javafx.beans.property.ListProperty;

/**
 * Interface defining the relationship between the user-defined command representation feature on the front-end
 * their representation in the data class on the backend
 * @author RyanStPierre
 *
 */
public interface ICommands {

	/**
	 * Gives the names of the user-defined commands, those typed in by the user.
	 * @return ListProperty<String> of these command names
	 */
	ListProperty<String> getCommands();
	
	/**
	 * Returns the list storing all of the head nodes of the given user defined functions
	 * A function can be run be executing this head node, which will recursively execute its code (children)
	 * @return ListProperty<INonLinearCommand> of these headnodes
	 */
	ListProperty<INonLinearCommand> getCommandNodes();

}
