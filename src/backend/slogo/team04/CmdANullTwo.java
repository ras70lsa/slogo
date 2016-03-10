package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


/**
 * Null class for cmd testing
 * @author jonathanim
 *
 */
public class CmdANullTwo extends CommandTreeNode {
    



    public CmdANullTwo (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        return 1d;
    }
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        return this;
    }

}
