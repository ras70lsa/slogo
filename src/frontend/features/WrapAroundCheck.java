package frontend.features;

import constants.DisplayConstants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ModelLine;

public class WrapAroundCheck {

	public WrapAroundCheck() {
		// TODO Auto-generated constructor stub
	}
	
	public Line drawLine(double startX, double startY, double endX, double endY, ModelLine line) {
		double sX, sY, eX, eY;
		
		if(isInBounds(startX, startY)){
			sX = startX;
			sY = startY;
			eX = endX;
			eY = endY;
		}else{
			double numTimesXWidthRemoved = Math.floor((startX/DisplayConstants.VIEW_WIDTH));
			double numTimesYWidthRemoved = Math.floor((startY/DisplayConstants.VIEW_HEIGHT));
			sX = Math.abs(startX) % DisplayConstants.VIEW_WIDTH;
			sY = Math.abs(startY) % DisplayConstants.VIEW_HEIGHT;
			if(startX<0){
				sX = DisplayConstants.VIEW_WIDTH-sX;
				eX = endX+(numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
			}else{
				eX = endX-(numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
			}
			if(startY<0){
				sY = DisplayConstants.VIEW_HEIGHT-sY;
				eY = endY+(numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
			}else{
				eY = endY-(numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
			}
			eX = endX - (numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
			eY = endY - (numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
		}
		Line n = new Line();
		n.getStrokeDashArray().addAll(line.getStyle());
		Color color = new Color(line.getColor().getRed(), line.getColor().getGreen(), line.getColor().getBlue(), ALPHA);
		n.setStroke(color);
		n.setStrokeWidth(line.getWidth());
		
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
		return n;
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
	
	

}
