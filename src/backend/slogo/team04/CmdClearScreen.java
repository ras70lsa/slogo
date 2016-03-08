package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdClearScreen extends CommandTreeNode {



    public CmdClearScreen (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myController.clearScreen();
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }

}
