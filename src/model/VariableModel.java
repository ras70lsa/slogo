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
		variables.add(new Variable("Ryan", 4.9));
		variables.add(new Variable("Tayla", 6.3));
		variables.add(new Variable("Jean", 10));
	}

	public ListProperty<Variable> getVariables() {
		return variables;
	}

}
