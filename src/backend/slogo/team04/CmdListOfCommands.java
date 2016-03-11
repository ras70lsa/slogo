package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


/**
 * Helper class, not real part of slogo language, thus is not included as part of the recursive construction function
 * in the CommandFactory. To use this, will need to instantiate an instance by hand where convenient.
 * 
 * Used to encapsulate the storage logic for an expected list of commands
 * with the opening and closing brackets []
 * 
 *
 * Will automatically return the last seen double value as a result of execution
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
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        
        double lastValue = CommandTreeNode.DOUBLE_ZERO;
        for(INonLinearCommand cmd : myCommands){
            lastValue = cmd.executeCommand(myController, myInterpreter);
        }
       
        return lastValue;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner,
                                          ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {

        String myNextWord = myScanner.getNextWord();
        if(myScanner.checkIfStartOfList(myNextWord, myInterpreter)){
            myNextWord = myScanner.getNextWord();
            while(!myScanner.checkIfEndOfList(myNextWord, myInterpreter)){
                myCommands.add(CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myNextWord, myScanner, this, myInterpreter));
                myNextWord = myScanner.getNextWord();
            }
        }else{
            throw new UserInputException("List of commands failed");
        }
        return this;
    }

    @Override
    public String parsableRepresentation () {
        String toReturn = CommandTreeNode.LEFT_BRACKET;
        for(INonLinearCommand cmd : myCommands){
           toReturn = toReturn + CommandTreeNode.SPACE + cmd.parsableRepresentation();
        }
        return toReturn + CommandTreeNode.RIGHT_BRACKET;
    }

}
