package frontend.features;

import java.util.Map;
import java.util.TreeMap;

import frontend.slogo.team04.LanguageSelector;
import interfaces.slogo.team04.IDisplay;
import interfaces.slogo.team04.IModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class Option extends MenuFeature {

	Menu clear;
	Menu advancedOptions;
	
	Map<String, EventHandler<ActionEvent>> clearItems = new TreeMap<>();{
	     clearItems.put(getString("History"), e-> clearHistory());
	     clearItems.put(getString("VariableTitle"), e-> clearVariables());
	};
	
	private IModel model;
	private IDisplay display;
	
	public Option(IModel model, IDisplay display, String title) {
		super(title);
		this.model = model;
		this.display = display;
		createMenus();
		setUp();
	}
	
	private void createMenus() {
		clear = new Menu(getString("Clear"));
		advancedOptions = new Menu(getString("AdvancedOptions"));
	}

	private void setUp() {
		this.getItems().add(clear);
		createAdvanced();
		add(new LanguageSelector(model.getLangauageProperty()));
		populateClear();
		
	}
	
	private void createAdvanced() {
		
		for(Node node: display.getView().getReleventProperties()) {
			MenuItem item = new MenuItem("", node);
			advancedOptions.getItems().add(item);
		}
		add(new SeparatorMenuItem());
		add(advancedOptions);
	}
	
	private void add(MenuItem menuItem) {
		this.getItems().add(menuItem);
	}

	private void populateClear() {
		populateFromMap(clear, clearItems);
		
	}
	
	private void clearHistory() {
		model.getHistory().clear();
	}
	
	private void clearVariables() {
		model.getVariables().clearVariables();
	}

}
