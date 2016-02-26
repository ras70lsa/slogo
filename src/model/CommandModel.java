package model;

import backend_slogo_team04.Action;
import interfaces_slogo_team04.ICommands;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandModel implements ICommands{

	private ListProperty<String> commands;
	
	public CommandModel() {
		ObservableList<String> list = FXCollections.observableArrayList();
		commands = new SimpleListProperty<String>(list);
		commands.add("Ryan");
	}
	
	public ListProperty<String> getCommands() {
		return commands;
	}
	
	public void add(String input) {
		commands.add(input);
	}

}
