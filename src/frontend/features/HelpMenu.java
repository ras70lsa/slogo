package frontend.features;

public class HelpMenu extends MenuFeature {

	public HelpMenu(String title) {
		super(title);	
		setUp();
	}

	private void setUp() {
		this.getItems().add(createMenuItem(getString("CommandHelp"), e-> helpBox("Basic")));
		this.getItems().add(createMenuItem(getString("CommandHelpAdvanced"), e-> helpBox("Extended")));
		
	}
	
	private void helpBox (String type) {
		HTMLDisplay display = new HTMLDisplay(type);
		display.show();
   }
	

	
}
