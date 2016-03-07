package model;

import backend.structures.RGBColor;

public class ModelLine {
	private double startX, startY, endX, endY;
	private RGBColor color;
	
	public ModelLine(double startX, double startY, double endX, double endY, RGBColor color){
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.color = color;
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
	
	
}
