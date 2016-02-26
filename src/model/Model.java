package model;

import interfaces_slogo_team04.ICommands;
import interfaces_slogo_team04.ICommunicator;
import interfaces_slogo_team04.IHistoryModel;
import interfaces_slogo_team04.IModel;
import interfaces_slogo_team04.IVariable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Holds the model's information
 * @author Ryan St Pierre
 *
 */
public class Model implements IModel  {
	
	private HistoryModel history;
	private VariableModel variables;
	private StringProperty language;
	private Communicator communicator;
	private CommandModel commands;
	
	public Model() {
		language = new SimpleStringProperty("English");
		history = new HistoryModel(language);
		variables = new VariableModel();
		communicator = new Communicator(this);
		commands = new CommandModel();
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
		return variables;
	}
	
	public ICommands getCommandInterface() {
		return commands;
	}

	public ICommunicator getCommunicator() {
		return communicator;
	}

}
