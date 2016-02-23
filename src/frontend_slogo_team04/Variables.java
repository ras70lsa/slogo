package frontend_slogo_team04;

import interfaces_slogo_team04.IState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Variables extends ScrollablePane {

	private Rectangle text;
	private TestingState state;
	private ImageView image;
	
	public Variables(TestingState state) {
	
		this.state = state;
		setUp();
		addListeners();
		addCSS("visual_resources/DefaultVariables.css");
		
	}
	
	private void addListeners() {
	
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
		state.getImageProperty().addListener((a,b,c) -> updateImageView(c));
	}
	
	
	
	private void updateImageView(Image c) {
		image.setImage(c);
	}

	private void createTextField() {
		
		for(int i =0; i <10 ; i++) {
			Text text = new Text("test" + " " + i);
			text.getStyleClass().add("text");
			add(text);
		}
		
	}
	
	public void update() { 
		super.update();
	}
	
	public void setUp() {
		createTextField();
	}

	public IState getState() {
		
		return state;

	}
	
	public void inputEntered() {

	}

	

}
