package model;

import java.util.HashMap;
import java.util.Map;

import backend_slogo_team04.Variable;
import interfaces_slogo_team04.IVariable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VariableModel implements IVariable {

	private ListProperty<Variable> variables;
	
	public VariableModel() {
		ObservableList<Variable> variableList = FXCollections.observableArrayList();
		variables = new SimpleListProperty<Variable>(variableList);
		Map<String, Double> map = new HashMap<String, Double>();
	}

	public ListProperty<Variable> getVariables() {
		return variables;
	}
	
	public void set(Map<String, Double> map) {
		
		variables.clear();
		for(String name: map.keySet()) {
			variables.add(new Variable(name, map.get(name)));
		}
	}

}
