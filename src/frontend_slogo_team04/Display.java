package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;

import constants.CSSPathConstants;
import constants.DisplayConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
import frontend_features.AlertMessage;
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
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
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
	private Controller controller;
	private GridPane gridPane;
	private Accordion leftFeatures;
	private Accordion rightFeatures;
	private SlogoMenu menuBar;
	
	public Display(IModel iModel, Controller controller) {
		modules = new ArrayList<Module>();
		this.controller = controller;
		model = iModel;
		setUpScene();
		position();
		addListeners();
	}
	
	private void createAccordions() {
		makeLeft();
		makeRight();
	}

	private void makeRight() {
		history = new History(model.getHistory());
		variables = new VariableFeature(model.getVariables());
		commands = new CommandFeature(model.getCommandInterface());
		rightFeatures = new Accordion();
		rightFeatures.getPanes().addAll(history, commands, variables);
		rightFeatures.setExpandedPane(variables);
		rightFeatures.expandedPaneProperty().addListener((a, oldView, newView) ->
			controlCollapse(oldView, newView, rightFeatures));
		
	}

	private void makeLeft() {
		//do nothing for now
		leftFeatures = new Accordion();
	}

	public IModel getHistoryModel() {
		return model;
		
	}
	private void createModules(Controller controller) {
		textInput= new UserTextInput(controller);
		view = new View(model.getViewInterface());
		addModules();
	}

	private void addModules() {
		modules.add(textInput);
		modules.add(view);
	}

	private void addListeners() {
		history.getInteracted().addListener(e -> textInput.setText(history.getSelectedText()));
		history.getExecute().addListener(e -> parse(history.getSelectedText()));
		commands.getInteracted().addListener(e -> textInput.append(commands.getSelectedText()));
	}

	private void parse(String selectedText) {
		try {
			controller.parseString(selectedText);
		} catch (UserInputException | LogicException e) {
			AlertMessage alert = new AlertMessage(e.getMessage());
			alert.displayError();
		}
	}

	public interface FunctionCall {
		public void call(Module module);
	}
	
	private void position() {
		gridPane = new GridPane();
		gridPane.getStylesheets().add(CSSPathConstants.GRID_PANE);
		add(gridPane);
		gridPane.add(leftFeatures, 1, 1, 1, 3);
		gridPane.add(view.getPane(), 2, 2);
		gridPane.add(textInput.getPane(), 2, 3);
		gridPane.add(rightFeatures, 3, 1, 3, 3);
		gridPane.add(menuBar, 2, 1);
		setSizes();
		
	}
	
	private void setSizes() {
		leftFeatures.setPrefWidth(DisplayConstants.ACCORDION_WIDTH);
		rightFeatures.setPrefWidth(DisplayConstants.ACCORDION_WIDTH);
		view.getPane().setPrefHeight(DisplayConstants.VIEW_HEIGHT);
		view.getPane().setPrefWidth(DisplayConstants.VIEW_WIDTH);
		textInput.getPane().setPrefHeight(DisplayConstants.TEXT_HEIGHT);
	}
	
	private void controlCollapse(TitledPane oldView, TitledPane newView, Accordion accordion) {
		//Test to see if any are open
		boolean doSomething = true;
		for(TitledPane pane: accordion.getPanes()) {
			if(pane.isExpanded()) {
				doSomething = false;
				break;
			}
		}
		
		if(doSomething && oldView !=null) {
			accordion.setExpandedPane(oldView);
		}
	}
	
	private void add(Node node) {
		panes.getChildren().add(node);
	}
	
	private void createMenuBar() {
		menuBar = new SlogoMenu(model, this);
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
		createModules(controller);
		createAccordions();
		createMenuBar();
	}
	
	public View getView() {
		return view;
	}

}
