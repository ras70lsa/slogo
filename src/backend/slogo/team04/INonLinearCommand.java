package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

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
     * @throws LogicException TODO 
     * 
     */
	abstract double executeCommand(ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException;
    

   
    /**
     * Each command as defined by the list of possible Slogo commands knows what it needs to instantiate itself
     * @param myInterpreter TODO
     * @param nextWord
     * @return
     * @throws UserInputException TODO
     */
	abstract INonLinearCommand parseString(SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException;
    
    /**
     * Each command knows how to write itself down via strings such that re-parsing the resultant string will recreate the parse tree
     * of nodes required to run the same command
     * @return
     */
    abstract String parsableRepresentation();
}
