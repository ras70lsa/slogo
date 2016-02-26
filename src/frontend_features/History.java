package frontend_features;



import java.util.ArrayList;
import java.util.List;

import constants.DisplayConstants;
import frontend_slogo_team04.GuiUserOption;
import frontend_slogo_team04.HistoryUIState;
import interfaces_slogo_team04.IHistoryModel;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ListView;


/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends InteractionListView {

	private IHistoryModel model;
	private String selectedItem;
	private BooleanProperty interacted;
	
	public History(IHistoryModel model, HistoryUIState visuals) {
		super(model.getCommandList());
		setAction(e->print());
		this.model = model;
		interacted = new SimpleBooleanProperty();
		selectedItem = "";
		addCSS("visual_resources/DefaultHistory.css");
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
		return toRet;
	}

}
