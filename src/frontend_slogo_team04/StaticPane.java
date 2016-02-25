package frontend_slogo_team04;

import interfaces_slogo_team04.State;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

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
	
	public void setPane(Pane display){
		this.pane = display;
	}
	
	public void add(Node node, double x, double y) {
		pane.getChildren().add(node);
		node.setTranslateX(x);
		node.setTranslateY(y);
	}

	public void updateColor(Color color){
		String hex = toRGBCode(color);
		getPane().setStyle("-fx-background-color: " + hex);
	}
	

}
