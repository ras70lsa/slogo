package runner;

import frontend_slogo_team04.Display;
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
		Display display = new Display();
		display.start();
		
	}
	
}
