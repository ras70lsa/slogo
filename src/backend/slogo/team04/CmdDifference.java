package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdDifference extends CommandTreeNode {
    protected final static String MY_KEY = "Difference";

    private INonLinearCommand inputOne, inputTwo; // the two nodes that we need to grab



    public CmdDifference(CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double valOne, valTwo;
        valOne = inputOne.executeCommand(myController, myInterpreter);
        valTwo = inputTwo.executeCommand(myController, myInterpreter);
        return valOne - valTwo;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        inputOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        inputTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
