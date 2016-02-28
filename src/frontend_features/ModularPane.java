package frontend_features;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import visual_states.GuiUserOption;
import javafx.scene.layout.VBox;
import java.util.List;

import backend_structures.RGBColor;
import constants.DisplayConstants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Parent class for all features with Panes on the screen
 * @author Ryan St Pierre
 */
public abstract class ModularPane implements Module {
	
	private DoubleProperty width;
	private DoubleProperty height;
	
	public ModularPane() {
		width = new SimpleDoubleProperty();
		height = new SimpleDoubleProperty();
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
	            (int)( c.getRed() * 255 ),
	            (int)( c.getGreen() * 255 ),
	            (int)( c.getBlue() * 255 ) );
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
		getPane().setStyle("-fx-background-color: " + hex);
	}
	

}
