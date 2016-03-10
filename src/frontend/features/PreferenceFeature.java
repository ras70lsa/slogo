package frontend.features;

import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import interfaces.slogo.team04.IView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ModelLine;

public class PreferenceFeature extends TitledPane {

	public static final double SPACING = 10;
	public static final double HALF = .5;
	public static final int WIDTH_MIN = 2;
	public static final int WIDTH_MAX = 16;
	private VBox items;
	private IView view;
	private ColorIndexMenu backgroundPallete;
	private ShapeIndexMenu shapeIndex;
	ResourceBundle myBundle;
	
	public PreferenceFeature(IView view) {
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH +
    			ResourceConstants.ENGLISH);
		items = new VBox(SPACING);
		this.view = view;
		setUp();
		addListener();
	}
	
	private void addListener() {
		backgroundPallete.getSelected().addListener((a,b,c) -> view.getBackgroundColor().set(c));
		shapeIndex.getSelected().addListener((a,b,c) -> view.setShape((int) c+1));
	}
	
	private void populate() {
		VBox labelAndCombo = new VBox(SPACING);
		labelAndCombo.setPrefWidth(DisplayConstants.ACCORDION_WIDTH);
		//To Do: add resource bundle
		labelAndCombo.getChildren().add(new Label(myBundle.getString("BackgroundColor")));
		backgroundPallete = new ColorIndexMenu(view.getColorListProperty());
		labelAndCombo.getChildren().addAll(backgroundPallete,addColorIndexCombo());
		items.getChildren().add(labelAndCombo);
	}

	private void setUp() {
		
		this.setContent(items);
		//To Do add resource Bundle
		this.setText(myBundle.getString("Palette"));
		populate();
		items.getChildren().add(new HBox(addPenCombo(), andPenWidthCombo()));
	}


	private VBox addColorIndexCombo(){
		shapeIndex = new ShapeIndexMenu();
		VBox vbox = getVBoxWithSpacing();
		vbox.getChildren().add(new Label(myBundle.getString("ShapeIndex")));
		vbox.getChildren().add(shapeIndex);
		return vbox;
	}
	
	private VBox andPenWidthCombo() {
		ComboBox<String> penWidth = getComboBox();
		VBox vbox = getVBoxWithSpacing();
		vbox.getChildren().add(new Label(myBundle.getString("PenWidth")));
		vbox.getChildren().add(penWidth);
		for(int i= WIDTH_MIN; i<= WIDTH_MAX; i+=2) {
			penWidth.getItems().add(i + "");
		}
		penWidth.setOnAction(e -> view.getPenWidth().set(getDoubleSelction(penWidth.getSelectionModel().getSelectedItem())));
		return vbox;
	}

	private VBox getVBoxWithSpacing() {
		VBox vbox = new VBox(SPACING);
		vbox.setPrefWidth(DisplayConstants.ACCORDION_WIDTH);
		return vbox;
	}

	private double getDoubleSelction(String string) {
		
		return Double.parseDouble(string);
	}

	private VBox addPenCombo() {
		ComboBox<String> penStyle = getComboBox();
		VBox vbox = getVBoxWithSpacing();
		vbox.getChildren().add(new Label(myBundle.getString("PenStyle")));
		vbox.getChildren().add(penStyle);
		penStyle.getItems().add(ModelLine.SOLID);
		penStyle.getItems().add(ModelLine.DOTTED);
		penStyle.getItems().add(ModelLine.DASHED);
		penStyle.setOnAction(e -> penStyleSelected(penStyle.getSelectionModel().getSelectedItem()));
		return vbox;
	}

	private ComboBox<String> getComboBox() {
		ComboBox<String> combo = new ComboBox<>();
		combo.setPrefWidth(DisplayConstants.ACCORDION_WIDTH * HALF);
		return combo;
	}

	private void penStyleSelected(String selectedItem) {
		view.setPenStyle(selectedItem);
	}
}
