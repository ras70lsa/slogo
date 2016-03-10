package model;

import backend.slogo.team04.INonLinearCommand;
import backend.slogo.team04.ISlogoInterpreterVariableScope;
import backend.slogo.team04.Variable;
import interfaces.slogo.team04.ICommands;
import interfaces.slogo.team04.IVariable;
import javafx.beans.property.ListProperty;

public class ExecutionState implements ISlogoInterpreterVariableScope, IVariable, ICommands {

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
	
	@Override
	public void clearVariables() {
		variables.clear();
		
	}

	@Override
	public void incept() {
		// TODO Auto-generated method stub
		variables.incept();
	}

	@Override
	public void kick() {
		// TODO Auto-generated method stub
		variables.kick();
	}

	@Override
	public ListProperty<ListProperty<Variable>> getStack() {
		return variables.getStack();
	}

}
