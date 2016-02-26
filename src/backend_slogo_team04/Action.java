package backend_slogo_team04;

import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

/**
 * Encapsulates the actions taken (information needed when parsing a single block of text from the front end) so that we can track
 * what we have done, so that we can implement redo-undo functionality later
 * @author jonathanim
 * @author Ryan St Pierre
 *
 */
public class Action {
	

	private Model model;
	private Map<String, Double> variables;
	private Map<String, INonLinearCommand> commands;
	private StringProperty input;
	

	public Action(Model model, String input, Map<String, Double> variables, Map<String, INonLinearCommand> commands) {
		this.model=model;
		this.variables = variables;
		this.commands = commands;
		this.input= new SimpleStringProperty(input);
	}
	
	public Model getModel() {
		return model;
	}
	
	public Map<String, Double> getVariables() {
		return variables;
	}
	
	public StringProperty getInput2() {
		return input;
	}
	
	public String getInput() {
		return input.get();
	}
	
	public Map<String, INonLinearCommand> getCommands() { 
		return commands;
	}

}
