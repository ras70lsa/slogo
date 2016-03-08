package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdNotEqual extends CommandTreeNode {
    private INonLinearCommand expressionOne, expressionTwo;

    public CmdNotEqual (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double valOne, valTwo;
        valOne = expressionOne.executeCommand(myController, myInterpreter);
        valTwo = expressionTwo.executeCommand(myController, myInterpreter);
        return oneIfNotEqual(valOne, valTwo);
    }

    private double oneIfNotEqual(double valOne, double valTwo){
        int compareOutput = Double.compare(valOne, valTwo);
        if(compareOutput == 0){
            return CommandTreeNode.DOUBLE_ZERO;
        }
        return CommandTreeNode.DOUBLE_ONE;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        expressionOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        expressionTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
