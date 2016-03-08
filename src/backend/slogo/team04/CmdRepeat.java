package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdRepeat extends CommandTreeNode {
    private static final String REP_COUNT_VAR_NAME = ":repcount";
    private CmdVariable myRepCount;
    private INonLinearCommand myExpression, myCommands;
    



    public CmdRepeat (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double limit = myExpression.executeCommand(myController, myInterpreter);
        double lastValueSeen = CommandTreeNode.DOUBLE_ZERO;
        for(double i = CommandTreeNode.DOUBLE_ONE; i <= limit; i+=CommandTreeNode.DOUBLE_ONE){
            myRepCount.setVariableValue(i, myInterpreter);
            lastValueSeen = myCommands.executeCommand(myController, myInterpreter);
        }
        return lastValueSeen;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        myExpression = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);     
        myRepCount = new CmdVariable(this, REP_COUNT_VAR_NAME);
        myCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        return this;
    }

}
