package backend_slogo_team04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;


public class CmdTo extends CommandTreeNode {
    private String myCommandName;
    private double isInitializedCorrectly;




    public CmdTo (CommandTreeNode myParent, String myCommandName) {
        super(myParent);
        this.myCommandName = myCommandName;
        isInitializedCorrectly = CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        //should never directly do anything, should only create command nodes which are stored in the interpreter and are grabbed and run themselves
        //TODO make this refer to a resource bundle
        return isInitializedCorrectly;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        // TODO Auto-generated method stub

        CmdCommand myCommandToCreate = new CmdCommand(null, myCommandName); // command float on their own, do not have any parent as they are not really part of tree



        String myWord = CommandTreeNode.getNextWord(myScanner);

        List<CmdVariable> listOfVariables = new ArrayList<CmdVariable>();
        List<INonLinearCommand> listOfCommands = new ArrayList<INonLinearCommand>(); 
        if(SlogoRegexChecker.isStartOfList(myWord)){
            // grab variables
            myWord = CommandTreeNode.getNextWord(myScanner);
            while(!SlogoRegexChecker.isEndOfList(myWord)){
                listOfVariables.add(CommandTreeNode.getVariableOrAssertError(myWord,myScanner, myCommandToCreate, myInterpreter));
                myWord = CommandTreeNode.getNextWord(myScanner);
            }
            myWord = CommandTreeNode.getNextWord(myScanner);
            if(SlogoRegexChecker.isStartOfList(myWord)){
                //grabbing and storing the commands
                myWord = CommandTreeNode.getNextWord(myScanner);
                while(!SlogoRegexChecker.isEndOfList(myWord)){
                    listOfCommands.add(CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, myCommandToCreate, myInterpreter));
                    myWord = CommandTreeNode.getNextWord(myScanner);
                }
                isInitializedCorrectly = CommandTreeNode.DOUBLE_ONE; // construction was properly done
                // adding adding the calculated state to the command and then adding it to the stored list in the interpreter
                myCommandToCreate.setMyState(listOfVariables, listOfCommands);
                myInterpreter.putFunction(myCommandName, myCommandToCreate);
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
