package model;

import backend_slogo_team04.CommandTreeNode;
import backend_slogo_team04.INonLinearCommand;
import backend_slogo_team04.ISlogoInterpreter;
import backend_slogo_team04.Variable;
import interfaces_slogo_team04.ICommands;
import interfaces_slogo_team04.IVariable;
import javafx.beans.property.ListProperty;

public class ExecutionState implements ISlogoInterpreter, IVariable, ICommands {

	private CommandModel commands;
	private VariableModel variables;
	
	public ExecutionState() {
		commands = new CommandModel();
		variables = new VariableModel();
	}
	
	@Override
	public void resetAllSimulationVariables() {
		resetUserDefinedCommands();
		resetGlobalVariables();
		
	}

	@Override
	public void resetGlobalVariables() {
		variables.clear();
	}

	@Override
	public void resetUserDefinedCommands() {
		commands.clear();	
	}

	@Override
	public void putFunction(String functionName, INonLinearCommand headNodeOfFunction) {
		commands.put(functionName.toLowerCase(), headNodeOfFunction);
		
	}

	@Override
	public INonLinearCommand getFunction(String functionName) {
		return commands.getCommand(functionName.toLowerCase());
	}

	@Override
	public double setVariableValue(String variable, Double value) {
		return variables.setVariable(variable, value);
	}

	@Override
	public double getVariableValue(String variable) {
		return variables.getVariableValue(variable);
	}

	@Override
	public ListProperty<Variable> getVariables() {
		return variables.getVariables();
	}

	@Override
	public ListProperty<String> getCommands() {
		return commands.getCommands();
	}

}
