package interfaces.slogo.team04;

import backend.slogo.team04.ISlogoInterpreter;
import model.ExecutionState;

/**
 * Defines the behavior that the Controller can use to communicate with the model
 * @author RyanStPierre
 *
 */
public interface ICommunicator {

	public void addToHistory(String str);
	public String getLanguage();
	public ExecutionState getExecutionModel();
}
