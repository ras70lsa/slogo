package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdIfElse extends CommandTreeNode {
    protected final static String MY_KEY = "IfElse";
    private INonLinearCommand myExpression, trueCommands, falseCommands;



    public CmdIfElse (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        // TODO Auto-generated method stub
        INonLinearCommand cmdToExecute;
        if(CommandFactory.isNonZero(myExpression, myController, myInterpreter)){
            cmdToExecute = trueCommands;
        }else{
            cmdToExecute = falseCommands;
        }
        return cmdToExecute.executeCommand(myController, myInterpreter);
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        // TODO Auto-generated method stub
        myExpression = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        trueCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        falseCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        return this;
    }

}
