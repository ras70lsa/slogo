package backend.slogo.team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdPow extends CommandTreeNode {
    private INonLinearCommand expOne, expTwo; // the two nodes that we need to grab

    public CmdPow(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double valOne, valTwo;
        valOne = expOne.executeCommand(myController, myInterpreter);
        valTwo = expTwo.executeCommand(myController, myInterpreter);
        return Math.pow(valOne, valTwo);
    }




    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        expOne = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        expTwo = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }
}
