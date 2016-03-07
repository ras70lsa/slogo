package backend.structures;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ModelLine;

public class Pen {
	private RGBColor penColor;
	private double strokeWidth;
	private static final int DEFAULT_LINE_WIDTH = 2;

	public Pen(RGBColor penColor) {
		setPenColor(penColor);
		strokeWidth = DEFAULT_LINE_WIDTH;
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
}
