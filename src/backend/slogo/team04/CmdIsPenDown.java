package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdIsPenDown extends CommandTreeNode {
    protected final static String MY_KEY = "IsPenDown";
    public CmdIsPenDown (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myController.isPenDown(new CmdID(this).executeCommand(myController, myInterpreter));
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }

}
