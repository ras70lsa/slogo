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
  
    /**
     * Inform the object that user wanted it to wake
     */
    public void userAttemptedStep();
    
    public void setDrawReadySynchObject(Object synchObject);
    
    public void notifyDrawWaiters();
    
    public Object getDrawReadySynchObject();
    
    public boolean firstTimeStep();
    
    public boolean isDoneStepping();
    
    public void doneStepping();
    

}
