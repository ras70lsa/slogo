package frontend.features;

import backend.slogo.team04.Actor;
import javafx.beans.property.ListProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class turtleImageDisplay extends TitlePaneFeature{
	private ListProperty<Actor> allActors;
	private ListView<Actor> actorImages;
	
	public turtleImageDisplay(ListProperty<Actor> allActors){
		actorImages = new ListView<Actor>();
		this.allActors = allActors;
		init();
	}
	
	public void init(){
		imageCellFactory factory = new imageCellFactory();
		actorImages.setItems(allActors);
		actorImages.setCellFactory(factory.getFactory());
		
	}
}
