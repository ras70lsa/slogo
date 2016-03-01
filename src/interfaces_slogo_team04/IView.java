package interfaces_slogo_team04;


import java.io.File;
import java.util.List;
import java.util.Observer;

import backend_slogo_team04.Actor;
import model.ModelLine;
import properties.ColorProperty;
import properties.ImageProperty;

public interface IView{

	public void addObserver(Observer o);
	public List<ModelLine> getLines();
	public Actor getActor();
	public ColorProperty getBackgroundColor();
	public ColorProperty getPenColor();
	public ImageProperty getImageProperty();
}
