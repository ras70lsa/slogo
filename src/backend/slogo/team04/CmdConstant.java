package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdConstant extends CommandTreeNode {

    private double myValue;

    public CmdConstant (CommandTreeNode myParent, double myValue) {
        super(myParent);
        this.myValue = myValue;
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        return myValue;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return String.valueOf(myValue);
    }
    
    

}
