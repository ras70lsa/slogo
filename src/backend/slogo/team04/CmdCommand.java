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
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        myInterpreter.incept();
        for(int i = 0; i < listOfVariables.size(); i++){ //TODO the list of variables lives in the interpreter, either we pass or lambda, null error
            listOfVariables.get(i).setVariableValue(listOfVariableAssignments.get(i).executeCommand(myController, myInterpreter), myInterpreter);
        }

        double lastCommandValue = CommandTreeNode.DOUBLE_ZERO;
        for(INonLinearCommand cmd : listOfCommands){
            lastCommandValue = cmd.executeCommand(myController, myInterpreter);
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
        String toReturn = myName;
        appendParsableRepresentationWithSpaces(toReturn, listOfVariableAssignments);
        appendParsableRepresentationWithSpaces(toReturn, listOfCommands);
        return toReturn;
    }



}
