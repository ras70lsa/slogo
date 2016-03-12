package constants;

import javafx.scene.paint.Color;

public class DisplayConstants {

	public static final double DISPLAY_HEIGHT= 670;
	public static final double DISPLAY_WIDTH= 1200;
	public static final double HALF = 0.5;
	
	public static final Color BACKGROUND_COLOR = Color.LIGHTCYAN;
	
	public static final int BUFFER = 20;
	
	public static final double TEXT_HEIGHT = 127;
	public static final double TEXT_WIDTH = 500;
	
	public static final double TEXT_X = BUFFER;
	public static final double TEXT_Y = DISPLAY_HEIGHT - TEXT_HEIGHT - BUFFER;
	
	public static final double TURTLE_WIDTH = 40;
	public static final double TURTLE_HEIGHT = 40;
	
	public static final double VAR_WIDTH = 250;
	public static final double VAR_HEIGHT = TEXT_HEIGHT;
	
	
	public static final double VIEW_HEIGHT = 480;
	public static final double VIEW_WIDTH = 650;
	
	public static final double ACCORDION_WIDTH = (DISPLAY_WIDTH - VIEW_WIDTH) * HALF;
	
	public static final double START_WIDTH = 400;
	public static final double START_HEIGHT = 400;
	
	public static final String RESOURCES_PATH = "resources.languages/";
	
	public static final double USER_OPTION_SIZE = 200;
	public static final Color USER_OPTION_COLOR = Color.LIGHTGOLDENRODYELLOW;
	
	public static final int HTML_SIZE = 600;
	public static final int RGB_MAX = 255;
	
	public static final int HTML_HEIGHT = 1000;
	public static final String[] possibleLangauges = {"English", "Chinese", "French", "German",
			"Italian", "Portuguese", "Russian", "Spanish"};

}
