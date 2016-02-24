package frontend_slogo_team04;


import java.util.Stack;

import backend_slogo_team04.Action;
import interfaces_slogo_team04.State;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends ScrollablePane {

	BooleanProperty interacted = new SimpleBooleanProperty();
	private HistoryState state; 
	String selectedText;
	
	public History(HistoryState hState) {
		this.state = hState;
		addCSS("visual_resources/DefaultHistory.css");
		addListeners();
		selectedText = "";
	}

	private void addListeners() {
		state.getListProperty().addListener((a,b,newInfo) -> updateText(newInfo));
	}
	
	public State getState() {
		return state;
	}

	public void updateText(ObservableList<Action> newInfo) {
		clearBox();
		for(Action a: newInfo) {
			String str = a.getInput();
			DividedText text = new DividedText("holder", str);
			text.setOnMouseClicked(e -> clicked(text.getRight()));
			add(text);
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
