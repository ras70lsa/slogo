package frontend.slogo.team04;


import java.util.HashMap;
import java.util.Map;

import backend.slogo.team04.Variable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class WorkspaceManager {

	private Stage mainStage;
	private ListProperty<String> workspaceNames;
	private ListProperty<Workspace> workspaceValue;
	
	public WorkspaceManager() {
		mainStage = new Stage();
		workspaceNames = new SimpleListProperty<>(FXCollections.observableArrayList());
		workspaceValue = new SimpleListProperty<>(FXCollections.observableArrayList());
	}

	public void addNewAndGo(String name) {
		addNew(name);
		show(name);
	}
	
	public void addNew(String name) {
		Workspace workspace = new Workspace(this, mainStage);
		workspaceNames.add(name);
		workspaceValue.add(workspace);
	}

	public void show(String name) {
		mainStage.setScene(workspaceValue.get(workspaceNames.indexOf(name)).getScene());
		mainStage.setTitle(name);
		
	}
	
	public void start() {
		mainStage.show();
	}
	
	public ListProperty<String> getWorkspaceNames() {
		return workspaceNames;
	}
	
	

}
