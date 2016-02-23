package frontend_slogo_team04;

import java.util.ResourceBundle;

import backend_slogo_team04.Controller;
import backend_slogo_team04.SlogoScanner;
import constants.DisplayConstants;
import interfaces_slogo_team04.IState;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class UserTextInput extends StaticPane {

	private UserTextInputState state;
	private TextArea textArea;
	private String language;
	
	public UserTextInput(UserTextInputState state) {
		this.state = state;
		language = "English";
		setUp();
		addListeners();
		addCSS("visual_resources/DefaultDisplay.css");
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
        textArea.setPrefWidth(300);
        textArea.getStyleClass().add("TextArea");
        textArea.setPrefHeight(150);
        textArea.setScrollTop(100);
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
		add(go, 300 - go.getWidth(), 0); 
		
	}

	public IState getState() {
		return state;
	}
	
	public void inputEntered() {	
		ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + language);
		SlogoScanner reader = new SlogoScanner(textArea.getText());
		Controller controller = new Controller();
		controller.parseString(reader.getLanguageConvertedCode(myBundle));
	}
	

}
