package frontend_slogo_team04;

import java.util.Collection;

import constants.DisplayConstants;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is the class responsible for setting up the interaction with the user.
 * It controls its features as modules
 * @author Ryan St Pierre
 *
 */
public class Display {

	private Collection<Module> modules;
	private Stage myStage;
	private Group myRoot;
	private Scene myScene;
	
	public Display() {
		// TODO Auto-generated constructor stub
		setUpScene();
		addPanes();
	}
	
	private void positionModule() {
		
	}
	
	private void createMenuBar() {
		
	}
	
	private void addPanes() {
		Code code = new Code(new TestingState());
		code.getPane().setTranslateX(100);
		code.getPane().setTranslateY(100);
		myRoot.getChildren().add(code.getPane());
		
	}
	
	public void start() {
		myStage.show();
	}
	
	public void setUpScene() {
		
		myStage = new Stage();
		myRoot = new Group();
		myScene = new Scene(myRoot, DisplayConstants.DISPLAY_SIZE, DisplayConstants.DISPLAY_SIZE, 
						DisplayConstants.BACKGROUND_COLOR);
		myStage.setScene(myScene);
	}

}
