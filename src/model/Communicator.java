package model;

import backend_slogo_team04.ISlogoInterpreter;
import interfaces_slogo_team04.ICommunicator;
import interfaces_slogo_team04.IModel;

public class Communicator implements ICommunicator{

	IModel model;
	public Communicator(IModel model) {
		this.model = model;
	}
	
	public void addToHistory(String input) {
		model.getHistory().add(input);
		
	}
		
	public String getLanguage() {
		return model.getLangauage();
	}
	
	public ExecutionState getExecutionModel() {
		return model.getExecutionState();
	}
}
