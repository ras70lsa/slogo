package frontend_features;

import java.util.List;

import frontend_slogo_team04.GuiUserOption;
import interfaces_slogo_team04.ICommands;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class CommandFeature extends StaticPane {
	
	ICommands model;
	private ListView<String> commands;
	
	public CommandFeature(ICommands model) {
		this.model = model;
		addCSS("visual_resources/DefaultCommands.css");
	}

	private void print() {
		System.out.println(commands.getSelectionModel().getSelectedItem());
		
	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return null;
	}

}
