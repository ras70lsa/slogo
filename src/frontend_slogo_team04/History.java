package frontend_slogo_team04;


import java.util.Stack;

import backend_slogo_team04.Action;
import interfaces_slogo_team04.State;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class History extends ScrollablePane {

	private HistoryState state; 
	public History(HistoryState hState) {
		this.state = hState;
		addListeners();
		setUp();
	}

	private void addListeners() {
		state.getColorProperty().addListener((a,b,newValue) -> updateColor(newValue));
		state.getListProperty().addListener((a,b,newInfo) -> updateText(newInfo));
	}
	
	public void setUp() {
		
		Button r = new Button("Text");
		add(r);
		Button t = new Button("Text");
		add(t);
	}
	

	public State getState() {
	
		return state;
	}

	public void updateText(ObservableList<Double> newInfo) {
		//System.out.println(newInfo.toString());
		
	}

}
