package frontend_slogo_team04;

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
