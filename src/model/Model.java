package model;

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
	
	public Model() {
		language = new SimpleStringProperty("English");
		history = new HistoryModel(language);
		variables = new VariableModel();
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

}
