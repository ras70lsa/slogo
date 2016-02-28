package frontend_features;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public abstract class InteractionListView extends VPane {

	ListView<String> list;
	
	public InteractionListView(ObservableList<String> tracking, String title) {
		add(new Label(title));
		createListView(tracking);
		addListeners();
		addCSS("visual_resources/DefaultList.css");
		list.setPlaceholder(new Label("No Content"));
	}
	
	protected void setAction(EventHandler<MouseEvent> e) {
		list.setOnMouseClicked(e);
	}
	
	protected String getSelection() {
		return list.getSelectionModel().getSelectedItem();
	}
	
	private void createListView(ObservableList<String> tracking) {
		list = new ListView<String>();
		list = new ListView<String>();
		list.setVisible(true);
		list.setItems(tracking);
		add(list);
		
	}
	
	protected void canView(boolean interact) {
		list.setVisible(interact);
	}
	
	private void addListeners() {
		getWidth().addListener((a,b,w) -> resize(w.doubleValue(), getHeight().get()));
		getHeight().addListener((a,b,h) -> resize(getWidth().get(),h.doubleValue()));
	}
	
	private void resize(double width, double height) {
		list.setPrefSize(width, height);
	}

}