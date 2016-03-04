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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class WorkspaceManager {
	
	private Stage mainStage;
	private Tab currentTab;
	private Workspace currentWorkspace;
	private ListProperty<String> workspaceNames;
	private ListProperty<Workspace> workspaceValue;
	private ResourceBundle myBundle;
	private Group myGroup;
	private Scene myScene;
	
	public WorkspaceManager() {
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		mainStage = new Stage();
		workspaceNames = new SimpleListProperty<>(FXCollections.observableArrayList());
		workspaceValue = new SimpleListProperty<>(FXCollections.observableArrayList());
		setUp();
	}

	private void setUp() {
		myGroup = new Group();
		myScene = new Scene(myGroup,DisplayConstants.DISPLAY_WIDTH, DisplayConstants.DISPLAY_HEIGHT, 
						DisplayConstants.BACKGROUND_COLOR);
		mainStage.setScene(myScene);
		
	}

	public void go() {
		currentWorkspace = new Workspace(this, mainStage);
		currentTab.setContent(currentWorkspace.getGridPane());
		mainStage.setTitle(myBundle.getString("Holder"));
	}
	

	public void show(String name) {
		currentTab.setContent(workspaceValue.get(workspaceNames.indexOf(name)).getGridPane());
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

	public void begin() {
		TabPane tabs = new TabPane();
		myGroup.getChildren().add(tabs);
		currentTab = new Tab();
		tabs.getTabs().add(currentTab);
		go();
		start();
		
	}

}
