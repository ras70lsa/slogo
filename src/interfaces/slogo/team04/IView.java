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

public interface IView{

	void addObserver(Observer o);
	List<ModelLine> getLines();
	ColorProperty getBackgroundColor();
	ColorProperty getPenColor();
	ImageProperty getImageProperty();
	ListProperty<Actor> getActorProperty();
	ListProperty<RGBColor> getColorListProperty();
	void addActor(boolean visible);
	void update();
	void setPenStyle(String selectedItem);
	DoubleProperty getPenWidth();
	double setShape(int index);
	List<Actor> getStamps();
}
