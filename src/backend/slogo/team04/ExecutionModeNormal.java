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

}
