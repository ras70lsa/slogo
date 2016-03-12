package interfaces.slogo.team04;

import javafx.beans.property.StringProperty;
import model.ExecutionState;

public interface IModel {

	IHistoryModel getHistory();
	IVariable getVariables();
	String getLangauage();
	StringProperty getLangauageProperty();
	ICommunicator getCommunicator();
	ICommands getCommandInterface();
	IView getViewInterface();
	ExecutionState getExecutionState();
}
