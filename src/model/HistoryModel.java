package model;

import java.util.ResourceBundle;

import backend.slogo.team04.SlogoScanner;
import constants.DisplayConstants;
import frontend.slogo.team04.State;
import interfaces.slogo.team04.IHistoryModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HistoryModel extends State implements IHistoryModel{

	private ListProperty<String> history;
	private StringProperty language;
	
	public HistoryModel(StringProperty language) {
		
		this.language = language;
		ObservableList<String> commandList = FXCollections.observableArrayList();
		history = new SimpleListProperty<String>(commandList);
		addListeners();
		
	}
	
	private void addListeners() {
		language.addListener((a,b,c) -> updateText(b,c));
	}
	
	private void updateText(String old, String current) {
		for(int i=0; i<history.size(); i++) {
			SlogoScanner scanner = new SlogoScanner(history.get(i));
			ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + old);
			String translated = scanner.getLanguageConvertedCode(myBundle);
			myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + current);
			translated = scanner.getCodeConvertToLanguage(myBundle);
			history.set(i, translated);
		}
	}

	public ListProperty<String> getCommandList() {
		return history;
	}
	
	public void add(String command) {
		SlogoScanner scanner = new SlogoScanner(command);
		ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + language.get());
		String translated = scanner.getCodeConvertToLanguage(myBundle);
		history.add(translated);
	}

	
	public StringProperty getLanguage() {
		return language;
	}

	public void clear() {
		history.clear();
	}

}
