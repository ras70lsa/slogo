package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;


public class CmdIfElse extends CommandTreeNode {
    private INonLinearCommand myExpression, trueCommands, falseCommands;



    public CmdIfElse (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        // TODO Auto-generated method stub
        INonLinearCommand cmdToExecute;
        if(CommandTreeNode.isNonZero(myExpression, myController, myInterpreter)){
            cmdToExecute = trueCommands;
        }else{
            cmdToExecute = falseCommands;
        }
        return cmdToExecute.executeCommand(myController, myInterpreter);
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        // TODO Auto-generated method stub
        myExpression = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        trueCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        falseCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        return this;
    }

}
