package interfaces.slogo.team04;


import java.io.File;
import java.util.List;
import java.util.Observer;

import backend.slogo.team04.Actor;
import backend.structures.RGBColor;
import javafx.beans.property.ListProperty;
import model.ModelLine;
import properties.ColorProperty;
import properties.ImageProperty;

public interface IView{

	public void addObserver(Observer o);
	public List<ModelLine> getLines();
	public ColorProperty getBackgroundColor();
	public ColorProperty getPenColor();
	public ImageProperty getImageProperty();
	public ListProperty<Actor> getActorProperty();
	public ListProperty<RGBColor> getColorListProperty();
	public void addActor();
	public void update();
}
