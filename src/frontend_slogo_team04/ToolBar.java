package frontend_slogo_team04;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ToolBar extends MenuBar{
	
	

	public ToolBar() {
		// TODO Auto-generated constructor stub
	}
	
	private Stage helpBox (String title) {
		 Stage stage = new Stage();
		 stage.setTitle("HTML");
	        stage.setWidth(500);
	        stage.setHeight(500);
	        Scene scene = new Scene(new Group());

	        VBox root = new VBox();     

	        final WebView browser = new WebView();
	        final WebEngine webEngine = browser.getEngine();

	        ScrollPane scrollPane = new ScrollPane();
	        scrollPane.setContent(browser);
	        webEngine.load("file:///C:/Users/Xander/Documents/GitHub/slogo_team04/src/resources/commandPage.html");

	        root.getChildren().addAll(scrollPane);
	        scene.setRoot(root);

	        stage.setScene(scene);
	        stage.show();
	        return stage;
	        
   }

	private void addItem(Module module, Menu file) {
		MenuItem item = new MenuItem(module.getClass().getSimpleName());
		item.setOnAction(e -> module.update());
		file.getItems().add(item);
	}
	
	private void createMenuBar() {
		
		Menu file = new Menu("Options");
		Menu help = new Menu("Help");
		MenuItem commands = new MenuItem("Commands");
		help.getItems().add(commands);
		commands.setOnAction(e->helpBox("Help"));
		MenuBar bar = new MenuBar(file);
		bar.getMenus().add(help);
		loopAndDo(m -> addItem(m, file));
		myRoot.getChildren().add(bar);
		
	
   

}
