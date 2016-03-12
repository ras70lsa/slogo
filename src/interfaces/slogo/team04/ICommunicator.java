package interfaces.slogo.team04;

import model.ExecutionState;

/**
 * Defines the behavior that the Controller can use to communicate with the model
 *
 */
public interface ICommunicator {

	/**
	 * @param String str- the command code to add to the History
	 */
	void addToHistory(String str);
	
	/**
	 * @return String- current language
	 */
	String getLanguage();
	
	/**
	 * Returns the ExecutionModel
	 * This is the wrapper class that includes the functionality of the variables and user-defined commands
	 * @return
	 */
	ExecutionState getExecutionModel();

	/**
	 * Adds an actor to the workspace
	 */
	void addActor();
}
