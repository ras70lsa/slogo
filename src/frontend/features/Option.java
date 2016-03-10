package frontend.features;

import javafx.scene.control.Menu;

public class Option extends MenuFeature {

	Menu clear;
	
	public Option(String title) {
		super(title);
		clear = new Menu(getString("Clear"));
		setUp();
	}
	
	private void setUp() {
		this.getItems().add(clear);
		populateClear();
	}

	private void populateClear() {
		// TODO Auto-generated method stub
		
	}
}
