package frontend.features;

import java.util.Optional;
import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SaveAlert extends Alert {

	private static ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
	public static final ButtonType YES = new ButtonType(myBundle.getString("Yes"));
	public static final ButtonType NO = new ButtonType(myBundle.getString("No"));
	
	public SaveAlert() {
		super(AlertType.CONFIRMATION);
		this.getButtonTypes().addAll(YES, NO);
		this.setTitle(myBundle.getString("Save"));
		this.setHeaderText(myBundle.getString("SavePrompt"));
		this.setContentText(myBundle.getString("Select"));

	}

	public boolean saveDesired() {
		Optional<ButtonType> selection = this.showAndWait(); 
		return (selection.get() == YES);
	}
	
}
