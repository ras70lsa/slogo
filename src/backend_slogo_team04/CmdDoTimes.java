package backend_slogo_team04;


import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

import model.Controller;

public class CmdDoTimes extends CommandTreeNode {

    private CmdVariable myVariable;
    private INonLinearCommand myLimit, myCommands;
    public CmdDoTimes (Controller myController, CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double lastSeenCommandValue = 0;
        for(int i = 1; i <= myLimit.executeCommand(myController, myInterpreter); i++){
            myVariable.setVariableValue((double) i, myInterpreter);

            lastSeenCommandValue = myCommands.executeCommand(myController, myInterpreter);
        }
        return lastSeenCommandValue;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        String myWord = CommandTreeNode.getNextWord(myScanner);
        if(SlogoRegexChecker.isStartOfList(myWord)){
            // grab variable and then the limit factor
            myWord = CommandTreeNode.getNextWord(myScanner);
            myVariable = CommandTreeNode.getVariableOrAssertError(myWord,myScanner, this, myInterpreter);
            myLimit = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);

            myCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);


        }else{
            throw new UserInputException("Variable and limit brackets not closed");
        }
        return this;
    }

}
