package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdTo extends CommandTreeNode {
    private String myCommandName;
    private double isInitializedCorrectly;




    public CmdTo (CommandTreeNode myParent) {
        super(myParent);
        isInitializedCorrectly = CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        //should never directly do anything, should only create command nodes which are stored in the interpreter and are grabbed and run themselves
        //TODO make this refer to a resource bundle
        return isInitializedCorrectly;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        // TODO Auto-generated method stub
        String myWord = SlogoScanner.getNextWord();
        
        //check to see if this is a valid command word type
        if(SlogoRegexChecker.couldBeCommand(myWord) && !CommandFactory.isKeyWord(myWord)){
            this.myCommandName = myWord;
        }else{
            throw new UserInputException("Improperly named command detected");
        }
        
        
        CmdCommand myCommandToCreate = new CmdCommand(null, myWord); // command float on their own, do not have any parent as they are not really part of tree
        List<CmdVariable> listOfVariables = new ArrayList<CmdVariable>();
        List<INonLinearCommand> listOfCommands = new ArrayList<INonLinearCommand>(); 
        myWord = SlogoScanner.getNextWord();
        if(SlogoScanner.checkIfStartOfList(myWord, myInterpreter)){
            // grab variables
            myWord = SlogoScanner.getNextWord();
            while(!SlogoScanner.checkIfEndOfList(myWord, myInterpreter)){
                listOfVariables.add(CommandFactory.getVariableOrAssertError(myWord,myScanner, myCommandToCreate, myInterpreter));
                myWord = SlogoScanner.getNextWord();
            }
            myWord = SlogoScanner.getNextWord();
            if(SlogoScanner.checkIfStartOfList(myWord, myInterpreter)){
                //grabbing and storing the commands
                myWord = SlogoScanner.getNextWord();
                while(!SlogoScanner.checkIfEndOfList(myWord, myInterpreter)){
                    listOfCommands.add(CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myWord, myScanner, myCommandToCreate, myInterpreter));
                    myWord = SlogoScanner.getNextWord();
                }
                isInitializedCorrectly = CommandTreeNode.DOUBLE_ONE; // construction was properly done
                // adding adding the calculated state to the command and then adding it to the stored list in the interpreter
                myCommandToCreate.setMyState(listOfVariables, listOfCommands);
                myInterpreter.putFunction(myCommandName, myCommandToCreate); //TODO to support recursion i think all i need is to move this line
                //before the //grabbing and storing the commands section
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
