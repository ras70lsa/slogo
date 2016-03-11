package model;

import interfaces.slogo.team04.ICommunicator;
import interfaces.slogo.team04.IModel;

public class Communicator implements ICommunicator{

	IModel model;
	public Communicator(IModel model) {
		this.model = model;
	}
	
	public void addToHistory(String input) {
		model.getHistory().add(input);
		
	}
	
	public void addActor() {
		model.getViewInterface().addActor(true);
	}
		
	public String getLanguage() {
		return model.getLangauage();
	}
	
	public ExecutionState getExecutionModel() {
		return model.getExecutionState();
	}
}
