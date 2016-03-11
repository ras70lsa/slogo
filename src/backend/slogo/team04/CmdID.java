package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

/**
 * This command will simply return the value a queried from the parent nodes, if the values returned is {@link Double#NaN} then
 * it will directly query the model for the proper active ID value to use
 * @author jonathanim
 *
 */
public class CmdID extends CommandTreeNode {
    protected final static String MY_KEY = "ID";



    public CmdID (CommandTreeNode myParent) {
        super(myParent);

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        if(Double.isNaN(this.getCurrentActiveTurtleID())){
            return myController.ID();
        }else{
            return this.getCurrentActiveTurtleID();
        }
    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdID.MY_KEY;
    }

}
