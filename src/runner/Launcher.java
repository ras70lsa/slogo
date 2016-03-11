package runner;


import frontend.slogo.team04.WorkspaceManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launches the simulation application
 * @author: Ryan St Pierre
 */
public class Launcher extends Application{

	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		WorkspaceManager workspaces = new WorkspaceManager();
		workspaces.begin();
		
	}
	
}
