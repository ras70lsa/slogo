package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdAskWith extends CommandTreeNode {
    protected final static String MY_KEY = "AskWith";
    private CommandTreeNode myIDIterator , myCondition, myCmdListOfCommands;



    public CmdAskWith (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        myInterpreter.incept();
        double toReturn = myIDIterator.executeCommand(myController, myInterpreter);
        myInterpreter.kick();
        return toReturn;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        String myWord;
        myWord = myScanner.getNextWord();
        if(myScanner.checkIfStartOfList(myWord, myInterpreter)){
            // grab variables
            myWord = myScanner.getNextWord();
            myCondition = (CommandTreeNode) CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myWord, myScanner, this, myInterpreter);
            myWord = myScanner.getNextWord();
            if(myScanner.checkIfEndOfList(myWord, myInterpreter)){
                myCmdListOfCommands = (CommandTreeNode) new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
            }else{
                throw new UserInputException("Closing bracket not found");
            }
        }
        else{
            throw new UserInputException("Opening bracket not found for CmdTell");
        }        
        //fixing tree parent references to pass the proper current id value
        myIDIterator = new CmdIDIterator(this, myCondition, myCmdListOfCommands);
        myCondition.setMyParent(myIDIterator);
        myCmdListOfCommands.setMyParent(myIDIterator);
        return this;
    }

}
