package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdSetPenSize extends CommandTreeNode {
    private INonLinearCommand myChild;



    public CmdSetPenSize (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myController.setPenSize(myChild.executeCommand(myController, myInterpreter));
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        myChild = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
