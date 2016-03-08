package backend.structures;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ModelLine;
import model.ModelLine.Style;

public class Pen {
	private RGBColor penColor;
	private double strokeWidth;
	private static final int DEFAULT_LINE_WIDTH = 2;
	private Style style;
	private Map<String, Style> styles;
	
	public Pen(RGBColor penColor) {
		setPenColor(penColor);
		makeMap();
		strokeWidth = DEFAULT_LINE_WIDTH;
		style = Style.SOLID;
	}

	private void makeMap() {
		styles = new HashMap<>();
		styles.put(ModelLine.DASHED, Style.DASHED);
		styles.put(ModelLine.DOTTED, Style.DOTTED);
		styles.put(ModelLine.SOLID, Style.SOLID);
	}

	public RGBColor getPenColor() {
		return penColor;
	}

	public void setPenColor(RGBColor color) {
		this.penColor = color;
	}

	public void setPenWidth(double width) {
		strokeWidth = width;
	}

	public double getPenWidth() {
		return strokeWidth;
	}

	public ModelLine createLine(double startX, double startY, double endX, double endY) {
		Line line = new Line(startX, startY, endX, endY);
		line.setStrokeWidth(strokeWidth);
		//line.setStroke(penColor);
		return null;
	}
	
	public void makeDotted() {
		style = Style.DOTTED;
	}
	
	public void makeDashed() {
		style = Style.DASHED;
	}
	
	public void makeSolid() {
		style = Style.SOLID;
	}
	
	public Style getStyle() {
		return style;
	}

	public void setStyle(String selectedItem) {
		style = styles.get(selectedItem);
	}
	
	public void setLineWidth(double width) {
		strokeWidth = width;
	}
}
