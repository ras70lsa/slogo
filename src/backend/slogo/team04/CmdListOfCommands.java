package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


/**
 * Helper class, not real part of slogo language but used to encapsulate the storage logic for an expected list of commands
 * with the opening and closing brackets []
 * will automatically return the last seen double value as a result of execution
 * @author jonathanim
 *
 */
public class CmdListOfCommands extends CommandTreeNode {
    List<INonLinearCommand> myCommands;

    public CmdListOfCommands (CommandTreeNode myParent) {
        super(myParent);
        myCommands = new ArrayList<INonLinearCommand>();

    }

    @Override
    public double executeCommand (ISlogoModelActions myController,
                                  ISlogoInterpreter myInterpreter) throws LogicException {
        double lastValue = CommandTreeNode.DOUBLE_ZERO;
        for(INonLinearCommand cmd : myCommands){
            lastValue = cmd.executeCommand(myController, myInterpreter);
        }
        return lastValue;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner,
                                          ISlogoInterpreter myInterpreter) throws UserInputException {

        String myNextWord = CommandTreeNode.getNextWord(myScanner);
        if(SlogoRegexChecker.isStartOfList(myNextWord)){
            myNextWord = CommandTreeNode.getNextWord(myScanner);
            while(!SlogoRegexChecker.isEndOfList(myNextWord)){
                myCommands.add(CommandTreeNode.recursiveSlogoFactoryNoListsControlledAdvance(myNextWord, myScanner, this, myInterpreter));
                myNextWord = CommandTreeNode.getNextWord(myScanner);
            }
        }else{
            throw new UserInputException("List of commands failed");
        }
        return this;
    }

}
