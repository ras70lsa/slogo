package frontend_features;

import java.util.List;

import interfaces_slogo_team04.ICommands;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import visual_states.GuiUserOption;

public class CommandFeature extends InteractionListView {
	
	private BooleanProperty interacted;
	private String selectedText;
	
	public CommandFeature(ICommands model) {
		super(model.getCommands());
		setAction(e-> passToBox());
		addCSS("visual_resources/DefaultCommands.css");
		interacted = new SimpleBooleanProperty();
	}

	private void passToBox() {
		selectedText = getSelection();
		interacted.set(!interacted.get());
	}
	
	public String getText() {
		return selectedText;
	}
	
	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return null;
	}
	
	public BooleanProperty getInteracted() {
		return interacted;
	}

}
