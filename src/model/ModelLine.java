package model;


public class ModelLine {
	private double startX, startY, endX, endY;
	
	public ModelLine(double startX, double startY, double endX, double endY){
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
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
	
	
}
