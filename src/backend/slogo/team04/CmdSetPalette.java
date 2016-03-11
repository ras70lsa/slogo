package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdSetPalette extends CommandTreeNode {
    protected final static String MY_KEY = "SetPalette";
    private INonLinearCommand myIndex, myR, myG, myB;
    private double proposedIndex, proposedR, proposedG, proposedB;



    public CmdSetPalette (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        proposedIndex = myIndex.executeCommand(myController, myInterpreter);
        proposedR = myR.executeCommand(myController, myInterpreter);
        proposedG = myG.executeCommand(myController, myInterpreter);
        proposedB = myB.executeCommand(myController, myInterpreter);
        if(SlogoRegexChecker.isPostiveIndex(proposedIndex)
                && SlogoRegexChecker.isRGBValue(proposedR)
                && SlogoRegexChecker.isRGBValue(proposedG)
                && SlogoRegexChecker.isRGBValue(proposedB)){
            return myController.setPalette((int) proposedIndex, (int) proposedR, (int) proposedG, (int) proposedB);
        }else{
            throw new LogicException("Improper input values into set palette command");
        }
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myIndex = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myR = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myG = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        myB = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        
        return this;
    }

    @Override
    public String parsableRepresentation () {
   
        return CmdSetPalette.MY_KEY + CommandTreeNode.SPACE + myIndex.parsableRepresentation() + CommandTreeNode.SPACE + 
                myR.parsableRepresentation() + CommandTreeNode.SPACE +
                myG.parsableRepresentation() + CommandTreeNode.SPACE +
                myB.parsableRepresentation();
    }

}
