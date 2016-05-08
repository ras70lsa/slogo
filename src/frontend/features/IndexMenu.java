// This entire file is part of my masterpiece.
// YAQI GUO

package frontend.features;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class IndexMenu<T> extends ComboBox<T>{
	public IndexMenu() {
		super();
	}
	
	protected void addSelection(EventHandler<ActionEvent> e) {
		this.setOnAction(e);
	}
	
	public void addChildren(ObservableList<T> input, Callback<ListView<T>, ListCell<T>> myCellFactory) {
		setItems(input);
		setCellFactory(myCellFactory);
	}
	
}
