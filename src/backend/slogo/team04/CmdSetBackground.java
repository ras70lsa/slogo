package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdSetBackground extends CommandTreeNode {
    protected final static String MY_KEY = "SetBackground";
    private INonLinearCommand myChild;



    public CmdSetBackground (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double myProposedIndex = myChild.executeCommand(myController, myInterpreter);
        if(SlogoRegexChecker.isPostiveIndex(myProposedIndex)){
            return myController.setBackground((int) myProposedIndex);
        }else{
            throw new LogicException("Expected integer like index value as input to SetBackground");
        }
        
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
         myChild = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
         return this;
    }

}
