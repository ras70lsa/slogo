package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

import model.Controller;

public class CmdFor extends CommandTreeNode {
    private CmdVariable myVariable;
    private INonLinearCommand myStart, myEnd, myIncrement, cmdList;

    public CmdFor (Controller myController, CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double increment = myIncrement.executeCommand(myController, myInterpreter);
        double limit = myEnd.executeCommand(myController, myInterpreter);
        double lastValueSeen = CommandTreeNode.DOUBLE_ZERO;
        for(double d = myStart.executeCommand(myController, myInterpreter); d <= limit; d+=increment){
            myVariable.setVariableValue(d, myInterpreter);
            lastValueSeen = cmdList.executeCommand(myController, myInterpreter);
        }
        return lastValueSeen;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        String nextString = CommandTreeNode.getNextWord(myScanner);
        if(SlogoRegexChecker.isStartOfList(nextString)){
            myVariable = CommandTreeNode.getVariableOrAssertError(CommandTreeNode.getNextWord(myScanner), myScanner, this, myInterpreter);
            myStart = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
            myEnd = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
            myIncrement = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
            nextString = CommandTreeNode.getNextWord(myScanner);
            if(SlogoRegexChecker.isEndOfList(nextString)){
                cmdList = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
            }else{
                throw new UserInputException("Expected closing list bracket");
            }
        }else{
            throw new UserInputException("Expected opening list bracket");
        }
        
        return this;
    }

}
