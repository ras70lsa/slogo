package backend_structures;

/**
 * Used to store color generically on the backend (remove the dependency on Java FX)
 * @author RyanStPierre
 */
public class RGBColor {
	
	private double red;
	private double green;
	private double blue;
	
	public RGBColor(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public double getRed() {
		return red;
	}
	
	public double getBlue() {
		return blue;
	}
	
	public double getGreen() {
		return green;
	}
	
	public String toString() {
		return red + " " + green + " " + blue;
	}

}
