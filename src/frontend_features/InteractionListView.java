package frontend_features;

import constants.CSSPathConstants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public abstract class InteractionListView extends VPane {

	private ListView<String> list;
	private BooleanProperty interacted;
	private String selectedText;
	
	
	public InteractionListView(ObservableList<String> tracking, String title) {
		setUp(tracking, title);
		addListeners();
		addCSS(CSSPathConstants.LIST);
	}
	
	private void setUp(ObservableList<String> tracking, String title) {
		add(new Label(getString(title)));
		createListView(tracking);
		list.setPlaceholder(new Label(getString("EmptyPlaceHolder")));
		interacted = new SimpleBooleanProperty();
		selectedText = "";
		setAction();
	}

	protected void setAction() {
		list.setOnMouseClicked(event -> clicked());
		
	}
	
	private void clicked() {
		selectedText = getSelection();
		interacted.set(!interacted.get());
	}
	
	public String getSelectedText() {
		return selectedText;
	}

	protected String getSelection() {
		return list.getSelectionModel().getSelectedItem();
	}
	
	private void createListView(ObservableList<String> tracking) {
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
	
	public BooleanProperty getInteracted() {
		return interacted;
	}

}