package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdLog extends CommandTreeNode {
    protected final static String MY_KEY = "NaturalLog";
    private INonLinearCommand inputValue; // the two nodes that we need to grab

    public CmdLog(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double valOne;
        valOne = inputValue.executeCommand(myController, myInterpreter, debugMe);
        return Math.log(valOne);
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        inputValue = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdLog.MY_KEY + CommandTreeNode.SPACE + inputValue.parsableRepresentation();
    }

}
