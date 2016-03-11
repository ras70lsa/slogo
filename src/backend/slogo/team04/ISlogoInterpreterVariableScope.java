package backend.slogo.team04;



public interface ISlogoInterpreterVariableScope extends ISlogoInterpreter{

    
    
    /**
     * Cause the state tracking object to push a new stack onto its stack of variables, in essence tracking variable scope
     * one level deeper
     * 
     * From a functional perspective, the variable state should now look empty to the parsing code
     * 
     * We should not have to track how deep we have gone, as the nature of what constitutes a proper slogo call 
     * should preclude asymmetrical incept/kick calls
     * 
     */
    void incept();
    
    
   
    
    /**
     * This method will case the state tracking object to 'pop up' one level, forgetting the variables that it currently 
     * has stored, and restoring the variables that were in memory prior to having incepted
     * 
     */
    void kick();
    
    /**
     * This method call will not reset the variables, but will kick all but the last stored maps off the stack, this will be called
     * by the head tree node everytime, incase there was an interrupted parse execution.
     */
    void kickAllButLowest();


}
