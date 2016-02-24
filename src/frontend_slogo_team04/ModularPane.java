package frontend_slogo_team04;

import interfaces_slogo_team04.IState;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class ModularPane implements Module {
	
	public void update() {
		getState().getUserOptions().show();
	}
	
	public String toRGBCode(Color color ){
	    return String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
	}

}
