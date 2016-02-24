package backend_slogo_team04;

import java.util.Scanner;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

public class CmdNot extends CommandTreeNode {
    private INonLinearCommand testOne; 

    public CmdNot (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) {
        boolean testOneNonZero = isNonZero(testOne, myController, myInterpreter);
        if(testOneNonZero){
            return CommandTreeNode.DOUBLE_ZERO;
        }
        return CommandTreeNode.DOUBLE_ONE;
    }




    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        testOne = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        return this;
    }

}
