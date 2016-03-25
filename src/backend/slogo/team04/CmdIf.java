//This entire file is part of my masterpiece.
//Jonathan Im
/**
 * This class shows how scope works through manually calling incept and kick on our state storage interface where it makes sense to have changes 
 * in variable scope
 * Debugging was implemented simply by calling a standard method on the ISlogoDebugObject interface in subclasses that directly correspond to 
 * Slogo language commands (there are some helper classes that don't really exists and should thus seamlessly operate without pausing for example
 * the CmdIDIterator node)
 */
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
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        if(CommandFactory.isNonZero(myExpression, myController, myInterpreter, debugMe)){
            myInterpreter.incept();
            double toReturn = myListOfCommands.executeCommand(myController, myInterpreter, debugMe);
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

    @Override
    public String parsableRepresentation () {
        return CmdIf.MY_KEY + CommandTreeNode.SPACE + myExpression.parsableRepresentation() + CommandTreeNode.SPACE + myListOfCommands.parsableRepresentation();
    }

}
