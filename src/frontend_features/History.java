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

	public static final double LABEL_HEIGHT = 25;
	public static final double SCROLL_BAR = 3.9;
	private IHistoryModel model;
	private HistoryUIState visuals;
	private ListView<String> history;
	private String selectedItem;
	private BooleanProperty interacted;
	
	public History(IHistoryModel model, HistoryUIState visuals) {
		this.model = model;
		interacted = new SimpleBooleanProperty();
		selectedItem = "";
		this.visuals = visuals;
		addCSS("visual_resources/DefaultHistory.css");
		addLabel("History", LABEL_HEIGHT);
	}

	private void print() {
		selectedItem = history.getSelectionModel().getSelectedItem();
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
