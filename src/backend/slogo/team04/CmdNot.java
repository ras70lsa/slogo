package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;


public class CmdNot extends CommandTreeNode {
    private INonLinearCommand testOne; 

    public CmdNot (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        boolean testOneNonZero = CommandFactory.isNonZero(testOne, myController, myInterpreter);
        if(testOneNonZero){
            return CommandTreeNode.DOUBLE_ZERO;
        }
        return CommandTreeNode.DOUBLE_ONE;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        testOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
