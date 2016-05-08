package interfaces.slogo.team04;


import java.util.List;
import java.util.Observer;

import backend.slogo.team04.Actor;
import backend.structures.RGBColor;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import model.ModelLine;
import properties.ColorProperty;
import properties.ImageProperty;

/**
 * Defines the relationship between the rendering of the turtle drawing and the view model
 *
 */
public interface IView{

	/**
	 * Allows the view to add itself to the model to be updated when necessary
	 * In other word this allows the view to subscribe 
	 * @param Observer
	 */
	void addObserver(Observer o);
	
	/**
	 * Returns the lines to be rendered
	 * @return List<ModelLine>
	 */
	List<ModelLine> getLines();
	
	/**
	 * Returns the current background color
	 * @return ColorProperty
	 */
	ColorProperty getBackgroundColor();
	
	/**
	 * Returns the current pen color
	 * @return ColorProperty
	 */
	ColorProperty getPenColor();
	
	/**
	 * Returns the current Actor Image
	 * @return ImageProperty
	 */
	ImageProperty getImageProperty();
	ListProperty<Actor> getActorProperty();
	ListProperty<RGBColor> getColorListProperty();
	void addActor(boolean visible);
	void update();
	void setPenStyle(String selectedItem);
	DoubleProperty getPenWidth();
	double setShape(int index);
	List<Actor> getStamps();
	ImageProperty getCurrentImage();
	double getFunction();
}
