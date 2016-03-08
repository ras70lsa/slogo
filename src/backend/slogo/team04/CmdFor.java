package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class CmdFor extends CommandTreeNode {
    private CmdVariable myVariable;
    private INonLinearCommand myStart, myEnd, myIncrement, cmdList;

    public CmdFor (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double increment = myIncrement.executeCommand(myController, myInterpreter);
        double limit = myEnd.executeCommand(myController, myInterpreter);
        double lastValueSeen = CommandTreeNode.DOUBLE_ZERO;
        for(double d = myStart.executeCommand(myController, myInterpreter); d <= limit; d+=increment){
            myVariable.setVariableValue(d, myInterpreter);
            lastValueSeen = cmdList.executeCommand(myController, myInterpreter);
        }
        return lastValueSeen;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
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

}
