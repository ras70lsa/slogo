package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

/**
 * We are going to collapse all of the different conditionals into commands. This will be a conditional that returns 1 if
 * the current turtle being considered is active
 * 
 * This will allow us to reduce repetition and use the same iterate over all turtles method to implement all of our conditional turtle
 * action functionality 
 * 
 * @author jonathanim
 *
 */
public class CmdExecuteIfCurrentTurtleActive extends CommandTreeNode {
    private INonLinearCommand myChild;
    public CmdExecuteIfCurrentTurtleActive (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        boolean[] currentActiveTurtles = myController.activeTurtles();
        double currentIDValue = new CmdID(this).executeCommand(myController, myInterpreter);
        int curID = (int) currentIDValue;
        if(currentActiveTurtles[curID]){
            return myChild.executeCommand(myController, myInterpreter);
        }else{
            return Double.NaN;
        }
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        myChild = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
