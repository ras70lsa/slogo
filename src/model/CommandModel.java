package model;

import backend.slogo.team04.INonLinearCommand;
import interfaces.slogo.team04.ICommands;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandModel implements ICommands{

	private ListProperty<String> names;
	private ListProperty<INonLinearCommand> commands;
	
	public CommandModel() {
		ObservableList<String> list = FXCollections.observableArrayList();
		names = new SimpleListProperty<String>(list);
		ObservableList<INonLinearCommand> cList = FXCollections.observableArrayList();
		commands = new SimpleListProperty<INonLinearCommand>(cList);
	}
	
	public ListProperty<String> getCommands() {
		return names;
	}
	
	public ListProperty<INonLinearCommand> getCommandNodes() {
		return commands;
	}
	
	public void add(String input) {
		names.add(input);
	}
	
	public void clear() {
		names.clear();
		commands.clear();
	}
	
	public void put(String name, INonLinearCommand executionNode) {
		names.add(name);
		commands.add(executionNode);
	}
	
	public INonLinearCommand getCommand(String name) {
		int index = names.indexOf(name);
		if(index == -1) {
			return null;
		}
		return commands.get(index);
	}

}
