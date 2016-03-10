package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class CmdCos extends CommandTreeNode {
    protected final static String MY_KEY = "Cosine";
    private INonLinearCommand inputValue; // the two nodes that we need to grab

    public CmdCos(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double valOne;
        valOne = inputValue.executeCommand(myController, myInterpreter);
        return Math.cos(Math.toRadians(valOne));
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        inputValue = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
