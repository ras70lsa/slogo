package frontend_slogo_team04;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import java.io.File;
import java.util.Collection;



import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GuiUserOption {

	public void get(File file, String str) {
		
	}
	
	public Node get(BooleanProperty value, String str) {
		// TODO Auto-generated constructor stub
		Button b = createToggle(value);
		b.setTranslateX(200);
		b.setTranslateY(200);
		return b;
		
	}
	
	public Node get(ObjectProperty<Color> color, String str) {
		ColorPicker picker = new ColorPicker();
		picker.setOnAction(e -> color.set(picker.getValue()));
		return picker;
	}
	
	

	private Button createToggle(BooleanProperty value) {
		return new ToggleButton(value);
	}
	

	public Node get(Enum enumerable, Collection<String> str) {
		return null;
	}

}
