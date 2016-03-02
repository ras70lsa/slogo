package frontend.features;

import java.util.ResourceBundle;

import constants.CSSPathConstants;
import constants.DisplayConstants;
import constants.ResourceConstants;
import frontend.slogo.team04.LanguageSelector;
import interfaces.slogo.team04.IDisplay;
import interfaces.slogo.team04.IModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SlogoMenu extends MenuBar {

	IDisplay display;
	IModel model;
	ResourceBundle myBundle;
	Menu options;
	Menu help;
	Menu clear;
	
	public SlogoMenu(IModel model, IDisplay display) { 
		this.display = display;
		this.model = model;
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		createMenuBar();
	}
	
	private void createMenuBar() {
		
		createMenus();
		addVisualItem(myBundle.getString("View"), options);
		populate();
	}
	
	private void populate() {
		clear.getItems().add(createMenuItem(myBundle.getString("History"), e-> clearHistory()));
		clear.getItems().add(createMenuItem(myBundle.getString("VariableTitle"), e-> clearVariables()));
		help.getItems().add(createMenuItem(myBundle.getString("CommandHelp"), e-> helpBox()));
		options.getItems().add(clear);
		options.getItems().add(new SeparatorMenuItem());
		options.getItems().add(new LanguageSelector(model.getLangauageProperty()));
	}

	private void clearVariables() {
		model.getVariables().clearVariables();
	}

	private void createMenus() {
		options = new Menu(myBundle.getString("Options"));
		help = new Menu(myBundle.getString("Help"));
		clear =  new Menu(myBundle.getString("Clear"));
		addMenu(options);
		addMenu(help);
		
	}

	private void clearHistory() {
		model.getHistory().clear();
	}

	private MenuItem createMenuItem(String name, EventHandler<ActionEvent> event) {
		MenuItem menuItem = new MenuItem(name);
		menuItem.setOnAction(event);
		return menuItem;
	}

	private void addMenu(Menu menu) {
		this.getMenus().add(menu);
	}

	private void addVisualItem(String title, Menu file) {
		MenuItem item = new MenuItem(title);
		item.setOnAction(e -> display.getView().getOptions());
		file.getItems().add(item);
	}
	

	private void helpBox () {
		
		HTMLDisplay display = new HTMLDisplay();
		display.show();
   }
}
