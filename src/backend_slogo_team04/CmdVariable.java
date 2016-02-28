package backend_slogo_team04;

import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;


public class CmdVariable extends CommandTreeNode {
    private String myName;



    public CmdVariable (CommandTreeNode myParent, String myVarName) {
        super(myParent);
        this.myName = myVarName.toLowerCase();
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        return myInterpreter.getVariableValue(myName);
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }
    
    protected void setVariableValue(Double toSet, ISlogoInterpreter myInterpreter){
        myInterpreter.setVariableValue(myName, toSet);
    }
    
    protected double getVariableValue(ISlogoInterpreter myInterpreter){
        return myInterpreter.getVariableValue(myName);
    }

}
