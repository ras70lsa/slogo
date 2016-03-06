package model;



import interfaces.slogo.team04.ICommands;
import interfaces.slogo.team04.ICommunicator;
import interfaces.slogo.team04.IHistoryModel;
import interfaces.slogo.team04.IModel;
import interfaces.slogo.team04.ISlogoModelActions;
import interfaces.slogo.team04.IVariable;
import interfaces.slogo.team04.IView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Holds the model's information
 * @author Ryan St Pierre
 *
 */
public class Model implements IModel  {
	
	private HistoryModel history;
	private ExecutionState executionState;
	private StringProperty language;
	private Communicator communicator;
	private ViewModel view;
	
	public Model() {
		language = new SimpleStringProperty("English");
		history = new HistoryModel(language);
		executionState = new ExecutionState();
		communicator = new Communicator(this);
		view = new ViewModel();
	}

	public IHistoryModel getHistory() {
		return history;
	}
	
	public String getLangauage() {
		return language.get();
	}
	
	public StringProperty getLangauageProperty() {
		return language;
	}

	public IVariable getVariables() {
		return executionState;
	}
	
	public ICommands getCommandInterface() {
		return executionState;
	}

	public ICommunicator getCommunicator() {
		return communicator;
	}

	public IView getViewInterface() {
		return view;
	}
	
	public ISlogoModelActions getView() {
		return view;
	}

	@Override
	public ExecutionState getExecutionState() {
		return executionState;
		
	}

}
