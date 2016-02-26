package constants;

import javafx.scene.paint.Color;

public class DisplayConstants {

	public static final double DISPLAY_HEIGHT= 670;
	public static final double DISPLAY_WIDTH= 1200;
	public static final Color BACKGROUND_COLOR = Color.LIGHTCYAN;
	
	public static final int BUFFER = 20;
	
	public static final double TEXT_HEIGHT = 200;
	public static final double TEXT_WIDTH = 470;
	
	public static final double TEXT_X = BUFFER;
	public static final double TEXT_Y = DISPLAY_HEIGHT - TEXT_HEIGHT - BUFFER;
	
	public static final double HISTORY_WIDTH = 350;
	public static final double HISTORY_HEIGHT = DISPLAY_HEIGHT - 3 * BUFFER;
	
	public static final double TURTLE_WIDTH = 40;
	public static final double TURTLE_HEIGHT = 40;
	
	public static final double VAR_WIDTH = 150;
	public static final double VAR_HEIGHT = TEXT_HEIGHT;
	
	public static final double VAR_X = DISPLAY_WIDTH -  2 * BUFFER - HISTORY_WIDTH - VAR_WIDTH;
	public static final double VAR_Y = TEXT_Y;
	
	public static final double HISTORY_X = DISPLAY_WIDTH - BUFFER - HISTORY_WIDTH;
	public static final double HISTORY_Y = 2 * BUFFER;
	
	public static final double VIEW_HEIGHT = DISPLAY_HEIGHT - TEXT_HEIGHT - 4 * BUFFER;
	public static final int VIEW_WIDTH = 500;
	
	public static final double COM_WIDTH = 150;
	public static final double COM_HEIGHT = HISTORY_HEIGHT - VAR_HEIGHT - BUFFER;
	
	public static final double COM_X = DISPLAY_WIDTH -  2 * BUFFER - HISTORY_WIDTH - VAR_WIDTH;
	public static final double COM_Y = 2 * BUFFER;
	
	public static final String RESOURCES_PATH = "resources.languages/";
	
	public static final String[] possibleLangauges = {"English", "Chinese", "French", "German",
			"Italian", "Portuguese", "Russian", "Spanish"};
}
