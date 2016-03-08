package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.slogo.team04.Variable;
import interfaces.slogo.team04.IVariable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VariableModel implements IVariable {

	private ListProperty<ListProperty<Variable>> stack;
	private int currentLevel;
	
	public VariableModel() {
		currentLevel=-1;
		stack = new SimpleListProperty<>(FXCollections.observableArrayList());
		incept();
	}

	public ListProperty<Variable> getVariables() {
		return getCurrentList();
	}
	
	private ListProperty<Variable> getCurrentList() {
		return stack.get(currentLevel);
	}

	public void set(Map<String, Double> map) {
		
		for(String name: map.keySet()) {
			stack.get(currentLevel).add(new Variable(name, map.get(name)));
		}
	}
	
	public void clear() { 
		getCurrentList().clear();
	}

	public double setVariable(String name, Double value) {
		if(alreadyThere(name, value) == -1) {
			getCurrentList().add(new Variable(name.toLowerCase(), value));
		} 
		return value;
	}

	private int alreadyThere(String name, Double value) {
		for(Variable variable: getCurrentList()) {
			if(variable.getName().get().equals(name.toLowerCase())) {
				variable.getDoubleValue().set(value);
				return getCurrentList().indexOf(variable);
			}
		}
		
		return -1;
	}

	public double getVariableValue(String name) {
		
		for(Variable variable: getCurrentList()) {
			if(variable.getName().get().equals(name.toLowerCase())) {
				return variable.getDoubleValue().get();
			}
		}
		
		return setVariable(name.toLowerCase(), 0d);
	}

	@Override
	public void clearVariables() {
		stack.clear();
		
	}

	public void incept() {
		ObservableList<Variable> variableList = FXCollections.observableArrayList();
		ListProperty<Variable> variables = new SimpleListProperty<Variable>(variableList);
		stack.add(variables);
		currentLevel++;
	}

	public void kick() {
		stack.remove(currentLevel--);
	}
	
	public ListProperty<ListProperty<Variable>> getStack() {
		return stack;
	}

}
