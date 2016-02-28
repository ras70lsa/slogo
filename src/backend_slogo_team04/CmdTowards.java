package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;


public class CmdTowards extends CommandTreeNode {
    INonLinearCommand myX, myY;



    public CmdTowards (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myController.towards(myX.executeCommand(myController, myInterpreter)
                                    , myY.executeCommand(myController, myInterpreter));
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        // TODO Auto-generated method stub
        myX = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myY = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
