package frontend_slogo_team04;

import interfaces_slogo_team04.State;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class History extends ScrollablePane {

	TestingState state; 
	public History(TestingState state) {
		// TODO Auto-generated constructor stub
		this.state = state;
		addListeners();
		setUp();
	}

	private void addListeners() {
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
	}
	
	public void setUp() {
		
		Button r = new Button("Text");
		add(r);
		Button t = new Button("Text");
		add(t);
	}
	
	public State getState() {
		return state;
	}

}
