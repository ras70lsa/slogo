package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

import model.Controller;

public class CmdAtan extends CommandTreeNode {
    private INonLinearCommand expOne; // the two nodes that we need to grab

    public CmdAtan(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double valOne;
        valOne = expOne.executeCommand(myController, myInterpreter);
        return Math.atan(valOne); //even though language specifies degrees, i think this is the proper way to use arctan, forgot all trig
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        expOne = CommandTreeNode.recursiveSlogoFactory(myScanner, this, myInterpreter);
        return this;
    }

}
