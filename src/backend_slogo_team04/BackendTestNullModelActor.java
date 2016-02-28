package backend_slogo_team04;

import java.util.List;
import interfaces_slogo_team04.ISlogoModelActions;

public class BackendTestNullModelActor implements ISlogoModelActions{

    @Override
    public double forward (double pixels) {
        System.out.printf("forward: %f\n", pixels);
        return 0;
    }

    @Override
    public double back (double pixels) {
        System.out.printf("back: %f\n", pixels);
        return 0;
    }

    @Override
    public double left (double pixels) {
        System.out.printf("left: %f\n", pixels);
        return 0;
    }

    @Override
    public double right (double pixels) {
        System.out.printf("right: %f\n", pixels);
        return 0;
    }

    @Override
    public double setHeading (double degrees) {
        System.out.printf("setHeading: %f\n", degrees);
        return 0;
    }

    @Override
    public double towards (double x, double y) {
        System.out.printf("towards x: %f\ny:%f\n", x,y);
        return 0;
    }

    @Override
    public double setxy (double x, double y) {
        System.out.printf("setxy x: %f\ny:%f\n", x,y);
        return 0d;
    }

    @Override
    public double penDown () {
        System.out.printf("PenDown\n");
        return 0;
    }

    @Override
    public double penUp () {
        System.out.printf("PenUp\n");
        return 0;
    }

    @Override
    public double showTurtle () {
        System.out.printf("ShowTurtle\n");
        return 0;
    }

    @Override
    public double hideTurtle () {
        System.out.printf("HideTurtle\n");
        return 0;
    }

    @Override
    public double home () {
        System.out.printf("home\n");
        return 0;
    }

    @Override
    public double xCor () {
        System.out.printf("xCor\n");
        return 0;
    }

    @Override
    public double yCor () {
        System.out.printf("yCor\n");
        return 0;
    }

    @Override
    public double heading () {
        System.out.printf("heading\n");
        return 0;
    }

    @Override
    public double isPenDown () {
        System.out.printf("isPenDown\n");
        return 0;
    }

    @Override
    public double isShowing () {
        System.out.printf("isShowing\n");
        return 0;
    }

    @Override
    public double clearScreen () {
        System.out.printf("clearScreen\n");
        return 0;
    }

    @Override
    public List<Action> getHistory () {
        System.out.printf("Getting history\n");
        return null;
    }
    
  
    


}
