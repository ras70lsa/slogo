package frontend.slogo.team04;

import java.util.List;

import backend.slogo.team04.Action;

public interface VisualizationAction {
		public double forward(double pixels);
	    public double back(double pixels);
	    public double left(double pixels);
	    public double right(double pixels);
	    public double setHeading(double degrees);
	    public double towards(double x, double y);
	    public double setxy(double x, double y);
	    public double penDown();
	    public double penUp();
	    public double showTurtle();
	    public double hideTurtle();
	    public double home();
	    public double clearScreen();
	    public double xCor();
	    public double yCor();
	    public double heading();
	    public boolean isPenDown();
	    public boolean isShowing();
	    public void updateHistory(List<Action> history);

}
