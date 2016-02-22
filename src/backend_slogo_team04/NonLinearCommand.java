package backend_slogo_team04;


/**
 * We will construct our parsing/interpreting tree using concretes instances of this abstract
 * @author jonathanim
 *
 */
public interface NonLinearCommand {
   
    /**
     * Will cause the command to actually cause the turtle or drawn screen to change, and will do so through connections between the 
     * subclasses and our controller class
     * 
     * This may not result in direct action, may include flow logic, for example the repeat command will simply call executeCommand on 
     * all of its associated children the appropriate number of times    
     */
    public abstract void executeCommand();
    
    /**
     * The return value of the command, as specified, all commands return values in Slogo and all returned values are of type double
     * @return
     */
    public abstract double getValue();
    
    /**
     * Will be used during parsing to understand who should capture the next string bit from the parser class, the parent or the child
     * @return
     */
    public abstract boolean isDoneConstructing();
   
    /**
     * Each command as defined by the list of possible Slogo commands knows what it needs to instantiate itself
     * @param nextWord
     * @return
     */
    public abstract NonLinearCommand parseString(String nextWord);
    
   
}
