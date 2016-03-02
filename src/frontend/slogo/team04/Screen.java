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
	
	public Screen(double width, double height) { 
		setUp(width, height);
	}
	
	public void start() {
		myStage.show();
	}
	
	public void setUp(double width, double height) {
		
		myStage = new Stage();
		myGroup = new Group();
		myScene = new Scene(myGroup,width, height, 
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
