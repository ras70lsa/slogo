package frontend.features;

import backend.structures.RGBColor;
import javafx.beans.property.ListProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import properties.ColorProperty;

public class ColorIndexMenu extends ComboBox<RGBColor> {

	private static final int REC_WIDTH = 150;
	private static final int TOTAL_WIDTH = 260;
	private static final int TOTAL_HEIGHT = 50;
	private static final int REC_HEIGHT = 30;
	private static final int HBOX_SPACING = 5;
	private static final int RECTANGLE_TOP_MARGIN = 2;
	private static final int RECTANGLE_RIGHT_MARGIN = 3;
	private static final int RECTANGLE_BOTTOM_MARGIN = 2;
	private static final int RECTANGLE_LEFT_MARGIN = 5;
	private ColorProperty currentColor;

	public ColorIndexMenu(ListProperty<RGBColor> data) {
		super();
		currentColor = new ColorProperty();
		this.setPrefWidth(TOTAL_WIDTH);
		this.setPrefHeight(TOTAL_HEIGHT);
		addChildren(data);
		addSelection();
	}

	private void addSelection() {
		this.setOnAction(e -> {
			currentColor.set(this.getSelectionModel().getSelectedItem());
		});
	}

	public ColorProperty getSelected() {
		return currentColor;
	}

	public void addChildren(ListProperty<RGBColor> data) {
		setItems(data);
		setCellFactory((callback) -> {
				return new ListCell<RGBColor>() {
					public static final int HEIGHT = 10;
					public static final int WIDTH = 50;
					public static final int ALPHA = 1;
					private final Group group;
					private final Label text;
					private final Rectangle rectangle;

					{
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						group = new Group();
						HBox hbox = new HBox(HBOX_SPACING);
						rectangle = new Rectangle(REC_WIDTH, REC_HEIGHT);
						text = new Label();
						hbox.getChildren().addAll(rectangle, text);
						HBox.setMargin(rectangle, new Insets(RECTANGLE_TOP_MARGIN, RECTANGLE_RIGHT_MARGIN,
								RECTANGLE_BOTTOM_MARGIN, RECTANGLE_LEFT_MARGIN));
						text.setPrefHeight(HEIGHT);
						text.setPrefWidth(WIDTH);
						group.getChildren().add(hbox);
					}

					protected void updateItem(RGBColor item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							text.setText(Integer.toString(item.getIndex()));
							rectangle.setFill(new Color(item.getRed(),
									item.getGreen(),
									item.getBlue(), ALPHA));
							setGraphic(group);
						}
					}
				};
			});	
			
		};
}

