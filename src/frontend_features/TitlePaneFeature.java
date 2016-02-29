package frontend_features;

import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.scene.control.TitledPane;

public class TitlePaneFeature extends TitledPane {
	
	ResourceBundle myBundle;
	
	public TitlePaneFeature() {
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH +
				ResourceConstants.ENGLISH);
	}
	
	public TitlePaneFeature(String title) {
		this();
		this.setText(title);
	}

	protected String getString(String input) {
		return myBundle.getString(input);
	}
	
	

}
