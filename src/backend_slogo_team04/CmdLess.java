package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

public class CmdLess extends CommandTreeNode {
    private INonLinearCommand expressionOne, expressionTwo;
    


    public CmdLess (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
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
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        expressionOne = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        expressionTwo = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        
        //expressionOne = new CmdANullOne(null);
        //expressionTwo = new CmdANullTwo(null);
        return this;
    }

}
