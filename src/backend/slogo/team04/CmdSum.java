package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdSum extends CommandTreeNode {
    protected final static String MY_KEY = "Sum";
    private INonLinearCommand expOne, expTwo; // the two nodes that we need to grab



    public CmdSum (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double valOne, valTwo;
        valOne = expOne.executeCommand(myController, myInterpreter, debugMe);
        valTwo = expTwo.executeCommand(myController, myInterpreter, debugMe);
        return valOne + valTwo;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        expOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        expTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdSum.MY_KEY + CommandTreeNode.SPACE + expOne.parsableRepresentation() + CommandTreeNode.SPACE + expTwo.parsableRepresentation();
    }


}
