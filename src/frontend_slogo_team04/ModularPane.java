package frontend_slogo_team04;

import javafx.scene.paint.Color;
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
	
	public void update() {
		getState().getUserOptions().show();
	}
	
	public String toRGBCode( Color color ){
	    return String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
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

}
