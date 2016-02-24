package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

public class CmdLog extends CommandTreeNode {
    private INonLinearCommand expOne; // the two nodes that we need to grab

    public CmdLog(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double valOne;
        valOne = expOne.executeCommand(myController, myInterpreter);
        return Math.log(valOne);
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        expOne = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        return this;
    }

}
