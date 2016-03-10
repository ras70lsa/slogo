package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdTo extends CommandTreeNode {
    protected final static String MY_KEY = "MakeUserInstruction";
    private String myCommandName;
    private double isInitializedCorrectly;




    public CmdTo (CommandTreeNode myParent) {
        super(myParent);
        isInitializedCorrectly = CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        //should never directly do anything, should only create command nodes which are stored in the interpreter and are grabbed and run themselves
        return isInitializedCorrectly;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        String myWord = myScanner.getNextWord();

        //check to see if this is a valid command word type
        if(SlogoRegexChecker.conformsToCmdNamingConventions(myWord) && !CommandFactory.isKeyWord(myWord)){
            this.myCommandName = myWord;
        }else{
            throw new UserInputException("Improperly named command detected");
        }


        CmdCommand myCommandToCreate = new CmdCommand(null, myWord); // command float on their own, do not have any parent as they are not really part of tree
        List<CmdVariable> listOfVariables = new ArrayList<CmdVariable>();
        List<INonLinearCommand> listOfCommands = new ArrayList<INonLinearCommand>(); 
        myWord = myScanner.getNextWord();
        if(myScanner.checkIfStartOfList(myWord, myInterpreter)){
            // grab variables
            myWord = myScanner.getNextWord();
            while(!myScanner.checkIfEndOfList(myWord, myInterpreter)){
                listOfVariables.add(CommandFactory.getVariableOrAssertError(myWord,myScanner, myCommandToCreate, myInterpreter));
                myWord = myScanner.getNextWord();
            }
            myInterpreter.putFunction(myCommandName, myCommandToCreate); // supporting recursion
            myCommandToCreate.setMyState(listOfVariables, listOfCommands);
            myWord = myScanner.getNextWord();
            if(myScanner.checkIfStartOfList(myWord, myInterpreter)){
                //grabbing and storing the commands
                myWord = myScanner.getNextWord();
                while(!myScanner.checkIfEndOfList(myWord, myInterpreter)){
                    listOfCommands.add(CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myWord, myScanner, myCommandToCreate, myInterpreter));
                    myWord = myScanner.getNextWord();
                }
                isInitializedCorrectly = CommandTreeNode.DOUBLE_ONE; // construction was properly done
            }else{
                throw new UserInputException("Command list not closed by bracket");
            }
        }
        else{
            throw new UserInputException("Variable list not closed");
        }
        return this;
    }

}
