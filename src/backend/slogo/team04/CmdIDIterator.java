package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

/**
 * This class will sit atop all nodes that work with all active turtles, it will query and repeat child nodes for all active 
 * turtles
 * 
 * We may have more than one ID node in a chain, because a fwd commmand nested within a fwd command should also work on all of the 
 * active turtles
 * @author jonathanim
 *
 */
public class CmdIDIterator extends CommandTreeNode {
    private INonLinearCommand myConditionalNode, commandsToRunIfSatisfied; //this does not have to be checked, will happen in construction
    private double currentTurtleID;



    public CmdIDIterator (CommandTreeNode myParent, INonLinearCommand myConditionalNode, INonLinearCommand commandsToRunIfSatisfied) {
        super(myParent);
        this.myConditionalNode = myConditionalNode;
        this.commandsToRunIfSatisfied = commandsToRunIfSatisfied;
        currentTurtleID = Double.NaN;
    }

    //public CmdIDIterato

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double toReturn = CommandTreeNode.DOUBLE_ZERO;
        List<Boolean> turtleHasActed = new ArrayList<Boolean>();
        boolean someTurtleHasActed = true; //we are going to essentially iterate through list until it stop changing, because
        //        //the list of active turtles might grow shrink or change as a result of our operations

        boolean[] isTurtleActiveArray; // = myController.activeTurtles();
        while(someTurtleHasActed){
            isTurtleActiveArray = myController.activeTurtles();
            listFalseBooleanAdder(isTurtleActiveArray.length - turtleHasActed.size(), turtleHasActed);
            someTurtleHasActed = false;
            for(int i = 0; i < isTurtleActiveArray.length; i++){
                this.currentTurtleID = (double) i;
                double myCondVal = myConditionalNode.executeCommand(myController, myInterpreter); //checking if the test allows us to run
                if(myCondVal == CommandTreeNode.DOUBLE_ONE && !turtleHasActed.get(i) ){ // now this code will always iterate through everything untill nothing changes
                    someTurtleHasActed = true;
                    turtleHasActed.set(i, true);
                    toReturn = this.commandsToRunIfSatisfied.executeCommand(myController, myInterpreter);
                }
            }
        }
        return toReturn;
    }



    private void listFalseBooleanAdder(int numberOfFalsesToAdd, List<Boolean> myList){
        for(int i = 0; i < numberOfFalsesToAdd; i++){
            myList.add(Boolean.FALSE);
        }
    }


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        this.commandsToRunIfSatisfied.parseString(myScanner, myInterpreter);
        return this;
    }


    /**
     * The current turtle ID underneath the ID node
     */
    @Override
    protected double getCurrentActiveTurtleID(){
        return this.currentTurtleID;
    }

    @Override
    public String parsableRepresentation () {
        String conditionPart, commandsPart;
        conditionPart = myConditionalNode.parsableRepresentation();
        if(!conditionPart.equals(CommandTreeNode.EMPTY_STRING)){
            conditionPart = CommandTreeNode.LEFT_BRACKET + conditionPart + CommandTreeNode.RIGHT_BRACKET;
        }
        commandsPart = commandsToRunIfSatisfied.parsableRepresentation();
        return conditionPart + CommandTreeNode.SPACE + commandsPart;
    }

}
