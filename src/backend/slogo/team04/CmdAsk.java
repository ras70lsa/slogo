package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdAsk extends CommandTreeNode {
    protected final static String MY_KEY = "Ask";
    private INonLinearCommand myTellNode, myCommandList;



    public CmdAsk (CommandTreeNode myParent) {
        super(myParent);
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        myController.pushCurrentActive();
        myTellNode.executeCommand(myController, myInterpreter);
        myInterpreter.incept();
        double toReturn = myCommandList.executeCommand(myController, myInterpreter);
        myInterpreter.kick();
        myController.popCurrentActive();
        return toReturn;

    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myTellNode = CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(CmdTell.MY_KEY, myScanner, this, myInterpreter);
        myCommandList = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        return this;
    }

}
