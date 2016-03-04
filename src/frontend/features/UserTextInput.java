package frontend.features;


import constants.CSSPathConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Controller;

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
		addCSS(CSSPathConstants.INPUT);
	}
	
	private void createTextField() {
		textArea = new TextArea();
        textArea.getStyleClass().add(CSSPathConstants.TEXT_AREA);
        add(textArea);
	}
	
	public void setUp() {
		createButtons();
		createTextField();
	}

	private void createButtons() {
		HBox hbox = new HBox();
		hbox.getChildren().add(createButton(getString("Go"), e-> inputEntered()));
		hbox.getChildren().add(createButton(getString("AddActor"), e-> addActor()));
		add(hbox);
	}

	private void addActor() {
		controller.addActor();
	}

	private Button createButton(String name, EventHandler<ActionEvent> event) {
		Button go = new Button(name);
		go.setOnAction(event);
		return go;
	}
	
	public void inputEntered() {
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
