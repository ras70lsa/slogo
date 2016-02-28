package frontend_features;

import java.util.List;

import constants.DisplayConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
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

public class UserTextInput extends VPane {

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
        add(textArea);
	}
	
	public void setUp() {
		createGoButton();
		createTextField();
	}

	private void createGoButton() {
		Button go = new Button("GO");
		go.getStyleClass().add("button");
		go.setOnAction(e -> inputEntered());
		go.setPrefWidth(50);
		add(go); 
	}
	
	public void inputEntered() {
		System.out.println(textArea.getText());
		try {
			controller.parseString(textArea.getText());
			textArea.clear();
		} catch (UserInputException|LogicException e) {
			AlertMessage alert = new AlertMessage(e.getMessage());
			alert.displayError();
		} 
	}

	public void setText(String selectedText) {
		textArea.setText(selectedText);
	}

	public void append(String text) {
		if(text == null) {
			return;
		}
		textArea.setText(textArea.getText() + " " + text);
	}
	

}
