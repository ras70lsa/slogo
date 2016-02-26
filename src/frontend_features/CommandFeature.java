package frontend_features;

import java.util.List;

import frontend_slogo_team04.GuiUserOption;
import interfaces_slogo_team04.ICommands;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class CommandFeature extends InteractionListView {
	
	public CommandFeature(ICommands model) {
		super(model.getCommands());
		setAction(e->print());
		addCSS("visual_resources/DefaultCommands.css");
	}

	private void print() {
		System.out.println(getSelection());	
	}
	
	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return null;
	}

}
