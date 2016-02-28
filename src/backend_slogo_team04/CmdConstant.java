package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;


public class CmdConstant extends CommandTreeNode {

    private double myValue;

    public CmdConstant (CommandTreeNode myParent, double myValue) {
        super(myParent);
        this.myValue = myValue;
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myValue;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }

}
