package frontend_slogo_team04;


import java.util.Stack;

import backend_slogo_team04.Action;
import interfaces_slogo_team04.State;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * History Feature
 * @author Ryan St Pierre
 */

public class History extends ScrollablePane {

	private HistoryState state; 
	public History(HistoryState hState) {
		this.state = hState;
		addListeners();
		setUp();
		addCSS("visual_resources/DefaultHistory.css");
	}

	private void addListeners() {
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
		state.getListProperty().addListener((a,b,newInfo) -> updateText(newInfo));
	}
	
	public void setUp() {
		
		String shor = "Ryan";
		DividedText r = new DividedText(shor, shor);
		add(r);
		String lon = "Ryan\nTayla Nick";
		DividedText r2 = new DividedText(lon, lon);
		add(r2);
		r.setW(100);
		r2.setW(100);
		
		
		
		
	}
	
	public State getState() {
	
		return state;
	}

	public void updateText(ObservableList<Action> newInfo) {
		//clearBox();
//		for(Action a: newInfo) {
//			Button b = new Button(a.getInput());
//			b.setOnMouseClicked(e -> state.addToStack());
//			add(b);
//		}	
	}

}
