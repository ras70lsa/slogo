package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.adapter.JavaBeanBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import properties.*;


public class TestingState extends State {

	ColorProperty color;
	BooleanProperty tf;
	ImageProperty image;
	String backgroundColor;
	
	public TestingState() {
		
		color = new ColorProperty();
		tf = new SimpleBooleanProperty(false);
		image = new ImageProperty();
	}
	
	public void setBackgroundColor(String color){
		this.backgroundColor = color;
	}
	
	public String getBackgroundColor(){
		return backgroundColor;
	}

	public BooleanProperty getBooleanProperty() {
		return tf;
	}
	
	public ImageProperty getImageProperty() {
		return image;
	}
	
	public ColorProperty getColorProperty() {
		return color;
	}
	
	protected Collection<Node> getStageNodes() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(getFactory().get(color, "string"));
		nodes.add(getFactory().get(tf, "string"));
		nodes.add(getFactory().get(image, "String"));
		return nodes;
		
	}

}
