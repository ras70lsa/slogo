package frontend.slogo.team04;

import java.util.List;

import backend.slogo.team04.Action;

public interface VisualizationAction {
	double forward(double pixels);
	double back(double pixels);
	double left(double pixels);
	double right(double pixels);
	double setHeading(double degrees);
	double towards(double x, double y);
	double setxy(double x, double y);
	double penDown();
	double penUp();
	double showTurtle();
	double hideTurtle();
	double home();
	double clearScreen();
	double xCor();
	double yCor();
	double heading();
	boolean isPenDown();
	boolean isShowing();
	void updateHistory(List<Action> history);

}
