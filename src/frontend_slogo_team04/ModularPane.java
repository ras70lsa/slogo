package frontend_slogo_team04;

import interfaces_slogo_team04.IState;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class ModularPane implements Module {

	private Pane pane; 
	
	public ModularPane() {
		pane = new Pane();
	}
	
	public Pane getPane() {
		return pane;
	}
	
	public void setPane(Pane newPane){
		this.pane = newPane;
	}
	
	public void add(Node node, double x, double y) {
		pane.getChildren().add(node);
		node.setTranslateX(x);
		node.setTranslateY(y);
		System.out.println("Adding to pane");
	}

	

}
