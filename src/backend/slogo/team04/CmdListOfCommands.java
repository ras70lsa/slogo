package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
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
    public INonLinearCommand parseString (SlogoScanner myScanner,
                                          ISlogoInterpreter myInterpreter) throws UserInputException {

        String myNextWord = SlogoScanner.getNextWord();
        if(SlogoScanner.checkIfStartOfList(myNextWord, myInterpreter)){
            myNextWord = SlogoScanner.getNextWord();
            while(!SlogoScanner.checkIfEndOfList(myNextWord, myInterpreter)){
                myCommands.add(CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myNextWord, myScanner, this, myInterpreter));
                myNextWord = SlogoScanner.getNextWord();
            }
        }else{
            throw new UserInputException("List of commands failed");
        }
        return this;
    }

}
