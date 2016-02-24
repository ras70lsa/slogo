package frontend_slogo_team04;

import interfaces_slogo_team04.IState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Code extends StaticPane {

	private Rectangle text;
	private TestingState state;
	private ImageView image;
	
	public Code(TestingState state) {
	
		this.state = state;
		setUp();
		addListeners();
	}
	
	private void addListeners() {
	
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
		state.getImageProperty().addListener((a,b,c) -> updateImageView(c));
	}
	
	
	
	private void updateImageView(Image c) {
		image.setImage(c);
	}

	private void createTextField() {
		image = new ImageView();
		image.setFitWidth(100);
		image.setFitHeight(100);
		add(image, 10, 10);
	}
	
	public void update() { 
		super.update();
	}
	
	public void setUp() {
		createTextField();
	}
	
	@Override
	public IState getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void inputEntered() {

	}

	

}
