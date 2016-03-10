package frontend.features;

import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.scene.control.Menu;

public class MenuFeature extends Menu {

	private ResourceBundle myBundle;
	
	public MenuFeature() {
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
	}
	
	protected String getString(String input) {
		return myBundle.getString(input);
	}
}
