package runner;


import constants.DisplayConstants;
import frontend.slogo.team04.Display;
import frontend.slogo.team04.StartScreen;
import frontend.slogo.team04.WorkspaceManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Controller;
import model.Model;

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
		//Model model = new Model();
		//Controller controller = new Controller(model.getCommunicator(), model.getView());
		//Display display = new Display(model, controller, DisplayConstants.DISPLAY_WIDTH, DisplayConstants.DISPLAY_HEIGHT,
		//		workspaces);
		StartScreen start = new StartScreen(DisplayConstants.START_WIDTH, DisplayConstants.START_WIDTH, workspaces);
		start.start();
		//display.start();
		
	}
	
}
