package frontend_features;

import constants.ResourceConstants;
import interfaces_slogo_team04.IHistoryModel;
import visual_states.HistoryUIState;


/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends InteractionListView {

	private HistoryUIState visuals;
	
	public History(IHistoryModel model) {
		super(model.getCommandList(), ResourceConstants.HISTORY_KEY);
		visuals = new HistoryUIState();
		addListeners();
	}

	private void addListeners() { 
		visuals.getVisibleProperty().addListener((a,b,c) -> canView(c.booleanValue()));
	}
	
}
