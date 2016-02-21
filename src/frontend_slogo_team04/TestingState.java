package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import interfaces_slogo_team04.State;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.adapter.JavaBeanBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import properties.ImageProperty;

public class TestingState extends Application {

	ObjectProperty<Color> color;
	BooleanProperty tf;
	ImageProperty image;
	
	public TestingState() {
		
		color = new SimpleObjectProperty<Color>(this, "color", Color.RED);
		tf = new SimpleBooleanProperty(false);
		image = new ImageProperty();
	}
	
	
	public Color getColor(){
		return color.get();
	}
	
	public BooleanProperty getBooleanProperty() {
		return tf;
	}
	
	public ImageProperty getImageProperty() {
		return image;
	}
	
	public ObjectProperty<Color> getColorProperty() {
		return color;
	}
	

	public Stage getUserOptions() {
		
		Stage stage = createNewStage(getStageNodes());
		return stage;
	}
	
	private Collection<Node> getStageNodes() {
		List<Node> nodes = new ArrayList<Node>();
		GuiUserOption factory = new GuiUserOption();
		nodes.add(factory.get(color, "string"));
		nodes.add(factory.get(tf, "string"));
		nodes.add(factory.get(image, "String"));
		return nodes;
		
	}
	
	private Stage createNewStage(Collection<Node> nodes) {
		Group myGroup = new Group();
		Scene myScene = new Scene(myGroup, 500, 500, Color.BEIGE);
		Stage stage = new Stage();
		stage.setScene(myScene);
		int counter = 10;
		for(Node node: nodes) {
			myGroup.getChildren().add(node);
			node.setTranslateX(250);
			node.setTranslateY(counter);
			counter = counter + 50;
		}
		return stage;
	}
	
	

	public static void main(String [] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		TestingState tester = new TestingState();
		Stage stage = tester.getUserOptions();
		stage.show();
		
	}

}
