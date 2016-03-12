package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import exceptions.LogicException;



/**
 * This class forms the basis for how we are going to parse the input text, each word allowable in the language will expects certain
 * types of words to follow, each subsequent word suggesting its own logic
 * 
 * Thus we are using recursion, and passing the scanner object containing the text to be parsed around in order to 
 * @author jonathanim
 *
 */
public abstract class CommandTreeNode implements INonLinearCommand {

    protected static final double DOUBLE_ZERO = 0d;
    protected static final double DOUBLE_ONE = 1d;
    protected static final String SPACE = " ";
    protected static final String LEFT_BRACKET = " [ ";
    protected static final String RIGHT_BRACKET = " ] ";
    protected static final String EMPTY_STRING = " ";
    protected static final String LEFT_PAREN = " ( ";
    protected static final String RIGHT_PAREN = " ) ";


    private List<CommandTreeNode> myChildren;


    private CommandTreeNode myParent; //in case we need to do weird scope things in the future

    protected CommandTreeNode getMyParent(){
        return this.myParent;
    }

    protected void setMyParent(CommandTreeNode myParent){
        this.myParent = myParent;
    }




    public CommandTreeNode(CommandTreeNode myParent){
        this.myParent = myParent;
        myChildren = new ArrayList<CommandTreeNode>();
    }

    protected void addChildNode(CommandTreeNode nodeToAdd){
        if(nodeToAdd != null){
            this.myChildren.add(nodeToAdd);
        }
    }

    /**
     * Standard behavior is to simply query the parent for the current active ID value
     * @return
     */
    protected double getCurrentActiveTurtleID(){
        return this.myParent.getCurrentActiveTurtleID();
    }


    protected int[] convertIntegerArrayToIntArray(Object[] toConvert){
        if(toConvert.length == 0){
            return null;
        }
        int[] toReturn = new int[toConvert.length];
        for(int i = 0; i < toConvert.length; i++){
            toReturn[i] = ((Integer)toConvert[i]).intValue(); 
        }
        return toReturn;

    }

    /**
     * If you want a node to exhibit different aggregation behavior simply override this method in the related class
     * @return
     */
    protected BiFunction<Double, Double, Double> getMyUnlimitedParameterBehavior(){
        return (x,y) ->  x + y;
    }

    protected String appendParsableRepresentationWithSpaces(String toBuild, List<INonLinearCommand> myList){
        String toReturn = toBuild;
        for(INonLinearCommand val : myList){
            toReturn = toReturn + CommandTreeNode.SPACE + val.parsableRepresentation();
        }
        return toReturn;
    }

    protected void listFalseBooleanAdder (int numberOfFalsesToAdd, List<Boolean> myList) {
        for(int i = 0; i < numberOfFalsesToAdd; i++){
            myList.add(Boolean.FALSE);
        }
    }

    protected void ifDebugPauseExecution(ISlogoDebugObject debugMe) throws LogicException{
        synchronized(debugMe) {
            while(!debugMe.shouldWake()){ 
                try {
                    debugMe.wait();
                }
                catch (InterruptedException e) {
                    throw new LogicException("Debug thread was interrupted");
                }
            }
        }
        
    }







}
