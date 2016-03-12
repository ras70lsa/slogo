package frontend.slogo.team04;

import constants.DisplayConstants;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Creates a Menu that allows the user to select one of the available languages
 * @author RyanStPierre
 *
 */
public class LanguageSelector extends Menu  {

	StringProperty language;
	
	/**
	 * Takes in a StringProperty to edit
	 * @param language
	 */
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
