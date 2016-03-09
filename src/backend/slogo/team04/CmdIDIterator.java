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
    private INonLinearCommand myChildNode;
    private double currentTurtleID;



    public CmdIDIterator (CommandTreeNode myParent, INonLinearCommand myChildNode) {
        super(myParent);
        this.myChildNode = myChildNode;
        currentTurtleID = Double.NaN;
    }
    
    //public CmdIDIterato

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        double toReturn = CommandTreeNode.DOUBLE_ZERO;
        List<Boolean> turtleHasActed = new ArrayList<Boolean>();
        boolean someTurtleHasActed = true; //we are going to essentially iterate through list until it stop changing, because
        //        //the list of active turtles might grow shrink or change as a result of our operations

        boolean[] isTurtleActiveArray; // = myController.activeTurtles();
        while(someTurtleHasActed){
            //we need to add a check to see if the active turtles arrays has grown
            isTurtleActiveArray = myController.activeTurtles();
            listFalseBooleanAdder(isTurtleActiveArray.length - turtleHasActed.size(), turtleHasActed);
            someTurtleHasActed = false;
            for(int i = 0; i < isTurtleActiveArray.length; i++){
                if(isTurtleActiveArray[i] && !turtleHasActed.get(i) ){
                    someTurtleHasActed = true;
                    turtleHasActed.set(i, true);
                    this.currentTurtleID = (double) i;
                    //now run the code with this turtle id value
                    toReturn = this.myChildNode.executeCommand(myController, myInterpreter);
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
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        return this;
    }


    /**
     * The current turtle ID underneath the ID node
     */
    @Override
    protected double getCurrentActiveTurtleID(){
        return this.currentTurtleID;
    }

}
