package frontend_slogo_team04;

import interfaces_slogo_team04.State;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public abstract class StaticPane extends ModularPane {

	private Pane pane; 
	
	public StaticPane() {
		pane = new Pane();
	}
	
	public Region getPane() {
		return pane;
	}
	
	public void add(Node node, int x, int y) {
		pane.getChildren().add(node);
		node.setTranslateX(x);
		node.setTranslateY(y);
	}
	
	public void updateColor(Color color){
		String hex = toRGBCode(color);
		pane.setStyle("-fx-background-color: " + hex);
	}


	

}
