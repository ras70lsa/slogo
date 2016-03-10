package frontend.features;

import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuFeature extends Menu {

	private ResourceBundle myBundle;
	
	public MenuFeature(String title) {
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		this.setText(title);
	}
	
	protected MenuItem createMenuItem(String title, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(title);
		item.setOnAction(event);
		return item;
	}
	
	protected String getString(String input) {
		return myBundle.getString(input);
	}
	
}
