package backend.slogo.team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdAtan extends CommandTreeNode {
    private INonLinearCommand expOne; 

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
        expOne = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}