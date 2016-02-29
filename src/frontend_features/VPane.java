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
	}

	public Region getPane() {
		return vbox;
	}

	public void add(Node node) {
		vbox.getChildren().add(node);
	}
	
	public void clearBox(){
		vbox.getChildren().clear();
	}

}
