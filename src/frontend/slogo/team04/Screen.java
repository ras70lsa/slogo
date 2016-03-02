package frontend.slogo.team04;

import constants.DisplayConstants;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Screen {

	private Stage myStage; 
	private Group myGroup;
	private Scene myScene;
	
	public Screen() { 
		setUp();
	}
	
	public void start() {
		myStage.show();
	}
	
	public void setUp() {
		
		myStage = new Stage();
		myGroup = new Group();
		myScene = new Scene(myGroup, DisplayConstants.DISPLAY_WIDTH, DisplayConstants.DISPLAY_HEIGHT, 
						DisplayConstants.BACKGROUND_COLOR);
		myStage.setScene(myScene);
	}
	
	protected Group getGroup() {
		return myGroup;
	}
	
	protected void add(Node node) {
		myGroup.getChildren().add(node);
	}
	
}
