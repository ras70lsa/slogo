package visual.states;

import frontend.slogo.team04.State;
import properties.ImageProperty;

public class ViewUIState extends State {

	ImageProperty image;
	
	public ViewUIState() {
		image = new ImageProperty();
	}
	
	public ImageProperty getImageProperty() {
		return image;
	}
	
}
