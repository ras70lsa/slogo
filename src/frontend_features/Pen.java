package frontend_features;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Pen {
	private Color penColor;
	private double strokeWidth;
	private static final int DEFAULT_LINE_WIDTH = 2;

	public Pen(Color penColor) {
		setPenColor(penColor);
		strokeWidth = DEFAULT_LINE_WIDTH;
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color color) {
		this.penColor = color;
	}

	public void setPenWidth(double width) {
		strokeWidth = width;
	}

	public double getPenWidth() {
		return strokeWidth;
	}

	public Line createLine(double startX, double startY, double endX, double endY) {
		Line line = new Line(startX, startY, endX, endY);
		line.setStrokeWidth(strokeWidth);
		line.setStroke(penColor);
		return line;
	}
}
