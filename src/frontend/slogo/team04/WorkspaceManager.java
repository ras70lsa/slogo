package frontend.slogo.team04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.stage.Stage;

public class WorkspaceManager {

	private Stage mainStage;
	private Map<String, Workspace> workspaces;
	
	public WorkspaceManager() {
		mainStage = new Stage();
		workspaces = new HashMap<>();
	}
	
	public void addNewAndGo(String name) {
		addNew(name);
		show(name);
	}
	
	public void addNew(String name) {
		Workspace workspace = new Workspace(this, mainStage);
		workspaces.put(name, workspace);
	}

	public void show(String name) {
		mainStage.setScene(workspaces.get(name).getScene());
		
	}
	
	public void start() {
		mainStage.show();
	}

}
