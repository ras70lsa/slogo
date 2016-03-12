package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


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
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        myInterpreter.incept();
        for(int i = 0; i < listOfVariables.size(); i++){ //TODO the list of variables lives in the interpreter, either we pass or lambda, null error
            listOfVariables.get(i).setVariableValue(listOfVariableAssignments.get(i).executeCommand(myController, myInterpreter, debugMe), myInterpreter);
        }

        double lastCommandValue = CommandTreeNode.DOUBLE_ZERO;
        for(INonLinearCommand cmd : listOfCommands){
            lastCommandValue = cmd.executeCommand(myController, myInterpreter, debugMe);
        }
        myInterpreter.kick();
        return lastCommandValue;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
    	for (int i = 0; i < listOfVariables.size(); i++) {
            listOfVariableAssignments.add(CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter));
        }
        return this;
    }


    public CmdCommand createClone(CommandTreeNode myParent){
        CmdCommand copyToReturn = new CmdCommand(myParent , this.myName);
        copyToReturn.setMyState(this.listOfVariables, this.listOfCommands );
        return copyToReturn;
    }



    protected void setMyState(List<CmdVariable> myVariables, List<INonLinearCommand> myCommands){
        listOfVariables = myVariables;
        listOfCommands = myCommands;
    }

    public String getMyName () {
        return myName;
    }

    @Override
    public String parsableRepresentation () {
        String toReturn = myName + CommandTreeNode.LEFT_BRACKET + variableNames() + CommandTreeNode.RIGHT_BRACKET;
        toReturn = appendParsableRepresentationWithSpaces(toReturn + CommandTreeNode.LEFT_BRACKET, listOfCommands);
        return toReturn + CommandTreeNode.RIGHT_BRACKET;
    }
    
    private String variableNames () {
    	String toReturn = CommandTreeNode.EMPTY_STRING;
    	for (CmdVariable var : listOfVariables){
    		toReturn += CommandTreeNode.SPACE + var.parsableRepresentation();
    	}
    	return toReturn;
    }



}
