package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.PaletteException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdSetPenColor extends CommandTreeNode {
    protected final static String MY_KEY = "SetPenColor";
    private INonLinearCommand myChild;



    public CmdSetPenColor (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double myProposedIndex = myChild.executeCommand(myController, myInterpreter, debugMe);
        if(SlogoRegexChecker.isPostiveIndex(myProposedIndex)){
            try {
            	return myController.setPenColor((int) myProposedIndex);
            } catch (PaletteException e) {
            	throw new LogicException("Palette Index out of Range");
            }
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
