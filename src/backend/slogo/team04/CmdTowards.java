package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdTowards extends CommandTreeNode {
    protected final static String MY_KEY = "SetTowards";
    INonLinearCommand myX, myY;



    public CmdTowards (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        return myController.towards(myX.executeCommand(myController, myInterpreter, debugMe)
                                    , myY.executeCommand(myController, myInterpreter, debugMe), 
                                    new CmdID(this).executeCommand(myController, myInterpreter, debugMe));
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myX = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myY = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdTowards.MY_KEY + CommandTreeNode.SPACE + myX.parsableRepresentation() + CommandTreeNode.SPACE + myY.parsableRepresentation();
    }

}
