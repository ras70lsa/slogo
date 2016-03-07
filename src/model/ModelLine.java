package model;

import backend.structures.RGBColor;

public class ModelLine {
	private double startX, startY, endX, endY;
	private RGBColor color;
	private Style style;
	public static final double DASH_WIDTH = 5d;
	public static final double DOTTED_WIDTH = 2d;
	public static final String DOTTED = "Dotted";
	public static final String SOLID = "Solid";
	public static final String DASHED = "Dashed";
	
	public ModelLine(double startX, double startY, double endX, double endY, RGBColor color,
			Style style){
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.color = color;
		this.style = style;
	}
	
	public enum Style {
		
		SOLID(new Double[]{}),
		DASHED(new Double[]{DASH_WIDTH, DASH_WIDTH}),
		DOTTED(new Double[]{DOTTED_WIDTH, DOTTED_WIDTH});
		
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
	
}
