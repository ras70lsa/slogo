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
public class CmdIsCurrentTurtleActive extends CommandTreeNode {
    public CmdIsCurrentTurtleActive (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myController.isShowing(); asdf asdf 
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }

}
