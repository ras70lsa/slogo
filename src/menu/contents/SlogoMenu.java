package menu.contents;

import java.util.ResourceBundle;

import constants.DisplayConstants;
import constants.ResourceConstants;
import interfaces.slogo.team04.IDisplay;
import interfaces.slogo.team04.IModel;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * Sets up the desired MenuBar with functionality defined by its content
 * @author RyanStPierre
 *
 */
public class SlogoMenu {

	IDisplay display;
	IModel model;
	ResourceBundle myBundle;
	MenuBar bar;

	
	public SlogoMenu(IModel model, IDisplay display) { 
		this.display = display;
		this.model = model;
		bar = new MenuBar();
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		createMenuBar();
	}
	
	private void createMenuBar() {
		addMenu(new FileOption(display.getManager(), getString("File")));
		addMenu(new Option(model, display, getString("Options")));
		addMenu(new HelpMenu(myBundle.getString("Help")));
	}

	private void addMenu(Menu menu) {
		bar.getMenus().add(menu);
	}
	
	public MenuBar getBar() {
		return bar;
	}
	
	protected String getString(String input) {
		return myBundle.getString(input);
	}
	
	
}
