package backend.slogo.team04;

public class ExecutionModeNormal implements ISlogoDebugObject {

    @Override
    public boolean shouldPause () {
        return false;
    }

    @Override
    public boolean shouldWake () {
        return true;
    }

    @Override
    public void userAttemptedStep () {
        
    }

    @Override
    public void setDrawReadySynchObject (Object synchObject) {        
    }

    @Override
    public void notifyDrawWaiters(){
        
    }

    @Override
    public Object getDrawReadySynchObject () {
        return this;
    }

    @Override
    public boolean firstTimeStep () {
        return false;
    }

    @Override
    public boolean isDoneStepping () {
        return true;
    }

    @Override
    public void doneStepping () {
        // TODO Auto-generated method stub
        
    }
    
    
    

}
