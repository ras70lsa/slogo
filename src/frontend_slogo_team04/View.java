package frontend_slogo_team04;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class View extends StaticPane {

	private ImageView turtle;
	private TestingState state;
	
	public View(TestingState state) { 
		this.state = state;
		addListeners();
		addCSS("visual_resources/DefaultView.css");
	}
	
	private void addListeners() {
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
	}
	
	public void draw() {
		
	}

	public State getState() {
		return state;
	}

	

}
