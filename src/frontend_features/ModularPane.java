package frontend_features;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import visual_states.GuiUserOption;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ResourceBundle;

import backend_structures.RGBColor;
import constants.CSSPathConstants;
import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Parent class for all features with Panes on the screen
 * @author Ryan St Pierre
 */
public abstract class ModularPane implements Module {
	
	public static final int RGB_MULTIPLIER = 255;
	private DoubleProperty width;
	private DoubleProperty height;
	ResourceBundle myBundle;
	
	public ModularPane() {
		width = new SimpleDoubleProperty();
		height = new SimpleDoubleProperty();
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH +
					ResourceConstants.ENGLISH);
	}
	
	protected Stage getEmptyStage() {
		Scene myScene = new Scene(new Group(), DisplayConstants.USER_OPTION_SIZE, DisplayConstants.USER_OPTION_SIZE, 
				DisplayConstants.USER_OPTION_COLOR);
		Stage stage = new Stage();
		stage.setScene(myScene);
		return stage;
	}

	public String toRGBCode( RGBColor c ){
	    return String.format( "#%02X%02X%02X",
	            (int)( c.getRed() * RGB_MULTIPLIER ),
	            (int)( c.getGreen() * RGB_MULTIPLIER ),
	            (int)( c.getBlue() * RGB_MULTIPLIER ) );
	}
	
	public void position(double x, double y, double prefWidth, double prefHeight) {
		getPane().setTranslateX(x);
		getPane().setTranslateY(y);
		getPane().setPrefWidth(prefWidth);
		getPane().setPrefHeight(prefHeight);
		width.set(prefWidth);
		height.set(prefHeight);
	}

	public void addCSS(String str) {
		getPane().getStylesheets().add(str);
	}
	
	protected DoubleProperty getWidth() {
		return width;
	}
	
	protected DoubleProperty getHeight() {
		return height;
	}
	
	public void updateColor(RGBColor c){
		String hex = toRGBCode(c);
		getPane().setStyle(CSSPathConstants.BACKGROUND_COLOR + hex);
	}
	
	protected String getString(String input) {
		return myBundle.getString(input);
	}
	

}
