package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdAnd extends CommandTreeNode {
    protected final static String MY_KEY = "And";
    private INonLinearCommand testOne, testTwo; // the two nodes that we need to grab



    public CmdAnd (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        boolean testOneNonZero, testTwoNonZero;
        testOneNonZero = CommandFactory.isNonZero(testOne, myController, myInterpreter);
        testTwoNonZero = CommandFactory.isNonZero(testTwo, myController, myInterpreter);
        if(testOneNonZero && testTwoNonZero){
            return CommandTreeNode.DOUBLE_ONE;
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        testOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        testTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdAnd.MY_KEY + CommandTreeNode.SPACE + testOne.parsableRepresentation() + CommandTreeNode.SPACE + testTwo.parsableRepresentation();
    }
    

}
