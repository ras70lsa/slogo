package frontend_features;



import java.util.ArrayList;
import java.util.List;

import constants.DisplayConstants;
import interfaces_slogo_team04.IHistoryModel;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import visual_states.GuiUserOption;
import visual_states.HistoryUIState;


/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends InteractionListView {

	private IHistoryModel model;
	private String selectedItem;
	private BooleanProperty interacted;
	private HistoryUIState visuals;
	
	public History(IHistoryModel model) {
		super(model.getCommandList());
		this.model = model;
		setUp();
		visuals = new HistoryUIState();
		addListeners();
	}
	
	public void setUp() {
		setAction(e->print());
		interacted = new SimpleBooleanProperty();
		selectedItem = "";
		addCSS("visual_resources/DefaultHistory.css");
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

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		List<Node> toRet = new ArrayList<Node>();
		toRet.add(factory.get(model.getLanguage(), DisplayConstants.possibleLangauges));
		toRet.add(factory.get(visuals.getVisibleProperty(), "Visible"));
		return toRet;
	}

}
