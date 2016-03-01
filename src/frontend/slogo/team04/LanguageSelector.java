package frontend.slogo.team04;

import constants.DisplayConstants;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import visual_states.GuiUserOption;

public class LanguageSelector extends Menu  {

	StringProperty language;
	
	public LanguageSelector(StringProperty language) {
		super();
		this.language = language;
		create();
	}

	private void create() {
		this.setText("Langauge");
		setChildren();
	}

	private void setChildren() {
		for(String possible: DisplayConstants.possibleLangauges) {
			MenuItem item = new MenuItem(possible);
			item.setOnAction(e -> language.set(item.getText()));
			this.getItems().add(item);
		}
	}

}
