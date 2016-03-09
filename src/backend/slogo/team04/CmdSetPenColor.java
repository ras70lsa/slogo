package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdSetPenColor extends CommandTreeNode {
    private INonLinearCommand myChild;



    public CmdSetPenColor (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double myProposedIndex = myChild.executeCommand(myController, myInterpreter);
        if(SlogoRegexChecker.isIndexValue(myProposedIndex)){
            return myController.setPenColor((int) myProposedIndex);
        }else{
            throw new LogicException("Expected integer like index value as input to SetPenColor");
        }
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        myChild = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

}
