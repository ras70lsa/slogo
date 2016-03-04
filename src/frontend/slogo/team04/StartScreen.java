package frontend.slogo.team04;

import constants.DisplayConstants;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreen extends Screen {

	private WorkspaceManager workspaces;
	
	public StartScreen(double width, double height, WorkspaceManager workspaces) {
		super(width, height, new Stage(), workspaces);
		this.workspaces = workspaces;
		setUpScene();
	}
	
	public void setUpScene() {
		addStartButton();
	}

	private void addStartButton() {
		Button start = new Button(getString("Start"));
		start.setLayoutX(DisplayConstants.START_WIDTH/2 - start.getLayoutBounds().getWidth() / 2);
		start.setLayoutY(DisplayConstants.START_HEIGHT/2 - start.getLayoutBounds().getHeight()/ 2);
		add(start);
		start.setOnAction(e -> move());
		
	}

	private void move() {
		workspaces.begin();
		getStage().close();
	}
	
}
