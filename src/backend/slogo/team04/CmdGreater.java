package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdGreater extends CommandTreeNode {
    protected final static String MY_KEY = "GreaterThan";
    private INonLinearCommand expressionOne, expressionTwo;
    
    public CmdGreater (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double valOne, valTwo;
        valOne = expressionOne.executeCommand(myController, myInterpreter, debugMe);
        valTwo = expressionTwo.executeCommand(myController, myInterpreter, debugMe);
        return oneIfStrictlyGreaterThan(valOne, valTwo);
    }
    
    private double oneIfStrictlyGreaterThan(double valOne, double valTwo){
        int compareOutput = Double.compare(valOne, valTwo);
        if(compareOutput > 0){
            return CommandTreeNode.DOUBLE_ONE;
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        expressionOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        expressionTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdGreater.MY_KEY + CommandTreeNode.SPACE + expressionOne.parsableRepresentation() +
                CommandTreeNode.SPACE + expressionTwo.parsableRepresentation();
    }

}
