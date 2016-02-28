package frontend_features;

import constants.CSSPathConstants;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HTMLDisplay {

	Stage stage;
	public HTMLDisplay() {
		makeStage();
		setProperties();
	}
	
	private void setProperties() {
		 stage.setTitle("HTML");
	     stage.setWidth(500);
	     stage.setHeight(500);
	}

	private void makeStage() {
		 Stage stage = new Stage();
	     Scene scene = new Scene(new Group());
	     VBox root = new VBox();     

	     final WebView browser = new WebView();
	     final WebEngine webEngine = browser.getEngine();
	     ScrollPane scrollPane = new ScrollPane();
	     scrollPane.setContent(browser);
	     scrollPane.setFitToHeight(true);
	     scrollPane.setFitToWidth(true);
	     ClassLoader classLoader = getClass().getClassLoader();
	 	 String url = classLoader.getResource(CSSPathConstants.HELP).toExternalForm();  
	     webEngine.load(url);
	     
	     root.getChildren().add(scrollPane);
	     scene.setRoot(root);
	     stage.setScene(scene);
	}
	
	public void show() {
	     stage.show();
	}

}
