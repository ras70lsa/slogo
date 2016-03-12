package frontend.slogo.team04;


import constants.CSSPathConstants;
import constants.DisplayConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
import frontend.features.AlertMessage;
import frontend.features.CommandFeature;
import frontend.features.History;
import frontend.features.LocationFeature;
import frontend.features.PreferenceFeature;
import frontend.features.UserTextInput;
import frontend.features.VariableFeature;
import frontend.features.View;
import interfaces.slogo.team04.IDisplay;
import interfaces.slogo.team04.IModel;
import javafx.scene.control.Accordion;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import menu.contents.SlogoMenu;
import model.Controller;

/**
 * This class is the class responsible for setting up the interaction with the user.
 * It controls its features as modules
 * @author Ryan St Pierre
 *
 */
public class Display implements IDisplay {

	public static final int FIRST = 1; 
	public static final int SECOND = 2; 
	public static final int THIRD = 3; 
	
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
	private WorkspaceManager workspaces;
	
	public Display(IModel iModel, Controller controller, double width, double height, Stage 
			stage, WorkspaceManager workspaces) {
		this.workspaces = workspaces;
		this.controller = controller;
		model = iModel;
		createFeatures();
		position();			
		addListeners();
		controller.setDisplay(this);
		controller.update();
		
	}
	
	public void createFeatures() {
		createModules(controller);
		createAccordions();
		createMenuBar();
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
//		rightFeatures.expandedPaneProperty().addListener((a, oldView, newView) ->
//			controlCollapse(oldView, newView, rightFeatures));
		
	}

	private void makeLeft() {
		leftFeatures = new Accordion();
		LocationFeature actors = new LocationFeature(model.getViewInterface());
		leftFeatures.getPanes().add(actors);
		PreferenceFeature pallets = new PreferenceFeature(model.getViewInterface());
		leftFeatures.getPanes().add(pallets);
	}
	
	private void createModules(Controller controller) {
		textInput= new UserTextInput(controller);
		view = new View(model.getViewInterface());
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
	
	private void position() {
		gridPane = new GridPane();
		gridPane.getStylesheets().add(CSSPathConstants.GRID_PANE);
		gridPane.add(leftFeatures, FIRST, FIRST, FIRST, THIRD);
		gridPane.add(view.getPane(), SECOND, SECOND);
		gridPane.add(textInput.getPane(), SECOND, THIRD);
		gridPane.add(rightFeatures, THIRD, FIRST, THIRD, THIRD);
		gridPane.add(menuBar.getBar(), SECOND, FIRST);
		setSizes();
		
	}
	
	private void setSizes() {
		leftFeatures.setPrefWidth(DisplayConstants.ACCORDION_WIDTH);
		rightFeatures.setPrefWidth(DisplayConstants.ACCORDION_WIDTH);
		view.getPane().setPrefHeight(DisplayConstants.VIEW_HEIGHT);
		view.getPane().setPrefWidth(DisplayConstants.VIEW_WIDTH);
		textInput.getPane().setPrefHeight(DisplayConstants.TEXT_HEIGHT);
	}
	
	/**
	 * Forces one title pane to be open in the accordion if desired
	 */
//	private void controlCollapse(TitledPane oldView, TitledPane newView, Accordion accordion) {
//		//Test to see if any are open
//		boolean doSomething = true;
//		for(TitledPane pane: accordion.getPanes()) {
//			if(pane.isExpanded()) {
//				doSomething = false;
//				break;
//			}
//		}
//		
//		if(doSomething && oldView !=null) {
//			accordion.setExpandedPane(oldView);
//		}
//	}
	
	private void createMenuBar() {
		menuBar = new SlogoMenu(model, this);
	}

	public View getView() {
		return view;
	}
	
	public WorkspaceManager getManager() {
		return workspaces;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

    @Override
    public VariableFeature getVariableFeature () {
        return variables;
    }

    @Override
    public void disableStep () {
        textInput.disableStep();
        
    }

}
