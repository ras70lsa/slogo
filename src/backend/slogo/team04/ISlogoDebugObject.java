package backend.slogo.team04;



/**
 * Will be used to control advancement along the parse tree
 *
 */
public interface ISlogoDebugObject {
    
    
   
    /**
     * This method will be queried by the about to execute node to determine if it should pause
     * @return
     */
    public boolean shouldPause();
    
    /**
     * Will be used to determine if the executing code was actually asked by the user to continue execution
     * @return
     */
    public boolean shouldWake();
  
}
