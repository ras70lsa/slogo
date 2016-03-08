package frontend.slogo.team04;

import constants.DisplayConstants;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Controller;
import model.Model;

public class Workspace {

	Display display;
	Model model;
	public Workspace(WorkspaceManager space, Stage stage) {
		model = new Model();
		Controller controller = new Controller(model.getCommunicator(), model.getView());
		display = new Display(model, controller, DisplayConstants.DISPLAY_WIDTH, DisplayConstants.DISPLAY_HEIGHT,
				stage, space);
	}

	public GridPane getGridPane() {
		return display.getGridPane();
	}
	
	public Model getModel() {
		return model;
	}
	
	
	
}
