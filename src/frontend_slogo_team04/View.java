package frontend_slogo_team04;

import interfaces_slogo_team04.State;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class View extends ModularPane {

	private ImageView turtle;
	private TestingState state;
	
	public View(TestingState state) { 
		this.state = state;
		setUp();
		addListeners();
	}
	
	private void addListeners() {
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
	}
	
	private Object updateColor(Color newValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public void draw() {
		
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

}