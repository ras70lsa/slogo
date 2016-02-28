package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;

import constants.DisplayConstants;
import frontend_features.CommandFeature;
import frontend_features.History;
import frontend_features.Module;
import frontend_features.SlogoMenu;
import frontend_features.UserTextInput;
import frontend_features.VariableFeature;
import frontend_features.View;
import interfaces_slogo_team04.IDisplay;
import interfaces_slogo_team04.IModel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Controller;
import visual_states.HistoryUIState;

/**
 * This class is the class responsible for setting up the interaction with the user.
 * It controls its features as modules
 * @author Ryan St Pierre
 *
 */
public class Display implements IDisplay {

	private Collection<Module> modules;
	private Stage myStage;
	private Group panes;
	private Scene myScene;
	private UserTextInput textInput;
	private History history;
	private View view;
	private VariableFeature variables;
	private IModel model;
	private CommandFeature commands;
	
	public Display(IModel iModel, Controller controller) {
		modules = new ArrayList<Module>();
		model = iModel;
		createModules(controller);
		setUpScene();
		addPanes();
		createMenuBar();
		positionModules();
		addListeners();
	}
	
	public IModel getHistoryModel() {
		return model;
		
	}
	private void createModules(Controller controller) {
		textInput= new UserTextInput(controller);
		history = new History(model.getHistory());
		view = new View(model.getViewInterface());
		variables = new VariableFeature(model.getVariables());
		commands = new CommandFeature(model.getCommandInterface());
		modules.add(history);
		modules.add(textInput);
		modules.add(variables);
		modules.add(view);
		modules.add(commands);
	}

	private void addListeners() {
		history.getInteracted().addListener(e -> textInput.setText(history.getSelected()));
		commands.getInteracted().addListener(e -> textInput.append(commands.getText()));
	}

	public interface FunctionCall {
		public void call(Module module);
	}
	
	private void positionModules() {
		view.position(DisplayConstants.BUFFER, 2 * DisplayConstants.BUFFER, 
				DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		textInput.position(DisplayConstants.TEXT_X, DisplayConstants.TEXT_Y, 
				DisplayConstants.TEXT_WIDTH, DisplayConstants.TEXT_HEIGHT);
		variables.position(DisplayConstants.VAR_X, DisplayConstants.VAR_Y, 
				DisplayConstants.VAR_WIDTH, DisplayConstants.VAR_HEIGHT);
		history.position(DisplayConstants.HISTORY_X, DisplayConstants.HISTORY_Y, 
				DisplayConstants.HISTORY_WIDTH, DisplayConstants.HISTORY_HEIGHT);
		commands.position(DisplayConstants.COM_X, DisplayConstants.COM_Y, 
				DisplayConstants.COM_WIDTH, DisplayConstants.COM_HEIGHT);
		
	}
	
	private void loopAndDo(FunctionCall method) {
		for(Module module: modules) {
			method.call(module);
		}
	}
	
	private void addPanes() {
		loopAndDo( m -> add(m.getPane()));
	}
	
	private void add(Node node) {
		panes.getChildren().add(node);
	}
	
	private void createMenuBar() {
		panes.getChildren().add(new SlogoMenu(model, this));
	}
	
	public void start() {
		myStage.show();
	}
	
	public void setUpScene() {
		
		myStage = new Stage();
		panes = new Group();
		myScene = new Scene(panes, DisplayConstants.DISPLAY_WIDTH, DisplayConstants.DISPLAY_HEIGHT, 
						DisplayConstants.BACKGROUND_COLOR);
		myStage.setScene(myScene);
	}
	
	public View getView() {
		return view;
	}

}
