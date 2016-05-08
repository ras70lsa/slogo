package frontend.features;


import constants.DisplayConstants;
import model.ModelLine;

public class WindowDrawLine extends DrawLine implements IMyDrawer{

	public WindowDrawLine() {
		super();
	}

	public void draw(ModelLine line) {
		drawLine(makeXCorrection(line.getStartX()), 
				makeYCorrection(line.getStartY()), 
				makeXCorrection(line.getEndX()), 
				makeYCorrection(line.getEndY()), line);
	}

	@Override
	public double makeXCorrection(double x) {
		x = x + DisplayConstants.VIEW_WIDTH /2;
		return x;
	}

	@Override
	public double makeYCorrection(double y) {
		y = DisplayConstants.VIEW_HEIGHT /2 - y;
		return y; 	}
	

}

