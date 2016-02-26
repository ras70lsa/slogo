package frontend_features;

import javafx.scene.control.ListView;

public abstract class InteractionListView extends ScrollablePane {

	ListView<String> list;
	
	public InteractionListView() {
		createListView();
	}
	
	private void createListView() {
		list = new ListView<String>();
		list = new ListView<String>();
		list.setVisible(true);
		list.setItems();
		list.setOnMouseClicked(e -> print());
		add(commands, 0, 0);
		
	}
	
	private void addListeners() {
		getWidth().addListener((a,b,w) -> resize(w.doubleValue(), getHeight().get()));
		getHeight().addListener((a,b,h) -> resize(getWidth().get(),h.doubleValue()));
	}
	
	private void resize(double width, double height) {
		commands.setPrefSize(width, height);
	}

}