package frontend_features;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class InteractionListView extends TitlePaneFeature {

	private ListView<String> list;
	private BooleanProperty interacted;
	private String selectedText;
	
	public InteractionListView(ObservableList<String> tracking, String title) {
		super(title);
		setUp(tracking, title);
	}
	
	private void setUp(ObservableList<String> tracking, String title) {
		createListView(tracking);
		list.setPlaceholder(new Label(getString("EmptyPlaceHolder")));
		interacted = new SimpleBooleanProperty();
		selectedText = "";
		setAction();
	}

	protected void setAction() {
		list.setOnMouseClicked(event -> clicked(event));
	}
	
	protected void clicked(MouseEvent event) {
		if(event.getButton() != MouseButton.PRIMARY) {
			return; // do nothing
		}
		primaryClicked();
	}
	
	protected void primaryClicked() {
		selectedText = getSelection();
		interacted.set(!interacted.get());
	}

	public String getSelectedText() {
		return selectedText;
	}
	
	protected void setSelectedText(String input) {
		selectedText = input;
	}

	protected String getSelection() {
		return list.getSelectionModel().getSelectedItem();
	}
	
	private void createListView(ObservableList<String> tracking) {
		list = new ListView<String>();
		list.setVisible(true);
		list.setItems(tracking);
		setContent(list);
	}
	
	protected void canView(boolean interact) {
		list.setVisible(interact);
	}
	
	public BooleanProperty getInteracted() {
		return interacted;
	}
	
	protected ListView<String> getList() {
		return list;
	}

}