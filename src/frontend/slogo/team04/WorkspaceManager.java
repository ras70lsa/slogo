package frontend.slogo.team04;

public class WorkspaceManager {

	public void addNew(String name) {
		Workspace workspace = new Workspace();
		workspace.start(this);
	}

}
