package backend_slogo_team04;


/**
 * This class represents all actionable things that are present in the Slogo language, things that do not control flow or change internal
 * state like variables but instead should result in changes to the drawn screen.
 * @author jonathanim
 *
 */
public interface SlogoModelActions {
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
    public double isPenDown();
    public double isShowing();

}
