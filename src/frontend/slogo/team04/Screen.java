package frontend.slogo.team04;

import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Screen {

	private Stage myStage; 
	private Group myGroup;
	private Scene myScene;
	private ResourceBundle myBundle;
	private WorkspaceManager workspaces;
	
	public Screen(double width, double height, Stage myStage, WorkspaceManager workspaces) { 
		this.myStage = myStage;
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		this.workspaces = workspaces;
		setUp(width, height);
	}
	
	public void start() {
		myStage.show();
	}
	
	public void setUp(double width, double height) {
		
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
	
	protected String getString(String check) {
		return myBundle.getString(check);
	}
	
	protected void newWorkspace() {
		workspaces.go();
		showMainStage();
	}
	
	protected Stage getStage() {
		return myStage;
	}
	
	protected Scene getScene() {
		return myScene;
	}
	
	protected void showMainStage() {
		workspaces.start();
	}
	
}
