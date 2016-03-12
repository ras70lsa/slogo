package interfaces.slogo.team04;

import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;

/**
 * Defines the relationship between the History front-end feature and the History state class in the backend
 * @author RyanStPierre
 *
 */
public interface IHistoryModel  {

	/**
	 * Returns the list property holding the text representation of the user code
	 * @return ListProperty representing the user-defined command names of the current list
	 */
	ListProperty<String> getCommandList();
	
	/**
	 * Adds a String representing code history to the history list property
	 * @param command- the String to be added
	 */
	void add(String command);
	
	/**
	 * Returns the language that is currently being used.  This communication is important because 
	 * it allows the language in the history feature to change whenever the language is selected by the user.
	 * In other words this allows the list of previously types in code dynamically change with the changing 
	 * selected language
	 * @return StringProperty representing the current language
	 */
	StringProperty getLanguage();
	
	/**
	 * Clears the list of previously typed in code, consequentially clearing the front-end feature
	 */
	void clear();

}
