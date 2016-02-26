package backend_slogo_team04;

import java.util.Scanner;

import model.Controller;

/**
 * We will construct our parsing/interpreting tree using concretes instances of this abstract
 * @author jonathanim
 *
 */
public interface INonLinearCommand {
   
    /**
     * Will cause the command to actually cause the turtle or drawn screen to change, and will do so through connections between the 
     * subclasses and our controller class
     * 
     * This may not result in direct action, may include flow logic, for example the repeat command will simply call executeCommand on 
     * all of its associated children the appropriate number of times    
     * 
     * @param myController we delegate actionable events to this object
     * @param myInterpreter This object holds the state required for properly 'running' the Slogo commands
     * 
     */
    public abstract double executeCommand(Controller myController, Interpreter myInterpreter);
    

   
    /**
     * Each command as defined by the list of possible Slogo commands knows what it needs to instantiate itself
     * @param nextWord
     * @return
     */
    public abstract INonLinearCommand parseString(Scanner myScanner);
    
   
}
