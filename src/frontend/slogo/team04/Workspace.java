package frontend.slogo.team04;


import constants.DisplayConstants;
import model.Controller;
import model.Model;

public class Workspace {

	public void start(WorkspaceManager space) {
		Model model = new Model();
		Controller controller = new Controller(model.getCommunicator(), model.getView());
		Display display = new Display(model, controller, DisplayConstants.DISPLAY_WIDTH, DisplayConstants.DISPLAY_HEIGHT,
				space);
		display.start();
		
	}

	
}
