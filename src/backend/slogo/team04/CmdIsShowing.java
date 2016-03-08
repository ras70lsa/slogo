package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdIsShowing extends CommandTreeNode {
    public CmdIsShowing (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myController.isShowing();
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }

}
