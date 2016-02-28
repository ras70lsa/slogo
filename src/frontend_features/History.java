package frontend_features;



import java.util.ArrayList;
import java.util.List;

import interfaces_slogo_team04.IHistoryModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import visual_states.GuiUserOption;
import visual_states.HistoryUIState;


/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends InteractionListView {

	private String selectedItem;
	private BooleanProperty interacted;
	private HistoryUIState visuals;
	
	public History(IHistoryModel model) {
		super(model.getCommandList(), "History");
		setUp();
		visuals = new HistoryUIState();
		addListeners();
	}
	
	public void setUp() {
		setAction(e->print());
		interacted = new SimpleBooleanProperty();
		selectedItem = "";
	}

	private void addListeners() { 
		visuals.getVisibleProperty().addListener((a,b,c) -> canView(c.booleanValue()));
	}
	private void print() {
		selectedItem = getSelection();
		interacted.set(!interacted.get());
	}
	
	public BooleanProperty getInteracted() {
		return interacted;
	}
	
	public String getSelected() {
		return selectedItem;
	}

}
