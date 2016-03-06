package backend.structures;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Used to store color generically on the backend (remove the dependency on Java FX)
 * @author RyanStPierre
 */
public class RGBColor {
	
	/**
	 * Need to reconsider changed. RGB Color should not be responsible for holding index. 
	 */
	private DoubleProperty red = new SimpleDoubleProperty();
	private DoubleProperty green = new SimpleDoubleProperty();
	private DoubleProperty blue = new SimpleDoubleProperty();
	private IntegerProperty index = new SimpleIntegerProperty();
	
	public RGBColor(double d, double e, double f) {
		getRedProperty().set(d);
		getGreenProperty().set(e);
		getBlueProperty().set(f);
	}
	
	public RGBColor(double red, double green, double blue, int index) {
		this(red,green,blue);
		this.index.set(index);
	}
	
	public double getRed() {
		return red.get();
	}
	
	public DoubleProperty getRedProperty(){
		return red;
	}
	
	public DoubleProperty getGreenProperty(){
		return green;
	}
	
	public DoubleProperty getBlueProperty(){
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
