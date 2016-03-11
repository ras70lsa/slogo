package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdRandom extends CommandTreeNode {
    protected final static String MY_KEY = "Random";
    private INonLinearCommand expOne; // the two nodes that we need to grab

    public CmdRandom(CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double valOne;
        valOne = expOne.executeCommand(myController, myInterpreter);
        if(valOne < CommandTreeNode.DOUBLE_ZERO){
            throw new LogicException("Random double generation range is negative");
        }
        return Math.random() * valOne;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        expOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdRandom.MY_KEY + CommandTreeNode.SPACE + expOne.parsableRepresentation();
    }

}
