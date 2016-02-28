package backend_slogo_team04;


import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;


public class CmdIf extends CommandTreeNode {
    private INonLinearCommand myExpression;
    private INonLinearCommand myListOfCommands;


    public CmdIf (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        if(CommandTreeNode.isNonZero(myExpression, myController, myInterpreter)){
            return myListOfCommands.executeCommand(myController, myInterpreter);
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        myExpression = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myListOfCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        
        
        return this;
    }

}
