package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;

import constants.DisplayConstants;
import interfaces_slogo_team04.State;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * State for the text input
 * @author Ryan St Pierre
 */

public class UserTextInputState extends State {

	StringProperty language;
	
	public UserTextInputState() {
		language = new SimpleStringProperty("English");
	}
	
	public StringProperty getLanguageProperty() {
		return language;
	}
	
	public String getLanguage(){
		return "English";
	}

	protected Collection<Node> getStageNodes() {
		Collection<Node> myStageNodes = new ArrayList<Node>();
		myStageNodes.add(getFactory().get(language, DisplayConstants.possibleLangauges));
		return myStageNodes;
	}

}
