package frontend_slogo_team04;


import java.util.ResourceBundle;
import java.util.Stack;

import backend_slogo_team04.Action;
import backend_slogo_team04.Model;
import backend_slogo_team04.SlogoScanner;
import constants.DisplayConstants;
import interfaces_slogo_team04.State;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends ScrollablePane {

	private BooleanProperty interacted = new SimpleBooleanProperty();
	private HistoryState state; 
	private String selectedText;
	private HistoryUIState visuals;
	
	public History(HistoryState hState, HistoryUIState visuals) {
		this.state = hState;
		this.visuals = visuals;
		addListeners();
		addCSS("visual_resources/DefaultHistory.css");
		selectedText = "";
		
		
	}

	private void addListeners() {
		state.getListProperty().addListener((a,b,newInfo) -> 
			updateText(newInfo, visuals.getLanguageProperty().get()));
		visuals.getColorProperty().addListener((a,b,c) -> updateColor(c));
		visuals.getLanguageProperty().addListener((a,b,c) -> 
			updateText(state.getListProperty(), c));
	}
	
	public State getState() {
		return visuals;
	}

	public void updateText(ObservableList<Action> newInfo, String language) {
		clearBox();
		ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + language);
		for(Action a: newInfo) {
			String str = a.getInput();
			SlogoScanner scan = new SlogoScanner(str);
			String toAdd = scan.getCodeConvertToLanguage(myBundle);
			DividedText divided = new DividedText(toAdd, toAdd);
			divided.setOnMouseClicked(e -> clicked(divided.getRight()));
			add(divided);
		}	
		
	}
	
	private void clicked(Text right) {
		selectedText = right.getText();
		interacted.set(!interacted.get());
	}
	
	public String getSelectedText() {
		return selectedText;
	}
	
	public BooleanProperty getInteracted() {
		return interacted;
	}

}
