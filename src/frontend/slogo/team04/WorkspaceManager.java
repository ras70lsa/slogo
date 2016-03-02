package frontend.slogo.team04;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import backend.slogo.team04.Variable;
import constants.DisplayConstants;
import constants.ResourceConstants;
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
	private Workspace currentWorkspace;
	private ListProperty<String> workspaceNames;
	private ListProperty<Workspace> workspaceValue;
	private ResourceBundle myBundle;
	
	public WorkspaceManager() {
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		mainStage = new Stage();
		workspaceNames = new SimpleListProperty<>(FXCollections.observableArrayList());
		workspaceValue = new SimpleListProperty<>(FXCollections.observableArrayList());
	}

	public void go() {
		currentWorkspace = new Workspace(this, mainStage);
		mainStage.setScene(currentWorkspace.getScene());
		mainStage.setTitle(myBundle.getString("Holder"));
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

	public void save(String name) {
		mainStage.setTitle(name);
		workspaceNames.add(name);
		workspaceValue.add(currentWorkspace);
	}
	
	

}
