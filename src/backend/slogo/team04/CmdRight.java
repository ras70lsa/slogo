package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdRight extends CommandTreeNode {
    protected final static String MY_KEY = "Right";
    private INonLinearCommand myValue;
    public CmdRight (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        return myController.right(myValue.executeCommand(myController, myInterpreter, debugMe) , new CmdID(this).executeCommand(myController, myInterpreter, debugMe));
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myValue = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdRight.MY_KEY + CommandTreeNode.SPACE + myValue.parsableRepresentation();
    }

}
