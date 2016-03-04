package frontend.features;

import constants.ResourceConstants;
import interfaces.slogo.team04.ICommands;

public class CommandFeature extends InteractionListView {
	
	public CommandFeature(ICommands model) {
		super(model.getCommands(), ResourceConstants.COMMAND_KEY);
	}
}
