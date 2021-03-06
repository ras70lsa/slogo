package frontend.features;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

/**
 * Feature with pane
 * @author Ryan St Pierre
 */
public abstract class StaticPane extends ModularPane {

	private Pane pane; 
	
	public StaticPane() {
		pane = new Pane();
		pane.getStyleClass().add("pane");
	}
	
	public Region getPane() {
		return pane;
	}
	
	public void setPane(VBox display){
		this.pane = display;
	}
	
	public void remove(Node node){
		pane.getChildren().remove(node);
	}
	
	public void add(Node node, double x, double y) {
		pane.getChildren().add(node);
		node.setTranslateX(x);
		node.setTranslateY(y);
	}
	
	public void add(Node node) {
		pane.getChildren().add(node);
	}
	
	public void addLine(Line node){
		pane.getChildren().add(node);
	}

	public void clear() {
		pane.getChildren().clear();
	}

}
