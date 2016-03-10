package backend.slogo.team04;


import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdDoTimes extends CommandTreeNode {
    protected final static String MY_KEY = "DoTimes";

    private CmdVariable myVariable;
    private INonLinearCommand myLimit, myCommands;
    public CmdDoTimes (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double lastSeenCommandValue = 0;
        for(int i = 1; i <= myLimit.executeCommand(myController, myInterpreter); i++){
            myInterpreter.incept();
            myVariable.setVariableValue((double) i, myInterpreter);
            lastSeenCommandValue = myCommands.executeCommand(myController, myInterpreter);
            myInterpreter.kick();
        }
        return lastSeenCommandValue;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        String myWord = myScanner.getNextWord();
        if(myScanner.checkIfStartOfList(myWord, myInterpreter)){
            // grab variable and then the limit factor
            myWord = myScanner.getNextWord();
            myVariable = CommandFactory.getVariableOrAssertError(myWord,myScanner, this, myInterpreter);
            myLimit = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
            myWord = myScanner.getNextWord();
            if(myScanner.checkIfEndOfList(myWord, myInterpreter)){
                myCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
            } else{
                throw new UserInputException("Variable and limits declaration not closed with bracket");
            }

        }else{
            throw new UserInputException("Expected variable and limits declaration");
        }
        return this;
    }

}
