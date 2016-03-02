package runner;


import constants.DisplayConstants;
import frontend.slogo.team04.Display;
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
		Model model = new Model();
		Controller controller = new Controller(model.getCommunicator(), model.getView());
		Display display = new Display(model, controller, DisplayConstants.DISPLAY_WIDTH, DisplayConstants.DISPLAY_HEIGHT);
		display.start();
		
	}
	
}
