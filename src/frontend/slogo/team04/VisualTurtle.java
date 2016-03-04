package frontend.slogo.team04;


import constants.DisplayConstants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VisualTurtle extends ImageView{
	private boolean isShowing = true;
	private DoubleProperty myHeading = new SimpleDoubleProperty();
	
	public VisualTurtle(Image i){
		super(i);
		setImage(i);
		this.setFitHeight(DisplayConstants.TURTLE_HEIGHT);
		this.setFitWidth(DisplayConstants.TURTLE_WIDTH);
		this.setPreserveRatio(true);
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
	
	public double getHeading(){
		return myHeading.getValue();
	}
}
