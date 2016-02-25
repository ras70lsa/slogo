package frontend_slogo_team04;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VisualTurtle extends ImageView{
	private boolean isShowing = true;
	private DoubleProperty myHeading = new SimpleDoubleProperty();
	
	public VisualTurtle(Image i){
		setImage(i);
		myHeading.setValue(0);;
	}
	
	public void hideTurtle(){
		isShowing = false;
		setVisible(isShowing);
	}
	
	public void showTurtle(){
		isShowing = true;
		setVisible(isShowing);
	}
	
	public boolean isShowing(){
		return isShowing;
	}
	
	public void setHeading(double degress){
		myHeading.setValue(degress);
	}
}
