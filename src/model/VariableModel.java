package model;

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
	private static final double ZERO = 0d;
	private int currentLevel;
	private static final int BASE_LEVEL = 0;
	
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
	
		for(int i = currentLevel; i>=0; i--) {
			System.out.println("i" + i);
			List<Variable> currentPosition = stack.get(i);
			for(Variable variable: currentPosition) {
				if(variable.getName().get().equals(name.toLowerCase())) {
					System.out.println(variable.getDoubleValue().get());
					return variable.getDoubleValue().get();
				}
			}
		}
		return setVariable(name.toLowerCase(), ZERO);
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
	
	public void kickAllButLowest() {
	    while(currentLevel > BASE_LEVEL){
	        kick();
	    }
	}
	
	public ListProperty<ListProperty<Variable>> getStack() {
		return stack;
	}

}
