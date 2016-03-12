package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdTell extends CommandTreeNode {
    protected final static String MY_KEY = "Tell";
    private List<INonLinearCommand> myArguments;



    public CmdTell (CommandTreeNode myParent) {
        super(myParent);
        myArguments = new ArrayList<INonLinearCommand>();

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        List<Integer> listOfTurtlesToMakeActive = new ArrayList<Integer>();
        for(INonLinearCommand next : myArguments){
            double myProposedValue = next.executeCommand(myController, myInterpreter, debugMe);
            if(SlogoRegexChecker.isPostiveIndex(myProposedValue)){
                listOfTurtlesToMakeActive.add((int) myProposedValue);
            }else{
                throw new LogicException("Attempted to access index with non positive integer");
            }
        }
        return myController.tell(this.convertIntegerArrayToIntArray(listOfTurtlesToMakeActive.toArray()));
        
        
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        String myWord;
        myWord = myScanner.getNextWord();
        if(myScanner.checkIfStartOfList(myWord, myInterpreter)){
            // grab variables
            myWord = myScanner.getNextWord();
            while(!myScanner.checkIfEndOfList(myWord, myInterpreter)){
                myArguments.add(CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myWord, myScanner, this, myInterpreter));
                myWord = myScanner.getNextWord();
            }
        }
        else{
            throw new UserInputException("Closing bracket not found for CmdTell");
        }
        return this;
    }

    @Override
    public String parsableRepresentation () {
        String toReturn = CmdTell.MY_KEY + CommandTreeNode.LEFT_BRACKET;
        return appendParsableRepresentationWithSpaces(toReturn, myArguments) + CommandTreeNode.RIGHT_BRACKET;
    }

}
