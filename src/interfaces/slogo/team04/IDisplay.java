package interfaces.slogo.team04;

import frontend.features.VariableFeature;
import frontend.features.View;
import frontend.slogo.team04.WorkspaceManager;

/**
 * Defines what points can be accessed externally from a view
 *
 */
public interface IDisplay {

	/**
	 * @return View
	 */
	View getView();

	/**
	 * Each Display contains a WorkspaceManager. The WorkspaceManager is responsible for holding the workspaces
	 * that have been created and saved.  The WorkspaceManager also navigates between these Workspaces
	 * @return
	 */
	WorkspaceManager getManager();


    VariableFeature getVariableFeature();
    void disableStep();

}
