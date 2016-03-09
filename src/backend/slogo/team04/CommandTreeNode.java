package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;



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


    private List<CommandTreeNode> myChildren;


    private CommandTreeNode myParent; //in case we need to do weird scope things in the future
    
    protected CommandTreeNode getMyParent(){
        return this.myParent;
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

    
    protected int[] convertIntegerArrayToIntArray(Integer[] toConvert){
        if(toConvert.length == 0){
            return null;
        }
        int[] toReturn = new int[toConvert.length];
        for(int i = 0; i < toConvert.length; i++){
            toReturn[i] = toConvert[i].intValue(); 
        }
        return toReturn;
        
    }
   






}
