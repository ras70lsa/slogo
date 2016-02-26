package frontend_slogo_team04;


import java.util.ResourceBundle;
import java.util.Stack;

import backend_slogo_team04.Action;
import backend_slogo_team04.SlogoScanner;
import constants.DisplayConstants;
import interfaces_slogo_team04.IHistoryModel;
import interfaces_slogo_team04.IModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Model;

/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends ScrollablePane {

	private IHistoryModel model;
	private HistoryUIState visuals;
	ListView<String> commands;
	
	public History(IHistoryModel model, HistoryUIState visuals) {
		this.model = model;
		this.visuals = visuals;
		addCSS("visual_resources/DefaultHistory.css");
		makeTextList();
		
	}

	private void makeTextList() {
		commands = new ListView<String>();
		commands.setItems(model.getCommandList());
		commands.setOnMouseClicked(e -> print());
		add(commands);
		model.add("Forward");
	}

	private void print() {
		String str = commands.getSelectionModel().getSelectedItem();
		System.out.println(str);
	}

	public State getState() {
		return visuals;
	}

}
