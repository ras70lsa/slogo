package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdRepeat extends CommandTreeNode {
    protected final static String MY_KEY = "Repeat";
    private static final String REP_COUNT_VAR_NAME = ":repcount";
    private CmdVariable myRepCount;
    private INonLinearCommand myExpression, myCommands;
    



    public CmdRepeat (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double limit = myExpression.executeCommand(myController, myInterpreter);
        double lastValueSeen = CommandTreeNode.DOUBLE_ZERO;
        for(double i = CommandTreeNode.DOUBLE_ONE; i <= limit; i+=CommandTreeNode.DOUBLE_ONE){
            myInterpreter.incept();
            myRepCount.setVariableValue(i, myInterpreter);
            lastValueSeen = myCommands.executeCommand(myController, myInterpreter);
            myInterpreter.kick();
        }
        return lastValueSeen;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myExpression = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);     
        myRepCount = new CmdVariable(this, REP_COUNT_VAR_NAME);
        myCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdRepeat.MY_KEY + CommandTreeNode.SPACE + myExpression.parsableRepresentation() + CommandTreeNode.SPACE + myCommands.parsableRepresentation();
    }

}
