package interfaces.slogo.team04;


/**
 * Adding support for multiple turtles
 * @author jonathanim
 *
 */

public interface ISlogoModelActionsExtended{

    double forward(double pixels, double turtleID);
    double back(double pixels, double turtleID);
    double left(double degrees, double turtleID);
    double right(double degrees, double turtleID);
    double setHeading(double degrees, double turtleID);
    double towards(double x, double y, double turtleID);
    double setxy(double x, double y, double turtleID);
    double penDown(double turtleID);
    double penUp(double turtleID);
    double showTurtle(double turtleID);
    double hideTurtle(double turtleID);
    double home(double turtleID);
    double isPenDown(double turtleID);
    double isShowing(double turtleID);
    double clearScreen();
    void update();
    double xCor(double turtleID);
    double yCor(double turtleID);
    double heading(double turtleID);
    
    /**
     * A boolean for each turtle representing whether or not it is active 
     * We will assume that negative ID numbers are not allowed
     * We will assume that the ID space is contiguous, meaning that no skipping of ID numbers is allowed.
     * Any 'missing' ID numbers will result in turtle creation
     * ID numbers will start at 1 and progress upwards (note the numbering difference from Java arrays)
     * @return
     */
    boolean[] activeTurtles();
    
    double setBackground(int index);
    double setPenColor(int index);
    double setPenSize(double pixels);
    double setShape(int index);
    
    
    double setPalette(int index, int r, int g, int b);
    double penColor();
    double shape();
    double stamp();
    double clearStamps();
    
    double ID();
    double turtles();
    
    /**
     * I think we should include the syntatic sugar that will fill in the array, ie, if you try to create tell [ 100 ] 
     * then our code will make sure that turtles 1 - 100 exist
     * 
     * Newly created turtles will be set to active, otherwise, after calling this command only the turtles in the list 
     * should be active, alll non-listed should be set to false
     * @return
     */
    double tell(int[] arrayOfActiveTurtleIDs);
    
    /**
     * This saves the current list of active turtles to be restored later by popCurrentActive
     */
    void pushCurrentActive();
    
    void popCurrentActive();
    
    //TODO we need to add the ability to have a condition here
    
    
    
    
    

}
