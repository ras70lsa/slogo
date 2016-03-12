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
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        INonLinearCommand cmdToExecute;
        if(CommandFactory.isNonZero(myExpression, myController, myInterpreter, debugMe)){
            cmdToExecute = trueCommands;
        }else{
            cmdToExecute = falseCommands;
        }
        myInterpreter.incept();
        double toReturn = cmdToExecute.executeCommand(myController, myInterpreter, debugMe);
        myInterpreter.kick();
        return toReturn;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        // TODO Auto-generated method stub
        myExpression = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        trueCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        falseCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdIfElse.MY_KEY + CommandTreeNode.SPACE + myExpression.parsableRepresentation() + CommandTreeNode.SPACE +
                trueCommands.parsableRepresentation() + CommandTreeNode.SPACE + falseCommands.parsableRepresentation();
    }

}
