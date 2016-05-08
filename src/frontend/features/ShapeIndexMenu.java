package frontend.features;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.shape.Shape;
import javafx.util.Callback;


public class ShapeIndexMenu extends IndexMenu<TurtleShape>{
	private static final int TOTAL_WIDTH = 260;
	private static final int TOTAL_HEIGHT = 50;
	private SimpleIntegerProperty selectedIndex;
	
	public ShapeIndexMenu() {
		super();
		selectedIndex = new SimpleIntegerProperty();
		this.setPrefWidth(TOTAL_WIDTH);
		this.setPrefHeight(TOTAL_HEIGHT);
		ObservableList<TurtleShape> allShapes = FXCollections.observableArrayList();
		allShapes.addAll(TurtleShape.values());
		addChildren(allShapes,myCellFactory);
		addSelection(e -> {
			String selectedShape = this.getSelectionModel().getSelectedItem().toString();
			selectedIndex.set(TurtleShape.valueOf(selectedShape).ordinal());
		});
	}


	Callback<ListView<TurtleShape>, ListCell<TurtleShape>> myCellFactory = new Callback<ListView<TurtleShape>, ListCell<TurtleShape>>() {
		@Override
		public ListCell<TurtleShape> call(ListView<TurtleShape> p) {
			return new ListCell<TurtleShape>() {
				protected void updateItem(TurtleShape item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setGraphic(null);
					} else {
						Shape shape = this.getShape(item);
						setText(Integer.toString(TurtleShape.valueOf(item.toString()).ordinal()+1));
						setGraphic(shape);
					}
				}
				
				public Shape getShape(TurtleShape item){
					return item.getShape();
				}
			};
		}
	};
	
	public SimpleIntegerProperty getSelected() {
		return selectedIndex;
	}
}
