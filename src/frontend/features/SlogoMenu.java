package frontend.features;

import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import frontend.slogo.team04.LanguageSelector;
import interfaces.slogo.team04.IDisplay;
import interfaces.slogo.team04.IModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class SlogoMenu {

	IDisplay display;
	IModel model;
	ResourceBundle myBundle;
	Menu options;
	Menu advancedOptions;
	Menu help;
	Menu clear;
	MenuBar bar;

	
	public SlogoMenu(IModel model, IDisplay display) { 
		this.display = display;
		this.model = model;
		bar = new MenuBar();
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		createMenuBar();
	}
	
	private void createMenuBar() {
		
		createMenus();
		populate();
		advanced();
	}
	
	private void advanced() {
		
		for(Node node: display.getView().getReleventProperties()) {
			MenuItem item = new MenuItem("", node);
			advancedOptions.getItems().add(item);
		}
	}

	private void populate() {
		clear.getItems().add(createMenuItem(myBundle.getString("History"), e-> clearHistory()));
		clear.getItems().add(createMenuItem(myBundle.getString("VariableTitle"), e-> clearVariables()));
		help.getItems().add(createMenuItem(myBundle.getString("CommandHelp"), e-> helpBox("Basic")));
		help.getItems().add(createMenuItem(myBundle.getString("CommandHelpAdvanced"), e-> helpBox("Extended")));
		options.getItems().add(clear);
		options.getItems().add(advancedOptions);
		options.getItems().add(new SeparatorMenuItem());
		options.getItems().add(new LanguageSelector(model.getLangauageProperty()));
	}

	private void clearVariables() {
		model.getVariables().clearVariables();
	}

	private void createMenus() {
		addMenu(new FileOption(display.getManager(), getString("File")));
		addMenu(new Option(getString("Options")));
		options = new Menu(myBundle.getString("Options"));
		advancedOptions = new Menu(myBundle.getString("AdvancedOptions"));
		help = new Menu(myBundle.getString("Help"));
		clear =  new Menu(myBundle.getString("Clear"));
		addMenu(options);
		addMenu(help);
		
	}

	private void clearHistory() {
		model.getHistory().clear();
	}

	private void addMenu(Menu menu) {
		bar.getMenus().add(menu);
	}
	

	private void helpBox (String type) {
		HTMLDisplay display = new HTMLDisplay(type);
		display.show();
   }
	
	public MenuBar getBar() {
		return bar;
	}
	
	protected String getString(String input) {
		return myBundle.getString(input);
	}
	
	protected MenuItem createMenuItem(String title, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(title);
		item.setOnAction(event);
		return item;
	}
	
}
