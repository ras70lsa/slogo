package frontend_slogo_team04;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Collection;



import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import properties.ColorProperty;
import properties.ImageProperty;

/**
 * Automates the creation of nodes for the user selection stage based purely on a State's Property
 * @author Ryan St Pierre
 */
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
	
	public Node get(ColorProperty color, String str) {
		ColorPicker picker = new ColorPicker();
		picker.setOnAction(e -> color.set(picker.getValue()));
		return picker;
	}
	
	public Node get(ImageProperty image, String str) {
		Button button = new Button();
		button.setText(str);
		button.setOnAction(e-> getImage(image));
		return button;
		
	}
	
	public Node get(StringProperty property, String[] possible) {
		ComboBox<String> box = new ComboBox<String>();
		box.setPromptText("Choose Language");
		box.getItems().addAll(possible);
		box.setOnAction(e -> property.set(box.getValue()));
		return box;
	}
	
	private void getImage(ImageProperty image) {
		FileChooser f = new FileChooser();
		File file = f.showOpenDialog(new Stage());
		try {
			Image input = new Image(file.toURI().toURL().toString());
			image.set(input);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
	}

	private Button createToggle(BooleanProperty value) {
		return new ToggleButton(value);
	}
	

	public Node get(Enum enumerable, Collection<String> str) {
		return null;
	}

}
