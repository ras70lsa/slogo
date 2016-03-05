package backend.structures;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Used to store color generically on the backend (remove the dependency on Java FX)
 * @author RyanStPierre
 */
public class RGBColor {
	
	private SimpleIntegerProperty red = new SimpleIntegerProperty();
	private SimpleIntegerProperty green = new SimpleIntegerProperty();
	private SimpleIntegerProperty blue = new SimpleIntegerProperty();
	private SimpleIntegerProperty index = new SimpleIntegerProperty();
	
	public RGBColor(int red, int green, int blue) {
		getRedProperty().set(red);
		getGreenProperty().set(green);
		getBlueProperty().set(blue);
	}
	
	public RGBColor(int red, int green, int blue, int index) {
		this(red,green,blue);
		this.index.set(index);
	}
	
	public double getRed() {
		return red.get();
	}
	
	public IntegerProperty getRedProperty(){
		return red;
	}
	
	public IntegerProperty getGreenProperty(){
		return green;
	}
	
	public IntegerProperty getBlueProperty(){
		return blue;
	}
	
	public double getBlue() {
		return blue.get();
	}
	
	public double getGreen() {
		return green.get();
	}
	
	public String toString() {
		return red + " " + green + " " + blue;
	}
	
	public int getIndex(){
		return index.get();
	}
}
