package frontend_features;

import java.util.ResourceBundle;

import constants.CSSPathConstants;
import constants.DisplayConstants;
import constants.ResourceConstants;
import frontend_slogo_team04.LanguageSelector;
import interfaces_slogo_team04.IDisplay;
import interfaces_slogo_team04.IModel;
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
	Menu history;
	
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
		history.getItems().add(createMenuItem(myBundle.getString("Clear"), e-> clear()));
		help.getItems().add(createMenuItem(myBundle.getString("Command"), e-> helpBox()));
		options.getItems().add(history);
		options.getItems().add(new SeparatorMenuItem());
		options.getItems().add(new LanguageSelector(model.getLangauageProperty()));
	}

	private void createMenus() {
		options = new Menu(myBundle.getString("Options"));
		help = new Menu(myBundle.getString("Help"));
		history =  new Menu(myBundle.getString("History"));
		addMenu(options);
		addMenu(help);
		
	}

	private void clear() {
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
	
	private Stage helpBox () {
		 Stage stage = new Stage();
		 stage.setTitle("HTML");
	     stage.setWidth(500);
	     stage.setHeight(500);
	     Scene scene = new Scene(new Group());
	     VBox root = new VBox();     

	     final WebView browser = new WebView();
	     final WebEngine webEngine = browser.getEngine();
	     ScrollPane scrollPane = new ScrollPane();
	     scrollPane.setContent(browser);
	     scrollPane.setFitToHeight(true);
	     scrollPane.setFitToWidth(true);
	     ClassLoader classLoader = getClass().getClassLoader();
	 	 String url = classLoader.getResource(CSSPathConstants.HELP).toExternalForm();  
	     webEngine.load(url);
	     
	     root.getChildren().addAll(scrollPane);
	     scene.setRoot(root);
	     
	     stage.setScene(scene);
	     stage.show();
	     return stage;
	        
   }
}
