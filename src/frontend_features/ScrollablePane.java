package frontend_features;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Features with scrollable pane (assumes vBox to populate)
 * @author Ryan St Pierre
 */
public abstract class ScrollablePane extends ModularPane {

	public static final int REMOVE_SCROLL_BIT = 2;
	private ScrollPane pane; 
	private VBox content;
	
	public ScrollablePane() {
		pane = new ScrollPane();
		content = new VBox();
		content.getStyleClass().add("vbox");
		pane.setContent(content);
		setVBoxWidth(getWidth().getValue());
		getWidth().addListener((a,b,c) -> setVBoxWidth(c.doubleValue()));
		
	}

	private void setVBoxWidth(double d) {
		content.setPrefWidth(d- REMOVE_SCROLL_BIT);
	}

	public Region getPane() {
		return pane;
	}

	public void add(Node node) {
		content.getChildren().add(node);
		node.getStyleClass().add("vbox_item");
	}
	
	public void clearBox(){
		content.getChildren().clear();
	}
	
	public void updateColor(Color color){
		String hex = toRGBCode(color);
		getPane().setStyle("-fx-background: " + hex);
	}
	
	public void addLabel(String title, double height) {
		Label label = new Label(title);
		label.setPrefHeight(height);
		add(label);
	}

}
