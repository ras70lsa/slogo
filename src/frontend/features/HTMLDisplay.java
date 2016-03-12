package frontend.features;

import java.util.ResourceBundle;

import constants.CSSPathConstants;
import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HTMLDisplay {

	Stage stage;
	String type;
	public HTMLDisplay(String type) {
		this.type = type;
		makeStage();
		setProperties();
	}
	
	private void setProperties() {
		ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH +
    			ResourceConstants.ENGLISH);
		 stage.setTitle(myBundle.getString("HTML"));
//	     stage.setWidth(DisplayConstants.HTML_SIZE);
//	     stage.setHeight(DisplayConstants.HTML_SIZE);
	}

	private void makeStage() {
		 stage = new Stage();
	     Scene scene = new Scene(new Group());
	     VBox root = new VBox();     
	     final WebView browser = getWebView(this.type);
	     ScrollPane scrollPane = createScrollPane(browser);
	     root.getChildren().add(scrollPane);
	     scene.setRoot(root);
	     stage.setScene(scene);
	}
	
	private ScrollPane createScrollPane(WebView browser) {
		 ScrollPane scrollPane = new ScrollPane();
		 scrollPane.setContent(browser);
	     scrollPane.setFitToHeight(true);
	     scrollPane.setFitToWidth(true);
	     return scrollPane;
	}

	public void show() {
	     stage.show();
	}
	
	public WebView getWebView(String type) {
		 final WebView browser = new WebView();
	     final WebEngine webEngine = browser.getEngine();
	     ClassLoader classLoader = getClass().getClassLoader();
	     String url;
	     if(type.equals("Extended")){
	    	 url = classLoader.getResource(CSSPathConstants.HELP_EXTENDED).toExternalForm();
	     }else{
	    	 url = classLoader.getResource(CSSPathConstants.HELP).toExternalForm();
	     }
	     webEngine.load(url);
	     return browser;
	}

}
