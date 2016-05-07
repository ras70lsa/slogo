package frontend.features;

import java.util.ArrayList;
import java.util.List;
import constants.DisplayConstants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ModelLine;

import utilities.WrapAroundCheck;


public class WrapAroundDrawLine {

	private static final double ALPHA = 1;

	private Line TopLine;
	private Line RightLine;
	private Line LeftLine;
	private Line BottomLine;
	private ArrayList<Line> linemanager;
	public WrapAroundDrawLine() {
		TopLine = new Line(0, 0, DisplayConstants.VIEW_WIDTH, 0);
		RightLine = new Line(DisplayConstants.VIEW_WIDTH, 0, DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		LeftLine = new Line(0, 0, 0, DisplayConstants.VIEW_HEIGHT);
		BottomLine = new Line(0, DisplayConstants.VIEW_HEIGHT, DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		linemanager = new ArrayList<Line>();
	}
	
	public Line drawLine(double startX, double startY, double endX, double endY, ModelLine line) {
		double[] newLinePoints = new double[4];
		
		newLinePoints = checkStartPoints(startX, startY, endX, endY);
		
		Line n = new Line();

		checkEndPoints(newLinePoints, n, line);
		
		return n;
	}

	public void draw(ModelLine line) {
		drawLine(makeXLineCorrection(line.getStartX()), 
				makeYLineCorrection(line.getStartY()), 
				makeXLineCorrection(line.getEndX()), 
				makeYLineCorrection(line.getEndY()), line);
	}
	
		/**
		 * 
		 * @param startX
		 * @param startY
		 * @param endX
		 * @param endY
		 * @return Array of doubles, index 0 is the x location, 1 = y of the intersection point 
		 */
	private double[] checkStartPoints(double startX, double startY, double endX, double endY){
		double[] newLine = new double[4];
		newLine[0] = startX;
		newLine[1] = startY;
		newLine[2] = endX;
		newLine[3] = endY;
		
		if(isInBounds(startX, startY)){
			return newLine;
		}
			return modifyStartPoints(newLine);
	}
	
	private double[] modifyStartPoints(double[] input){
		double[] newLine = new double[4];
		double startX = input[0];
		double startY = input[1];
		double endX = input[2];
		double endY = input[3];
		
		double numTimesXWidthRemoved = Math.floor((startX/DisplayConstants.VIEW_WIDTH));
		double numTimesYWidthRemoved = Math.floor((startY/DisplayConstants.VIEW_HEIGHT));
		newLine[0] = Math.abs(startX) % DisplayConstants.VIEW_WIDTH;
		newLine[1] = Math.abs(startY) % DisplayConstants.VIEW_HEIGHT;
		if(startX<0){
			newLine[0] = DisplayConstants.VIEW_WIDTH-newLine[0];
		}
		if(startY<0){
			newLine[1] = DisplayConstants.VIEW_HEIGHT-newLine[1];
		}
		newLine[2] = endX - (numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
		newLine[3] = endY - (numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
		return newLine;
	}
	
	private void checkEndPoints(double[] input, Line n, ModelLine line){
		double sX = input[0];
		double sY = input[1];
		double eX = input[2];
		double eY = input[3];
		
		if(isInBounds(eX, eY)){
			addLine(n, sX, sY, eX, eY);
		}else{
			Line n2 = new Line();
			n2.setStartX(sX);
			n2.setStartY(sY);
			n2.setEndX(eX);
			n2.setEndY(eY);
			double[] originalNewEndPoints = adjustEnd(n2);
			addLine(n, sX, sY, originalNewEndPoints[0], originalNewEndPoints[1]);
			double[] newLine = adjustLine(originalNewEndPoints[0], originalNewEndPoints[1], eX, eY);
			drawLine(newLine[0], newLine[1], newLine[2], newLine[3], line);
		}
	}
	
	private boolean checkXBounds(double xLoc){
		return !(xLoc<0 || xLoc> DisplayConstants.VIEW_WIDTH);
	}
	
	private boolean checkYBounds(double yLoc){
		return !(yLoc<0 || yLoc> DisplayConstants.VIEW_HEIGHT);
	}
	
	private boolean isInBounds(double xLoc, double yLoc){
		return (checkXBounds(xLoc)&&checkYBounds(yLoc));
	}
	
	private double makeXLineCorrection(double x){
		return x + DisplayConstants.VIEW_WIDTH /2;
	}
	
	private double makeYLineCorrection(double y){
		return DisplayConstants.VIEW_HEIGHT /2 - y; 
	}
	
	private double[] adjustLine(double sx, double sy, double ex, double ey){
		double original[] = new double[4];
		original[0] = sx;
		original[1] = sy;
		original[2] = ex;
		original[3] = ey;
		
		if((int)Math.round(sy) == 0){
			original[1] = DisplayConstants.VIEW_HEIGHT-1;
			original[3] = ey + DisplayConstants.VIEW_HEIGHT;
		}
		if((int)Math.round(sy) == DisplayConstants.VIEW_HEIGHT){
			original[1] = 1;
			original[3] = ey - DisplayConstants.VIEW_HEIGHT;
		}
		if((int)Math.round(sx) == 0){
			original[0] = DisplayConstants.VIEW_WIDTH-1;
			original[2] = ex + DisplayConstants.VIEW_WIDTH;
		}
		if((int)Math.round(sx) == DisplayConstants.VIEW_WIDTH){
			original[0] = 1;
			original[2] = ex - DisplayConstants.VIEW_WIDTH;
		}
		return original;
	}
	
	private double[] adjustEnd(Line n){
		double original[] = new double[2];
		original[0] = n.getEndX();
		original[1] = n.getEndY();
		
		if(WrapAroundCheck.linesIntersect(n, TopLine)==true){
			return WrapAroundCheck.getLineIntersection(n, TopLine);	
		}
		else if(WrapAroundCheck.linesIntersect(n, BottomLine)==true){
			
			return WrapAroundCheck.getLineIntersection(n, BottomLine);
		}else if(WrapAroundCheck.linesIntersect(n, RightLine)==true){
			
			return WrapAroundCheck.getLineIntersection(n, RightLine);
		}else if(WrapAroundCheck.linesIntersect(n, LeftLine)==true){
			
			return WrapAroundCheck.getLineIntersection(n, LeftLine);
		}else{
			return original;
		}
	}
	
	private void addLine(Line node, double startX, double startY, double endX, double endY){
		node.setStartX(startX);
		node.setStartY(startY);
		node.setEndX(endX);
		node.setEndY(endY);
		linemanager.add(node);
	}
	
	public List<Line> getLines(){
		return linemanager;
	}
	
	public void clearLines(){
		linemanager.clear();
	}

}

