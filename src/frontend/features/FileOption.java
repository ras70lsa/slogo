package frontend.features;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import frontend.slogo.team04.WorkspaceManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileOption extends MenuFeature{

	private WorkspaceManager manager;
	private Menu open;
	
	/**
	 * The map of items in the Menu with its respect Event Action that is called upon action
	 * To add a MenuItem to this item add it below with the desired name and EventHandler/function call
	 * Since the preferred ordering is unknown a TreeMap is used to default to alphabetical
	 */
	Map<String, EventHandler<ActionEvent>> menuItems = new TreeMap<>();{
	     menuItems.put(getString("New"), e-> create());
	     menuItems.put(getString("Save"), e-> manager.getDialog());
	};
	
	public FileOption(WorkspaceManager manager) {
		super();
		this.manager= manager;
		this.setText(getString("File"));
		setUp();
		addListeners();
	}
	
	private void addListeners() {
		manager.getWorkspaceNames().addListener((a,b,c) -> makeOpenMenuItems(c));
	}

	private void setUp() {
		
		for(Entry<String, EventHandler<ActionEvent>> entry: menuItems.entrySet()) {
			this.getItems().add(createMenuItem(entry.getKey(), entry.getValue()));
		}
		open = new Menu(getString("Open"));
		this.getItems().add(open);
		makeOpenMenuItems(manager.getWorkspaceNames());
	}
	
	private void makeOpenMenuItems(ObservableList<String> c) {
		open.getItems().clear();
		for(String name: c) {
			addToOpen(name);
		}
	}
	
	private void addToOpen(String name) {
		open.getItems().add(createMenuItem(name, e-> manager.show(name)));
	}
	
	public MenuItem createMenuItem(String title, EventHandler<ActionEvent> event) {
		MenuItem item = new MenuItem(title);
		item.setOnAction(event);
		return item;
	}
	
	private void create() {
		manager.addTab();
	}

}
