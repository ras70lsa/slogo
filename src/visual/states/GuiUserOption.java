package visual.states;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import java.io.File;
import java.net.MalformedURLException;
import backend.structures.RGBColor;
import frontend.slogo.team04.ToggleButton;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
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

	public static final Color DEFUALT_LABEL_COLOR = Color.BLACK;
	public static final String OFFSET = "  ";
	
	
	public Node get(BooleanProperty value, String label) {
		HBox hbox = new HBox();
		hbox.getChildren().add(new Label(label));
		hbox.getChildren().add(new ToggleButton(value));
		return hbox;
		
	}
	
	public Node get(ColorProperty color, ListProperty<RGBColor> colorPalette, String title) {
		HBox hbox = new HBox();
		ColorPicker picker = new ColorPicker();
		picker.setOnAction(e -> setColor(picker.getValue(), colorPalette, color));
		hbox.getChildren().add(picker);
		Label label = new Label(OFFSET + title);
		label.setTextFill(DEFUALT_LABEL_COLOR);
		hbox.getChildren().add(label);
		return hbox;
	}

	private void setColor(Color frontEnd, ListProperty<RGBColor> colorPalette, ColorProperty color) {
		//Needs to be fixed. Need to talk about chances to RGB color
		RGBColor next = new RGBColor(frontEnd.getRed(), frontEnd.getGreen(), frontEnd.getBlue(), colorPalette.size());
		colorPalette.add(next);
		color.set(next);
	}

	public Node get(ImageProperty image, String title) {
		Button button = new Button();
		button.setText(title);
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
		if(file!=null) {
			try {
				Image input = new Image(file.toURI().toURL().toString());
				image.set(input);
			} catch (MalformedURLException e) {
				//do nothing
			}
		} 
	}

}
