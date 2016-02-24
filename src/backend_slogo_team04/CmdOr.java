package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

public class CmdOr extends CommandTreeNode {

    private INonLinearCommand testOne, testTwo; // the two nodes that we need to grab

    public CmdOr (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        boolean testOneNonZero, testTwoNonZero;
        testOneNonZero = isNonZero(testOne, myController, myInterpreter);
        testTwoNonZero = isNonZero(testTwo, myController, myInterpreter);
        if(testOneNonZero || testTwoNonZero){
            return CommandTreeNode.DOUBLE_ONE;
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }




    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        testOne = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        testTwo = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);        
        return this;
    }

}
