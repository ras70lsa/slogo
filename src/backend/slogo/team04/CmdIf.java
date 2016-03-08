package backend.slogo.team04;


import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdIf extends CommandTreeNode {
    private INonLinearCommand myExpression;
    private INonLinearCommand myListOfCommands;


    public CmdIf (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        if(CommandFactory.isNonZero(myExpression, myController, myInterpreter)){
            return myListOfCommands.executeCommand(myController, myInterpreter);
        }
        return CommandTreeNode.DOUBLE_ZERO;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        myExpression = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myListOfCommands = new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
        
        
        return this;
    }

}
