package backend_slogo_team04;

import java.util.Scanner;
import exceptions.UserInputException;

public class CmdOr extends CommandTreeNode {



    public CmdOr (Controller myController, CommandTreeNode myParent) {
        super(myController, myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (Controller myController, Interpreter myInterpreter) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner) throws UserInputException {
        // TODO Auto-generated method stub
        return null;
    }

}
