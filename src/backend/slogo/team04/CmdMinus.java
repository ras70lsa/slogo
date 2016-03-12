package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdMinus extends CommandTreeNode {
    protected final static String MY_KEY = "Minus";
    private INonLinearCommand expOne; 

    public CmdMinus(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double valOne;
        valOne = expOne.executeCommand(myController, myInterpreter, debugMe);
        return -valOne;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        expOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdMinus.MY_KEY + CommandTreeNode.SPACE + expOne.parsableRepresentation();
    }
    

}
