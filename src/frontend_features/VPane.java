package frontend_features;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Features with vbox style pane 
 * @author Ryan St Pierre
 */
public abstract class VPane extends ModularPane {

	private VBox vbox;
	
	public VPane() {
		vbox = new VBox();
		vbox.getStyleClass().add("vbox");
	}

	public Region getPane() {
		return vbox;
	}

	public void add(Node node) {
		vbox.getChildren().add(node);
		node.getStyleClass().add("vbox_item");
	}
	
	public void clearBox(){
		vbox.getChildren().clear();
	}
	
	public void updateColor(Color color){
		String hex = toRGBCode(color);
		getPane().setStyle("-fx-background: " + hex);
	}

}
