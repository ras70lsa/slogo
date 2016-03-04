package visual.states;

import frontend.slogo.team04.State;
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