package frontend_slogo_team04;

import interfaces_slogo_team04.State;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class ScrollablePane extends ModularPane {

	ScrollPane pane; 
	VBox content;
	
	public ScrollablePane() {
		pane = new ScrollPane();
		content = new VBox();
		pane.setContent(content);
	}

	public Region getPane() {
		return pane;
	}

	public void add(Node node) {
		
		content.getChildren().add(node);
		
	}
	
	public void updateColor(Color color){
		String hex = toRGBCode(color);
		pane.setStyle("-fx-background-color: " + hex);
	}

}
