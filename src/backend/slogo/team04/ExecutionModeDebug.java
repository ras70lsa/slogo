package backend.slogo.team04;

public class ExecutionModeDebug implements ISlogoDebugObject {
  
    private boolean myShouldWake;
    private Object mySynchObject;
    private boolean firstStep;
    private boolean myIsDoneStepping;

    public ExecutionModeDebug () {
        myShouldWake = false;
        firstStep = true;
        myIsDoneStepping = false;
    }

    @Override
    /**
     * To support the step function we will return a toggled boolean value
     */
    public boolean shouldPause () {
        myShouldWake = false;
        return true;
    }

    @Override
    public boolean shouldWake () {
        return myShouldWake;
    }

    public void userAttemptedStep(){
        myShouldWake = true;
    }

    @Override
    public void setDrawReadySynchObject (Object synchObject) {
        mySynchObject = synchObject;

    }

    @Override
    public void notifyDrawWaiters(){
        mySynchObject.notifyAll();
    }

    @Override
    public Object getDrawReadySynchObject () {
        return mySynchObject;
    }
    
    public boolean firstTimeStep(){
        if(firstStep){
            firstStep = false;
            return true;
        }
        return false;
        
    }

    
    
    @Override
    public boolean isDoneStepping () {
        return myIsDoneStepping;
    }

    @Override
    public void doneStepping () {
        myIsDoneStepping = true;
        
    }





}
