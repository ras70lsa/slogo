package frontend.features;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import constants.ResourceConstants;
import interfaces.slogo.team04.IHistoryModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import visual.states.HistoryUIState;


/**
 * Class responsible for displaying the history of code the user has entered
 * @author Ryan St Pierre
 */

public class History extends InteractionListView {

	private HistoryUIState visuals;
	private BooleanProperty execute;
	
	public History(IHistoryModel model) {
		super(model.getCommandList(), ResourceConstants.HISTORY_KEY);
		visuals = new HistoryUIState();
		execute = new SimpleBooleanProperty();
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
		HistoryContextMenu context = new HistoryContextMenu();
		getList().setContextMenu(context.getMenu());
	}
	
	/**
	 * HistoryContextMenu holds the context menu that display when the user right clicks on the feature
	 * It is in its own class to add potential greater functionality if desired
	 * @author RyanStPierre
	 *
	 */
	private class HistoryContextMenu {
		
		ContextMenu menu;
		
		Map<String, EventHandler<ActionEvent>> contents = new TreeMap<>();{
		     contents.put(getString("Go"), event-> run());
		     contents.put(getString("ToWindow"), event-> primaryClicked());
		};
		
		public HistoryContextMenu() {
			menu = new ContextMenu();
			setUpContent();
		}
		
		private void setUpContent() {
			for(Entry<String, EventHandler<ActionEvent>> entry: contents.entrySet()) {
				menu.getItems().add(createMenuItem(entry.getKey(), entry.getValue()));
			}
		}

		private MenuItem createMenuItem(String title, EventHandler<ActionEvent> event) {
			MenuItem item = new MenuItem(title);
			item.setOnAction(event);
			return item;
		}

		public ContextMenu getMenu() {
			return menu;
		}
	}

	private void run() {
		setSelectedText(getList().getSelectionModel().getSelectedItem());
		execute.set(!execute.get());
	}
	
	public BooleanProperty getExecute() {
		return execute;
	}
	
}
