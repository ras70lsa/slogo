package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdStamp extends CommandTreeNode {
    protected final static String MY_KEY = "Stamp";



    public CmdStamp (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        return myController.stamp();
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdStamp.MY_KEY;
    }

}
