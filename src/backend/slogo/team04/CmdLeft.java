package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdLeft extends CommandTreeNode {
    protected final static String MY_KEY = "Left";
    private INonLinearCommand myValue;


    public CmdLeft (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        return myController.left(myValue.executeCommand(myController, myInterpreter, debugMe) , new CmdID(this).executeCommand(myController, myInterpreter, debugMe));
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myValue = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdLeft.MY_KEY + CommandTreeNode.SPACE + myValue.parsableRepresentation();
    }

}
