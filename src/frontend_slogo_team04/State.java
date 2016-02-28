package frontend_slogo_team04;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.stage.Stage;
import visual_states.GuiUserOption;

/**
 * This interface groups values that can be altered by the user or through code based on functionally who needs access
 * @author Ryan St Pierre
 *
 */
public abstract class State {

	protected GuiUserOption getFactory() {
		return new GuiUserOption();
	}
	/**
	 *Possibly to delete
	 */
}
