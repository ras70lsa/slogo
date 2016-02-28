package frontend_features;

import java.util.List;

import constants.DisplayConstants;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.Controller;
import visual_states.GuiUserOption;

/**
 * Text input feature (for code)
 * @author Ryan St Pierre
 */

public class UserTextInput extends StaticPane {

	private TextArea textArea;
	private Controller controller;
	
	public UserTextInput(Controller controller) {
		this.controller = controller;
		setUp();
		addCSS("visual_resources/DefaultInputBox.css");
	}
	
	private void createTextField() {
		textArea = new TextArea();
        textArea.getStyleClass().add("TextArea");
        textArea.setScrollTop(10);
        textArea.setPrefHeight(DisplayConstants.TEXT_HEIGHT);
        textArea.setPrefWidth(DisplayConstants.TEXT_WIDTH);
        add(textArea, 0, 0);
	}
	
	public void setUp() {
		createTextField();
		createGoButton();
	}

	private void createGoButton() {
		Button go = new Button("GO");
		go.getStyleClass().add("button");
		go.setOnAction(e -> inputEntered());
		go.setPrefWidth(50);
		add(go, DisplayConstants.TEXT_WIDTH - go.getPrefWidth(), 0); 
		
	}
	
	public void inputEntered() {	
		controller.parseString(textArea.getText());
	}

	public void setText(String selectedText) {
		textArea.setText(selectedText);
	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return null;
	}

	public void append(String text) {
		textArea.setText(textArea.getText() + " " + text);
	}
	

}
