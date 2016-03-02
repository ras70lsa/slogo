package interfaces.slogo.team04;

import javafx.beans.property.StringProperty;
import model.ExecutionState;
import model.HistoryModel;

public interface IModel {

	public IHistoryModel getHistory();
	public IVariable getVariables();
	public String getLangauage();
	public StringProperty getLangauageProperty();
	public ICommunicator getCommunicator();
	public ICommands getCommandInterface();
	public IView getViewInterface();
	public ExecutionState getExecutionState();
}
