package backend.slogo.team04;


import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdQuotient extends CommandTreeNode {
    protected final static String MY_KEY = "Quotient";


    private INonLinearCommand expOne, expTwo; // the two nodes that we need to grab



    public CmdQuotient(CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double valOne, valTwo;
        valOne = expOne.executeCommand(myController, myInterpreter, debugMe);
        valTwo = expTwo.executeCommand(myController, myInterpreter, debugMe);
        if(valTwo == CommandTreeNode.DOUBLE_ZERO){
            throw new LogicException("User code attempted to divide by zero");
        }
        return valOne / valTwo;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        expOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        expTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdQuotient.MY_KEY + CommandTreeNode.SPACE + expOne.parsableRepresentation() + CommandTreeNode.SPACE + expTwo.parsableRepresentation();
    }
    

}
