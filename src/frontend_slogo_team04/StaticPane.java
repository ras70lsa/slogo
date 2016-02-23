package frontend_slogo_team04;

import interfaces_slogo_team04.IState;
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
	
	public void add(Node node, double x, double y) {
		pane.getChildren().add(node);
		node.setTranslateX(x);
		node.setTranslateY(y);
	}
	
	public void updateColor(Color color){
		String hex = toRGBCode(color);
		pane.setStyle("-fx-background-color: " + hex);
	}
	
	public void addCSS(String str) {
		pane.getStylesheets().add(str);
	}


	

}
