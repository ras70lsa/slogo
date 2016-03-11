package interfaces.slogo.team04;

import backend.structures.RGBColor;
import properties.ColorProperty;

/**
 * This class represents all actionable things that are present in the Slogo language, things that do not control flow or change internal
 * state like variables but instead should result in changes to the drawn screen.
 * @author jonathanim
 */
public interface ISlogoModelActions {
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
    double isPenDown();
    double isShowing();
    double clearScreen();
    void update();
    double xCor();
    double yCor();
    double heading();

}
