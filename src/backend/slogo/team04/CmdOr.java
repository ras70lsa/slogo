package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdOr extends CommandTreeNode {

    private INonLinearCommand testOne, testTwo; // the two nodes that we need to grab

    public CmdOr (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        boolean testOneNonZero, testTwoNonZero;
        testOneNonZero = CommandFactory.isNonZero(testOne, myController, myInterpreter);
        testTwoNonZero = CommandFactory.isNonZero(testTwo, myController, myInterpreter);
        if(testOneNonZero || testTwoNonZero){
            return CommandTreeNode.DOUBLE_ONE;
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        testOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        testTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);        
        return this;
    }

}
