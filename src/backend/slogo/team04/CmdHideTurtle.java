package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdHideTurtle extends CommandTreeNode {
    protected final static String MY_KEY = "HideTurtle";



    public CmdHideTurtle (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        myController.hideTurtle(new CmdID(this).executeCommand(myController, myInterpreter));
        return CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdHideTurtle.MY_KEY;
    }

}
