package interfaces.slogo.team04;

import frontend.features.VariableFeature;
import frontend.features.View;
import frontend.slogo.team04.WorkspaceManager;

public interface IDisplay {


    View getView();
    WorkspaceManager getManager();
    VariableFeature getVariableFeature();
    void disableStep ();

}
