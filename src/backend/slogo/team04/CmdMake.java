package backend.slogo.team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdMake extends CommandTreeNode {
    private CmdVariable myVariable;
    private INonLinearCommand myExpression;

    public CmdMake (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        myVariable.setVariableValue(myExpression.executeCommand(myController, myInterpreter), myInterpreter);
        return myVariable.getVariableValue(myInterpreter);
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        // grab a variable command
        myVariable = CommandTreeNode.getVariableOrAssertError(CommandTreeNode.getNextWord(myScanner), myScanner, this, myInterpreter);
        
        // grab an expression and then assign the value to the variable
        
        myExpression = CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
