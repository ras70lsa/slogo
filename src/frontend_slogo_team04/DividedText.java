package frontend_slogo_team04;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DividedText extends HBox {

	public DividedText() {
		populate();
	}
	
	private void populate() {
		Text t1 = new Text("Ryan\nTayla");
		this.getChildren().add(t1);
		Text t2 = new Text("Ryan2");
		this.getChildren().add(t2);
		this.setOnMouseClicked(e->System.out.println("yay"));
	}

}
