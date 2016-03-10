package backend.slogo.team04;


import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdIf extends CommandTreeNode {
    protected final static String MY_KEY = "If";
    private INonLinearCommand myExpression;
    private INonLinearCommand myListOfCommands;


    public CmdIf (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        if(CommandFactory.isNonZero(myExpression, myController, myInterpreter)){
            myInterpreter.incept();
            double toReturn = myListOfCommands.executeCommand(myController, myInterpreter);
            myInterpreter.kick();
            return toReturn;
            
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myExpression = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myListOfCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        
        
        return this;
    }

}
