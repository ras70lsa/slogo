package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdOr extends CommandTreeNode {
    protected final static String MY_KEY = "Or";

    private INonLinearCommand testOne, testTwo; // the two nodes that we need to grab

    public CmdOr (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        boolean testOneNonZero, testTwoNonZero;
        testOneNonZero = CommandFactory.isNonZero(testOne, myController, myInterpreter, debugMe);
        testTwoNonZero = CommandFactory.isNonZero(testTwo, myController, myInterpreter, debugMe);
        if(testOneNonZero || testTwoNonZero){
            return CommandTreeNode.DOUBLE_ONE;
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        testOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        testTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);        
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdOr.MY_KEY + CommandTreeNode.SPACE + testOne.parsableRepresentation() + CommandTreeNode.SPACE + testTwo.parsableRepresentation();
    }

}
