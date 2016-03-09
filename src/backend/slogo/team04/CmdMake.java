package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdMake extends CommandTreeNode {
    protected final static String MY_KEY = "MakeVariable";
    private CmdVariable myVariable;
    private INonLinearCommand myExpression;

    public CmdMake (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        myVariable.setVariableValue(myExpression.executeCommand(myController, myInterpreter), myInterpreter);
        return myVariable.getVariableValue(myInterpreter);
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        // grab a variable command
        myVariable = CommandFactory.getVariableOrAssertError(myScanner.getNextWord(), myScanner, this, myInterpreter);
        
        // grab an expression and then assign the value to the variable
        
        myExpression = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
