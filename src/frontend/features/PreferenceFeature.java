package frontend.features;

import backend.structures.RGBColor;
import constants.DisplayConstants;
import interfaces.slogo.team04.IView;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PreferenceFeature extends TitledPane {

	public static final double SPACING = 10;
	private VBox items;
	private IView view;
	ColorIndexMenu backgroundPallete;
	
	public PreferenceFeature(IView view) {
		items = new VBox();
		this.view = view;
		setUp();
		populate();
		addListener();
	}
	
	private void addListener() {
		backgroundPallete.getSelected().addListener((a,b,c) -> view.getBackgroundColor().set(c));
		
	}
	
	private void populate() {
		VBox labelAndCombo = new VBox(SPACING);
		labelAndCombo.setPrefWidth(DisplayConstants.ACCORDION_WIDTH);
		//To Do: add resource bundle
		labelAndCombo.getChildren().add(new Label("Background Color"));
		backgroundPallete = new ColorIndexMenu(view.getColorListProperty());
		labelAndCombo.getChildren().add(backgroundPallete);
		items.getChildren().add(labelAndCombo);
	}

	private void setUp() {
		
		this.setContent(items);
		//To Do add resource Bundle
		this.setText("Pallet Options");
		
	}
}
