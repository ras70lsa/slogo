package frontend_features;

import java.util.List;

import interfaces_slogo_team04.ICommands;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import visual_states.GuiUserOption;

public class CommandFeature extends InteractionListView {
	
	private BooleanProperty interacted;
	private String selectedText;
	
	public CommandFeature(ICommands model) {
		super(model.getCommands(), "Commands");
		setAction(e-> passToBox());
		interacted = new SimpleBooleanProperty();
	}

	private void passToBox() {
		selectedText = getSelection();
		interacted.set(!interacted.get());
	}
	
	public String getText() {
		return selectedText;
	}
	
	public BooleanProperty getInteracted() {
		return interacted;
	}

}
