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
    public CmdExecuteIfCurrentTurtleActive (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        boolean[] currentActiveTurtles = myController.activeTurtles();
        double currentIDValue = new CmdID(this).executeCommand(myController, myInterpreter);
        int curID = (int) currentIDValue;
        if(currentActiveTurtles[curID]){
            return CommandTreeNode.DOUBLE_ONE;
        }else{
            return CommandTreeNode.DOUBLE_ZERO;
        }
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        //myChild = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CommandTreeNode.EMPTY_STRING;
    }

}
