package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;

import constants.DisplayConstants;
import interfaces_slogo_team04.IHistoryModel;
import interfaces_slogo_team04.IModel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.Controller;

/**
 * This class is the class responsible for setting up the interaction with the user.
 * It controls its features as modules
 * @author Ryan St Pierre
 *
 */
public class Display {

	private Collection<Module> modules;
	private Stage myStage;
	private Group panes;
	private Scene myScene;
	private UserTextInput textInput;
	private History history;
	private View view;
	private VariableFeature variables;
	private IModel model;
	
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
		history = new History(model.getHistory(), null);
		//view = new View(null);
		variables = new VariableFeature(model.getVariables());
		modules.add(history);
		modules.add(textInput);
		modules.add(variables);
	}

	private void addListeners() {
	}

	public interface FunctionCall {
		public void call(Module module);
	}
	
	private void positionModules() {
		//view.position(DisplayConstants.BUFFER, 2 * DisplayConstants.BUFFER, 
		//		DisplayConstants.TEXT_WIDTH, DisplayConstants.VIEW_HEIGHT);
		textInput.position(DisplayConstants.TEXT_X, DisplayConstants.TEXT_Y, 
				DisplayConstants.TEXT_WIDTH, DisplayConstants.TEXT_HEIGHT);
		variables.position(DisplayConstants.VAR_X, DisplayConstants.VAR_Y, 
				DisplayConstants.VAR_WIDTH, DisplayConstants.VAR_HEIGHT);
		history.position(DisplayConstants.HISTORY_X, DisplayConstants.HISTORY_Y, 
				DisplayConstants.HISTORY_WIDTH, DisplayConstants.HISTORY_HEIGHT);
		
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
		
		Menu file = new Menu("Options");
		MenuBar bar = new MenuBar(file);
		loopAndDo(m -> addItem(m, file));
		panes.getChildren().add(bar);
		
	}

	private void addItem(Module module, Menu file) {
		MenuItem item = new MenuItem(module.getClass().getSimpleName());
		item.setOnAction(e -> module.update());
		file.getItems().add(item);
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

}
