package backend_slogo_team04;

import java.util.Scanner;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

public class CmdANullTwo extends CommandTreeNode {
    private INonLinearCommand testOne, testTwo; // the two nodes that we need to grab



    public CmdANullTwo (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) {
        return 1d;
    }
    


    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }

}
