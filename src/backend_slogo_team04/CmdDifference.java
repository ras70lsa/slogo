package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

public class CmdDifference extends CommandTreeNode {

    private INonLinearCommand expOne, expTwo; // the two nodes that we need to grab



    public CmdDifference(CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double valOne, valTwo;
        valOne = expOne.executeCommand(myController, myInterpreter);
        valTwo = expTwo.executeCommand(myController, myInterpreter);
        return valOne - valTwo;
    }




    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        expOne = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        expTwo = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        return this;
    }

}
