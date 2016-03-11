package interfaces.slogo.team04;

import model.ExecutionState;

/**
 * Defines the behavior that the Controller can use to communicate with the model
 * @author RyanStPierre
 *
 */
public interface ICommunicator {

	void addToHistory(String str);
	String getLanguage();
	ExecutionState getExecutionModel();
	void addActor();
}
