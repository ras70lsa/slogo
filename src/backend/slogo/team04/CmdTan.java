package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdTan extends CommandTreeNode {
    protected final static String MY_KEY = "Tangent";
    private INonLinearCommand inputValue; // the two nodes that we need to grab

    public CmdTan(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double valOne;
        valOne = inputValue.executeCommand(myController, myInterpreter);
        return Math.tan(Math.toRadians(valOne));
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        inputValue = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
