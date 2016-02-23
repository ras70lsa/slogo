package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;

import constants.DisplayConstants;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * This class is the class responsible for setting up the interaction with the user.
 * It controls its features as modules
 * @author Ryan St Pierre
 *
 */
public class Display {

	private Collection<Module> modules;
	private Stage myStage;
	private Group myRoot;
	private Scene myScene;
	Code code;
	History history;
	
	public Display() {
		
		modules = new ArrayList<Module>();
		code = new Code(new TestingState());
		history = new History(new TestingState());
		modules.add(code);
		modules.add(history);
		setUpScene();
		addPanes();
		createMenuBar();
		positionModules();
	}
	
	public interface FunctionCall {
		public void call(Module module);
	}
	
	private void positionModules() {
		code.getPane().setTranslateX(100);
		code.getPane().setTranslateY(100);
		history.getPane().setTranslateX(300);
		history.getPane().setTranslateY(50);
		history.getPane().setPrefSize(150, 400);
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
		myRoot.getChildren().add(node);
	}
	
	private void createMenuBar() {
		
		Menu file = new Menu("Options");
		MenuBar bar = new MenuBar(file);
		loopAndDo(m -> addItem(m, file));
		myRoot.getChildren().add(bar);
		
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
		myRoot = new Group();
		myScene = new Scene(myRoot, DisplayConstants.DISPLAY_SIZE, DisplayConstants.DISPLAY_SIZE, 
						DisplayConstants.BACKGROUND_COLOR);
		myStage.setScene(myScene);
	}

}
