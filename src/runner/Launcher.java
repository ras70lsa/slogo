package runner;

import backend_slogo_team04.Controller;
import frontend_slogo_team04.Display;
import frontend_slogo_team04.History;
import frontend_slogo_team04.HistoryState;
import frontend_slogo_team04.TestingState;
import frontend_slogo_team04.UserTextInput;
import frontend_slogo_team04.UserTextInputState;
import frontend_slogo_team04.Variables;
import frontend_slogo_team04.View;
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
		UserTextInputState textState = new UserTextInputState();
		HistoryState hState = new HistoryState();
		UserTextInput textInput = new UserTextInput(textState);
		History history = new History(hState);
		Controller controller = new Controller(textState, hState);
		textInput.setController(controller);
		View view = new View(new TestingState());
		Variables variables = new Variables(new TestingState());
		Display display = new Display(textInput, history, view, variables);
		display.start();
		
	}
	
}
