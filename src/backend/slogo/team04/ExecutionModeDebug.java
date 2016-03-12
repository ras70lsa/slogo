package backend.slogo.team04;

public class ExecutionModeDebug implements ISlogoDebugObject {
    private boolean myShouldPause;
    private boolean myShouldWake;
    
    public ExecutionModeDebug () {
      myShouldPause = true;
      myShouldWake = false;
    }
    
    @Override
    /**
     * To support the step function we will return a toggled boolean value
     */
    public boolean shouldPause () {
        myShouldWake = false;
        myShouldPause = !myShouldPause;
        return myShouldPause;
    }

    @Override
    public boolean shouldWake () {
        return myShouldWake;
    }
    
    public void userAttemptedStep(){
        myShouldWake = true;
    }
    
    
    

}
