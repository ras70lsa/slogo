package frontend.features;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ModelLine;

public class DrawLine {
	
	private static final double ALPHA = 1;
	private ArrayList<Line> linemanager;
	public DrawLine() {
		linemanager = new ArrayList<Line>();
	}
	
	public Line drawLine(double startX, double startY, double endX, double endY, ModelLine line) {
		
		Line n = new Line();
		addLine(n, startX, startY, endX, endY);
		n.getStrokeDashArray().addAll(line.getStyle());
		Color color = new Color(line.getColor().getRed(), line.getColor().getGreen(), line.getColor().getBlue(), ALPHA);
		n.setStroke(color);
		n.setStrokeWidth(line.getWidth());
		return n;
	}
	
	protected void addLine(Line node, double startX, double startY, double endX, double endY){
		node.setStartX(startX);
		node.setStartY(startY);
		node.setEndX(endX);
		node.setEndY(endY);
		linemanager.add(node);
	}
	
		
	public ArrayList<Line> getLines(){
		return linemanager;
	}
	
	public void clearLines(){
		linemanager.clear();
	}
	
	public ArrayList<Line> getLineManager(){
		return linemanager;
	}

}
