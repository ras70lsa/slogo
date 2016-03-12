package interfaces.slogo.team04;

import javafx.beans.property.StringProperty;
import model.ExecutionState;

/**
 * Defines what can be accessed from the Model
 * Gives access to the interfaces/API of all the sub-models
 * @author RyanStPierre
 *
 */
public interface IModel {

	/**
	 * Returns an interface that defines access to the current HistoryModel
	 * The HistoryModel contains the history of the previously run code
	 * @return IHistoryModel
	 */
	IHistoryModel getHistory();
	
	/**
	 * Returns an interface that defines access to the current VariableModel
	 * The VariableModel holds the variables of the current workspace (handling dynamic scope internally)
	 * @return IVariable
	 */
	IVariable getVariables();
	
	/**
	 * Returns the global selected language
	 * @return String
	 */
	String getLangauage();
	
	/**
	 * Returns the StringProperty instance of the language
	 * @return
	 */
	StringProperty getLangauageProperty();
	
	/**
	 * Returns the interface that defines access to the Communicator
	 * The Communicator is used for Controller communication with view features
	 * @return ICommunicator
	 */
	ICommunicator getCommunicator();
	
	/**
	 * Returns the interface that defines access to the CommandModel
	 * The CommandModel holds the user-defined functions
	 * @return ICommands
	 */
	ICommands getCommandInterface();
	
	/**
	 * Returns the IView
	 * IView defines the questions the front end drawing can ask the ViewModel
	 * @return IView
	 */
	IView getViewInterface();
	
	/**
	 * Returns the wrapper class containing the Variable state and User-defined commands
	 * @return ExecutionState
	 */
	ExecutionState getExecutionState();
}
