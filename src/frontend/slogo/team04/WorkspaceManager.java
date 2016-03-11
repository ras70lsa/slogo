package frontend.slogo.team04;


import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import archive.ArchiveWorkspace;
import archive.XMLReader;
import archive.XMLWriter;
import constants.DisplayConstants;
import constants.ResourceConstants;
import exceptions.ImproperFileException;
import frontend.features.AlertMessage;
import frontend.features.SaveAlert;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WorkspaceManager {
	
	private Stage mainStage;
	private Tab currentTab;
	private Workspace currentWorkspace;
	private ListProperty<String> workspaceNames;
	private ListProperty<ArchiveWorkspace> workspaceValue;
	private ResourceBundle myBundle;
	private Group myGroup;
	private Scene myScene;
	private TabPane tabs;
	
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
		ArchiveWorkspace archive = workspaceValue.get(workspaceNames.indexOf(name));
		currentWorkspace = archive.getNew(this, mainStage);
		currentTab.setContent(currentWorkspace.getGridPane());
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
		workspaceValue.add(new ArchiveWorkspace(currentWorkspace));
	}

	public void begin() {
		tabs = new TabPane();
		myGroup.getChildren().add(tabs);
		tabs.setOnMouseClicked(e -> makeCurrent());
		addTab();
		start();
	}
	
	private void makeCurrent() {
		currentTab = tabs.getSelectionModel().getSelectedItem();
	}

	public void addTab() {
		currentTab = new Tab();
		currentTab.setText(myBundle.getString("Active"));
		currentTab.setOnCloseRequest(e -> attemptedClose(e));
		tabs.getTabs().add(currentTab);
		tabs.getSelectionModel().select(currentTab);
		go();
	}

	/**
	 * Prompts the user to save before closing a workspace.  Stops the closing if cancel is selected.
	 */
	private void attemptedClose(Event e) {
		SaveAlert promptSave = new SaveAlert();
		ButtonType selection = promptSave.saveDesired();
		if(selection == SaveAlert.YES) {
			getDialog();
		} else if (selection == ButtonType.CANCEL) {
			e.consume();
		}
	}

	public void getDialog() {
		TextInputDialog input = createTextInput(myBundle.getString("SaveDialog"));
		input.setOnCloseRequest(e -> saveWorkspace(input));
	}
	
	private TextInputDialog createTextInput(String content ) {
		TextInputDialog nameInput = new TextInputDialog();
		nameInput.setContentText(content);
		nameInput.show();
		return nameInput;
	}
	
	private void saveWorkspace(TextInputDialog nameInput) {
		if(nameInput.getResult()!=null) {
			save(nameInput.getResult());
		}	
	}

	public void saveCommands() {
		TextInputDialog input = createTextInput(myBundle.getString("SaveCommandPrompt"));
		input.setOnCloseRequest(e-> saveUserDefinedCommands(input));
	}
	
	private void saveUserDefinedCommands(TextInputDialog nameInput) {
		if(nameInput.getResult()!=null) {
			List<String> commandNames = currentWorkspace.getModel().getExecutionState().getCommands();
			XMLWriter writer = new XMLWriter();
			writer.save(nameInput.getResult(), commandNames);
		}
	}

	public void loadCommandScript() {
		FileChooser load = new FileChooser();
		File selectedFile = load.showOpenDialog(mainStage);
		if (selectedFile != null) {
			XMLReader reader = new XMLReader();
			try {
				reader.execute(selectedFile);
			} catch (Exception e) {
				AlertMessage alert = new AlertMessage(e.getMessage());
				alert.displayError();
			}
		}
	}

}
