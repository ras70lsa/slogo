package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

import model.Controller;

public class CmdNot extends CommandTreeNode {
    private INonLinearCommand testOne; 

    public CmdNot (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
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
