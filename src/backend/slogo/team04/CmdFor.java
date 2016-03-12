package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class CmdFor extends CommandTreeNode {
    protected final static String MY_KEY = "For";
    private CmdVariable myVariable;
    private INonLinearCommand myStart, myEnd, myIncrement, cmdList;

    public CmdFor (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double increment = myIncrement.executeCommand(myController, myInterpreter, debugMe);
        double limit = myEnd.executeCommand(myController, myInterpreter, debugMe);
        double lastValueSeen = CommandTreeNode.DOUBLE_ZERO;
        for(double d = myStart.executeCommand(myController, myInterpreter, debugMe); d <= limit; d+=increment){
            myInterpreter.incept();
            myVariable.setVariableValue(d, myInterpreter);
            lastValueSeen = cmdList.executeCommand(myController, myInterpreter, debugMe);
            myInterpreter.kick();
        }
        return lastValueSeen;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        String nextString = myScanner.getNextWord();
        if(myScanner.checkIfStartOfList(nextString, myInterpreter)){
            myVariable = CommandFactory.getVariableOrAssertError(myScanner.getNextWord(), myScanner, this, myInterpreter);
            myStart = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
            myEnd = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
            myIncrement = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
            if(myScanner.checkIfEndOfList(myScanner.getNextWord(), myInterpreter)){
                cmdList = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
            }else{
                throw new UserInputException("Expected closing list bracket");
            }
        }else{
            throw new UserInputException("Expected opening list bracket");
        }
        
        return this;
    }

    @Override
    public String parsableRepresentation () {
        // TODO Auto-generated method stub
        return CmdFor.MY_KEY + CommandTreeNode.LEFT_BRACKET + myVariable.parsableRepresentation() + CommandTreeNode.SPACE +
                myStart.parsableRepresentation() + CommandTreeNode.SPACE +
                myEnd.parsableRepresentation() + CommandTreeNode.SPACE +
                myIncrement.parsableRepresentation() + CommandTreeNode.RIGHT_BRACKET + cmdList.parsableRepresentation();
    }

}
