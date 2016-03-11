package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdSetPenColor extends CommandTreeNode {
    protected final static String MY_KEY = "SetPenColor";
    private INonLinearCommand myChild;



    public CmdSetPenColor (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double myProposedIndex = myChild.executeCommand(myController, myInterpreter);
        if(SlogoRegexChecker.isPostiveIndex(myProposedIndex)){
            return myController.setPenColor((int) myProposedIndex);
        }else{
            throw new LogicException("Expected integer like index value as input to SetPenColor");
        }
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myChild = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdSetPenColor.MY_KEY + CommandTreeNode.SPACE + myChild.parsableRepresentation();
    }

}
