package visual_states;

import frontend_slogo_team04.State;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class HistoryUIState extends State {

	BooleanProperty canSee;
	
	public HistoryUIState() {
		canSee = new SimpleBooleanProperty(true);
	}
	
	public BooleanProperty getVisibleProperty() {
		return canSee;
	}

}