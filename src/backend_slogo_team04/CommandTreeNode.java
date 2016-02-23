package backend_slogo_team04;

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
    private List<CommandTreeNode> myChildren;
    private CommandTreeNode myParent;
 
    
    
    public CommandTreeNode(Controller myController){
       this(myController, null);
    }
    
    public CommandTreeNode(Controller myController, CommandTreeNode myParent){
        this.myParent = myParent;
        myChildren = new ArrayList<CommandTreeNode>();
    }
    
    protected void addChildNode(CommandTreeNode nodeToAdd){
        this.myChildren.add(nodeToAdd);
    }
    
    
    
    



}
