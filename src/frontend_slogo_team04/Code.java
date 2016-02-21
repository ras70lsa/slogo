package frontend_slogo_team04;


import exceptions.UserInputException;
import interfaces_slogo_team04.State;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class Code extends ModularPane {

	private Rectangle text;
	private TestingState state;
	
	public Code(TestingState state) {
		// TODO Auto-generated constructor stub
		this.state = state;
		createTextField();
	}

	private void createTextField() {
		text = new Rectangle(100, 100);
		text.setFill(state.getColor());
		text.setOnMouseClicked(e -> update());
		add(text, 0, 0);
	}
	
	public void update() { 
		state.getUserOptions().show();
		text.setFill(state.getColor());
	}
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void inputEntered() {
//		if(text.getText() == null) {
//			throw new UserInputException("No input");
//		}
	};

}
