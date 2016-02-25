package frontend_slogo_team04;

import backend_slogo_team04.Controller;
import constants.DisplayConstants;
import interfaces_slogo_team04.IState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class View extends ModularPane {

	private Controller myController;
	private ImageView turtle;
	private TestingState state;
	private Pane myDisplay;
	private boolean penDown = true;
	
	public View(TestingState state) { 
		this.state = state;
		setUp();
		addListeners();
	}
	
	private void addListeners() {
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
	}
	

	public void draw() {
		
	}
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		Pane newDisplay = new Pane();
		//newDisplay.setPrefSize(width, height);
		newDisplay.setPrefSize(DisplayConstants.DISPLAY_SIZE, DisplayConstants.DISPLAY_SIZE);
		setDisplay(newDisplay);
		setTurtleImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle.jpg")));
		getDisplay().getChildren().add(turtle);
	}

	@Override
	public IState getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
    private Pane getDisplay () {
        return myDisplay;
    }

    private void setDisplay (Pane display) {
        this.myDisplay = display;
    }

	private Object updateColor(Color newValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
    private void setTurtleImage(Image i){
    	turtle.setImage(i);
    }
    
}

