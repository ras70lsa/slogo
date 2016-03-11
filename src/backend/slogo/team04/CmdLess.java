package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdLess extends CommandTreeNode {
    protected final static String MY_KEY = "LessThan";
    private INonLinearCommand expressionOne, expressionTwo;
    


    public CmdLess (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double valOne, valTwo;
        valOne = expressionOne.executeCommand(myController, myInterpreter);
        valTwo = expressionTwo.executeCommand(myController, myInterpreter);
        return oneIfStrictlyLessThan(valOne, valTwo);
    }
    
    private double oneIfStrictlyLessThan(double valOne, double valTwo){
        int compareOutput = Double.compare(valOne, valTwo);
        if(compareOutput < 0){
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
        return CmdLess.MY_KEY + CommandTreeNode.SPACE + expressionOne.parsableRepresentation() + CommandTreeNode.SPACE + expressionTwo.parsableRepresentation();
    }

}
