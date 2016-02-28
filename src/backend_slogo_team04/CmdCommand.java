package backend_slogo_team04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;


public class CmdCommand extends CommandTreeNode {
    private String myName;
    private List<CmdVariable> listOfVariables;
    private List<INonLinearCommand> listOfCommands;
    
    private List<INonLinearCommand> listOfVariableAssignments;

    public CmdCommand (CommandTreeNode myParent, String myName) {
        super(myParent);
        this.myName = myName;
        listOfVariableAssignments = new ArrayList<INonLinearCommand>();
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        int i = 0;
        for(CmdVariable var: listOfVariables){
            var.setVariableValue(listOfVariableAssignments.get(i).executeCommand(myController, myInterpreter), myInterpreter);
            i++;
        }
        
        double lastCommandValue = CommandTreeNode.DOUBLE_ZERO;
        for(INonLinearCommand cmd : listOfCommands){
            lastCommandValue = cmd.executeCommand(myController, myInterpreter);
        }
        return lastCommandValue;
    }


    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        for (int i = 0; i < listOfVariables.size(); i++) {
            listOfVariableAssignments.add(CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter));
        }
        return this;
    }
    
    
    protected void setMyState(List<CmdVariable> myVariables, List<INonLinearCommand> myCommands){
        listOfVariables = myVariables;
        listOfCommands = myCommands;
    }

    public String getMyName () {
        return myName;
    }



}
