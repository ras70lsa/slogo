package frontend_slogo_team04;

import java.util.ResourceBundle;

import interfaces_slogo_team04.IState;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class UserTextInput extends StaticPane {

	private UserTextInputState state;
	TextArea textArea;
	
	public UserTextInput(UserTextInputState state) {
		this.state = state;
		setUp();
		addListeners();
	}
	
	private void addListeners() {
	
		
	}

	private void createTextField() {
		textArea = new TextArea();
        textArea.setPrefWidth(300);
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
		go.setOnAction(e -> inputEntered());
		add(go, 300 - go.getWidth(), 0); 
		
	}

	public IState getState() {
		return state;
	}
	
	public void inputEntered() {
		System.out.println(textArea.getText());
	}
	

}
