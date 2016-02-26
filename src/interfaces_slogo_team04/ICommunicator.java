package interfaces_slogo_team04;

/**
 * Defines the behavior that the Controller can use to communicate with the model
 * @author RyanStPierre
 *
 */
public interface ICommunicator {

	public void addToHistory(String str);
	public String getLanguage();
	public void addCommand(String command);
}
