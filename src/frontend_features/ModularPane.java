package frontend_features;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import visual_states.GuiUserOption;
import javafx.scene.layout.VBox;
import java.util.List;

import constants.DisplayConstants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Parent class for all features with Panes on the screen
 * @author Ryan St Pierre
 */
public abstract class ModularPane implements Module {
	
	private DoubleProperty width;
	private DoubleProperty height;
	private Group myGroup;
	
	public ModularPane() {
		width = new SimpleDoubleProperty();
		height = new SimpleDoubleProperty();
	}
	
	public void getOptions() {
	
		List<Node> properties = getReleventProperties(new GuiUserOption());
		Stage stage = getEmptyStage();
		VBox box= new VBox();
		myGroup.getChildren().add(box);
		for(Node node: properties) {
			box.getChildren().add(node);
		}
		stage.show();
		
	}
	
	private Stage getEmptyStage() {
		myGroup = new Group();
		Scene myScene = new Scene(myGroup, DisplayConstants.USER_OPTION_SIZE, DisplayConstants.USER_OPTION_SIZE, 
				DisplayConstants.USER_OPTION_COLOR);
		Stage stage = new Stage();
		stage.setScene(myScene);
		return stage;
	}

	protected abstract List<Node> getReleventProperties(GuiUserOption factory);

	public String toRGBCode( Color color ){
	    return String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
	}
	
	public void position(double x, double y, double prefWidth, double prefHeight) {
		getPane().setTranslateX(x);
		getPane().setTranslateY(y);
		getPane().setPrefWidth(prefWidth);
		getPane().setPrefHeight(prefHeight);
		width.set(prefWidth);
		height.set(prefHeight);
	}

	public void addCSS(String str) {
		getPane().getStylesheets().add(str);
	}
	
	protected DoubleProperty getWidth() {
		return width;
	}
	
	protected DoubleProperty getHeight() {
		return height;
	}

}
