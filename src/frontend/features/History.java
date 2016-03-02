package frontend.features;

import constants.ResourceConstants;
import interfaces.slogo.team04.IHistoryModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import visual_states.HistoryUIState;


/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends InteractionListView {

	private HistoryUIState visuals;
	private BooleanProperty execute;
	
	public History(IHistoryModel model) {
		super(model.getCommandList(), ResourceConstants.HISTORY_KEY);
		visuals = new HistoryUIState();
		execute = new SimpleBooleanProperty();
		addListeners();
		addSecondaryAction();
	}

	private void addSecondaryAction() {
		getList().setOnMouseClicked(e -> rightClick(e));
	}

	private void rightClick(MouseEvent e) {
		if(e.getButton() != MouseButton.SECONDARY) {
			primaryClicked();
			return;
		}
		showContextMenu(e.getSceneX(), e.getSceneY());
	}

	private void showContextMenu(double x, double y) {
		ContextMenu menu = new ContextMenu();
		MenuItem item1 = new MenuItem(getString("Go"));
		MenuItem item2 = new MenuItem(getString("ToWindow"));
		menu.getItems().addAll(item1, item2);
		item1.setOnAction(event -> run());
		item2.setOnAction(event -> primaryClicked());
		getList().setContextMenu(menu);
	}

	private void run() {
		setSelectedText(getList().getSelectionModel().getSelectedItem());
		execute.set(!execute.get());
	}
	
	public BooleanProperty getExecute() {
		return execute;
	}

	private void addListeners() { 
		visuals.getVisibleProperty().addListener((a,b,c) -> canView(c.booleanValue()));
	}
	
}
