package frontend.features;


import constants.DisplayConstants;
import model.ModelLine;

public class FenceDrawLine extends DrawLine implements IMyDrawer{

	public FenceDrawLine() {
		super();
	}

	public void draw(ModelLine line) {
		drawLine(makeXCorrection(line.getStartX()), 
				makeYCorrection(line.getStartY()), 
				makeXCorrection(line.getEndX()), 
				makeYCorrection(line.getEndY()), line);
	}
	
	@Override
	public double makeXCorrection(double x){
		x = x + DisplayConstants.VIEW_WIDTH /2;
		if(x<0){
			return 0;
		}else if(x>DisplayConstants.VIEW_WIDTH){
			return DisplayConstants.VIEW_WIDTH;
		}
		return x;
	}
	
	@Override
	public double makeYCorrection(double y){
		y = DisplayConstants.VIEW_HEIGHT /2 - y;
		if(y<0){
			return 0;
		}else if(y> DisplayConstants.VIEW_HEIGHT){
			return DisplayConstants.VIEW_HEIGHT;
		}
		return y; 
	}
	
	
}
