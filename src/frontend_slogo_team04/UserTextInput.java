package frontend_slogo_team04;

import java.util.ResourceBundle;

import backend_slogo_team04.Controller;
import backend_slogo_team04.SlogoScanner;
import constants.DisplayConstants;
import interfaces_slogo_team04.State;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Text input feature (for code)
 * @author Ryan St Pierre
 */

public class UserTextInput extends StaticPane {

	private UserTextInputState state;
	private TextArea textArea;
	private String language;
	Controller controller;
	
	public UserTextInput(UserTextInputState state) {
		this.state = state;
		language = "English";
		setUp();
		addListeners();
		addCSS("visual_resources/DefaultInputBox.css");
	}
	
	private void addListeners() {
	
		state.getLanguageProperty().addListener((a,b,c) -> setLanguage(c));
	}

	private void setLanguage(String newValue) {
		language= newValue;
		System.out.println(language);
	}

	private void createTextField() {
		textArea = new TextArea();
        textArea.getStyleClass().add("TextArea");
        textArea.setScrollTop(10);
        textArea.setPrefHeight(DisplayConstants.TEXT_HEIGHT);
        textArea.setPrefWidth(DisplayConstants.TEXT_WIDTH);
        add(textArea, 0, 0);
       
	}
	
	public void update() { 
		super.update();
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
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public State getState() {
		return state;
	}
	
	public void inputEntered() {	
		ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + language);
		SlogoScanner reader = new SlogoScanner(textArea.getText());
		controller.parseString(reader.getLanguageConvertedCode(myBundle));
	}
	

}
