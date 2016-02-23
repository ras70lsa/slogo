package frontend_slogo_team04;

import constants.DisplayConstants;
import interfaces_slogo_team04.IState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UserTextInputState implements IState {

	StringProperty language;
	
	public UserTextInputState() {
		language = new SimpleStringProperty("English");
	}
	
	public StringProperty getLanguageProperty() {
		return language;
	}
	
	public Stage getUserOptions() {
		Group myGroup = new Group();
		Scene myScene = new Scene(myGroup, 300, 300, Color.BEIGE);
		Stage stage = new Stage();
		stage.setScene(myScene);
		GuiUserOption gui = new GuiUserOption();
		myGroup.getChildren().add(gui.get(language, DisplayConstants.possibleLangauges));
		return stage;
	}
	
	public String getLanguage(){
		return "English";
	};

}
