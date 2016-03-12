package model;

import backend.structures.Pen;
import backend.structures.RGBColor;

/**
 * ModelLine holds an abstract representation of a line. This class is used to further segment the model from the view,
 * eliminating any JavaFx line dependency in the backend
 *
 */
public class ModelLine {
	private double startX, startY, endX, endY;
	private RGBColor color;
	private Style style;
	private double width;
	public static final double DASH_WIDTH = 5d;
	public static final double DOTTED_WIDTH = .1d;
	public static final double DOTTED_WIDTH_SPACE = 18d;
	public static final String DOTTED = "Dotted";
	public static final String SOLID = "Solid";
	public static final String DASHED = "Dashed";
	
	public ModelLine(double startX, double startY, double endX, double endY, Pen pen){
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.color = pen.getPenColor();
		this.style = pen.getStyle();
		this.width = pen.getPenWidth();
	}
	
	public enum Style {
		
		SOLID(new Double[]{}),
		DASHED(new Double[]{DASH_WIDTH, DASH_WIDTH}),
		DOTTED(new Double[]{DOTTED_WIDTH, DOTTED_WIDTH_SPACE});
		
		private Double[] style;
		Style(Double[] style) {
			this.style = style;
		}
		
		public Double[] getStyle() {return style;}
		
	}
	
	public double getEndX(){
		return endX;
	}
	
	public double getEndY(){
		return endY;
	}
	
	public double getStartX(){
		return startX;
	}
	
	public double getStartY(){
		return startY;
	}
	
	public RGBColor getColor() {
		return color;
	}

	public Double[] getStyle() {
		return style.getStyle();
	}
	
	public double getWidth() {
		return width;
	}
	
}
