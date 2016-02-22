package frontend_slogo_team04;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;

public class ToggleButton extends Button {

	
	BooleanProperty value;
	
	public ToggleButton(BooleanProperty value) {
		super();
		this.value = value;
		this.setOnMouseClicked(e-> toggle());
		setState();
	}
	
	public ToggleButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public ToggleButton(String text, Node graphic) {
		super(text, graphic);
		// TODO Auto-generated constructor stub
	}
	
	private void toggle() {
	
		changeValue();
		setState();
	}
	
	private void setState() {
		if(value.get()) {
			setText("ON");
			setStyle("-fx-background-color: green;-fx-text-fill:white;");
			setContentDisplay(ContentDisplay.LEFT);
		} else {
			setText("OFF");
			setStyle("-fx-background-color: grey;-fx-text-fill:black;");
			setContentDisplay(ContentDisplay.RIGHT);
		}
	}

	private void changeValue() {
		value.set(!value.get());
	}


}
