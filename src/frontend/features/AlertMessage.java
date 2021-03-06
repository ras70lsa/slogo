package frontend.features;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Used to display error messages
 * @author RyanStPierre
 *
 */

public class AlertMessage {

	private String message; 
	
	public AlertMessage(String input) {
		message = input;
	}
	
	public void displayError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
