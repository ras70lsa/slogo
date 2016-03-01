package frontend.slogo.team04;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Class for creating the data structure that display the histroy in an organized fashion
 * @author Ryan St Pierre
 *
 */
public class DividedText extends HBox {

	private Text t1;
	private Text t2;
	
	public DividedText(String str1, String str2) {
		populate(str1, str2);
		t1.getStyleClass().add("dividedText_text1");
		t2.getStyleClass().add("dividedText_text2");
	}
	
	private void populate(String string1, String string2) {
		t1 = new Text(string1);
		this.getChildren().add(t1);
		t2 = new Text(string2);
		this.getChildren().add(t2);
		this.setOnMouseClicked(e->System.out.println("yay"));
	}
	
	public void setW(double width) {
		
		double spacing = width - t1.getLayoutBounds().getWidth();
		if(width > t1.getLayoutBounds().getWidth()) {
			this.setSpacing(spacing);
		}
	}
	
	public Text getRight() {
		return t2;
	}

}
