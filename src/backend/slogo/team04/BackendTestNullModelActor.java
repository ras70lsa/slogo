package backend.slogo.team04;



import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class BackendTestNullModelActor implements ISlogoModelActionsExtended{
    @Override
    public boolean[] activeTurtles () {
        // TODO Auto-generated method stub
        boolean[] turtActive = new boolean[]{true, false , false, true, false};
        return  turtActive;
    }



    @Override
    public double clearScreen () {
        System.out.printf("clearScreen\n");
        return 0;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public double forward (double pixels, double turtleID) {
        System.out.printf("Moving turtle %d forward %f\n", (int) turtleID, pixels);
        return pixels;
    }

    @Override
    public double back (double pixels, double turtleID) {
        System.out.printf("Moving turtle %d back %f\n", (int) turtleID, pixels);
        return 0;
    }

    @Override
    public double left (double pixels, double turtleID) {
        System.out.printf("Moving turtle %d left %f\n", (int) turtleID, pixels);
        return 0;
    }

    @Override
    public double right (double pixels, double turtleID) {
        System.out.printf("Moving turtle %d right %f\n", (int) turtleID, pixels);
        return 0;
    }

    @Override
    public double setHeading (double degrees, double turtleID) {
        System.out.printf("Setting turtle %d to %f degrees\n", (int) turtleID, degrees);
        return 0;
    }

    @Override
    public double towards (double x, double y, double turtleID) {
       System.out.printf("Towards: %f, %f for turtle %d\n", x,y,(int) turtleID);
        return 0;
    }

    @Override
    public double setxy (double x, double y, double turtleID) {
        System.out.printf("SetXY: %f, %f for turtle %d\n", x,y,(int) turtleID);
        return 0;
    }

    @Override
    public double penDown (double turtleID) {
        System.out.printf("PenDown for Turtle %d\n", (int) turtleID);
        return 0;
    }

    @Override
    public double penUp (double turtleID) {
        System.out.printf("PenUp for Turtle %d\n",(int)  turtleID);
        return 0;
    }

    @Override
    public double showTurtle (double turtleID) {
        System.out.printf("ShowTurtle for Turtle %d\n",(int)  turtleID);
        return 0;
    }

    @Override
    public double hideTurtle (double turtleID) {
        System.out.printf("HideTurtle for Turtle %d\n",(int)  turtleID);
        return 0;
    }

    @Override
    public double home (double turtleID) {
        System.out.printf("Home for Turtle %d\n", (int) turtleID);
        return 0;
    }

    @Override
    public double isPenDown (double turtleID) {
        System.out.printf("isPenDown for Turtle %d\n",(int)  turtleID);
        return 0;
    }

    @Override
    public double isShowing (double turtleID) {
        System.out.printf("PenDown for Turtle %d\n", (int) turtleID);
        return 0;
    }

    @Override
    public double xCor (double turtleID) {
        System.out.printf("xCor for Turtle %d\n", (int) turtleID);
        return 0;
    }

    @Override
    public double yCor (double turtleID) {
        System.out.printf("yCor for Turtle %d\n",(int)  turtleID);
        return 0;
    }

    @Override
    public double heading (double turtleID) {
        System.out.printf("heading for Turtle %d\n", (int) turtleID);
        return 0;
    }

  

    @Override
    public double setBackground (int index) {
        System.out.printf("setting background index %d\n", (int) index);
        return 0;
    }

    @Override
    public double setPenColor (int index) {
        System.out.printf("setting pencolor index %d\n", (int) index);
        return 0;
    }

    @Override
    public double setPenSize (double pixels) {
        System.out.printf("setting pensize %f\n", pixels);
        return 0;
    }

    @Override
    public double setShape (int index) {
        System.out.printf("setting shape index %d\n", (int) index);
        return 0;
    }

    @Override
    public double setPalette (int index, int r, int g, int b) {
        System.out.printf("setting palette index %d , r: %d, g: %d, b:%d\n", index, r,g,b);
        return 0;
    }

    @Override
    public double penColor () {
        System.out.printf("getting pencolor\n");
        return 0;
    }

    @Override
    public double shape () {
        System.out.printf("getting shape\n");
        return 0;
    }

    @Override
    public double stamp () {
        System.out.printf("stamping\n");
        return 0;
    }

    @Override
    public double clearStamps () {
        System.out.printf("clearing stamps\n");
        return 0;
    }

    @Override
    public double ID () {
        System.out.printf("Getting current active ID\n");
        return 0;
    }

    @Override
    public double turtles () {
        System.out.printf("How many turtles\n");
        return 0;
    }

    @Override
    public double tell (int[] arrayOfActiveTurtleIDs) {
        System.out.print("Telling the following turtles: ");
        for(int i = 0; i < arrayOfActiveTurtleIDs.length; i++){
            System.out.print(arrayOfActiveTurtleIDs[i] + " ");    
        }
        
        System.out.println("");
        return 0;
    }

    @Override
    public void pushCurrentActive () {
        System.out.printf("Pushing actor stack\n");
        
    }

    @Override
    public void popCurrentActive () {
        System.out.printf("Popping actor stack\n");
        
    }

    
    


}
