package interfaces_slogo_team04;
import java.util.Collection;

import frontend_slogo_team04.GuiUserOption;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * This interface groups values that can be altered by the user or through code based on functionally who needs access
 * @author Ryan St Pierre
 *
 */
public abstract class State {

	private Group myGroup;
	GuiUserOption factory;
	public State() {
		factory = new GuiUserOption();
	}
	/**
	 * 
	 * @return A Stage with options for the user to alter regarding the state
	 */
	public Stage getUserOptions() {
		Stage stage = getEmptyState();
		positionNodes();
		return stage;

	}

	private void positionNodes() {
		int counter = 10;
		for(Node node: getStageNodes()) {
			myGroup.getChildren().add(node);
			node.setTranslateX(140);
			node.setTranslateY(counter);
			counter = counter + 50;
		}
		
	}

	protected abstract Collection<Node> getStageNodes();
	
	private Stage getEmptyState() {
		myGroup = new Group();
		Scene myScene = new Scene(myGroup, 300, 300, Color.BEIGE);
		Stage stage = new Stage();
		stage.setScene(myScene);
		return stage;
	}
	
	protected GuiUserOption getFactory() {
		return factory;
	}
	
	
	/**
	 * Each instance of the State interface will have the proper getters and setters to save and alter state
	 * This cannot be done generically in the State interface because there is no way of knowing which type these values 
	 * will take
	 */
}
