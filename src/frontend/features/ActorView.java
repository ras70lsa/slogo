package frontend.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import backend.slogo.team04.Actor;
import constants.CSSPathConstants;
import drawing.IDrawingAlgorithm;
import drawing.InstantaneousDrawing;
import interfaces.slogo.team04.IActorModel;
import javafx.scene.Node;
import visual.states.GuiUserOption;

public class ActorView extends StaticPane implements Observer {

	
	private IActorModel model;
	private IDrawingAlgorithm drawer;

	public ActorView(IActorModel model) {
		this.model = model;
		model.addObserver(this);
		setUp();
	}

	private void setUp() {
		addCSS(CSSPathConstants.DEFAULT_VIEW);
		addSilentListeners();
		drawer = new InstantaneousDrawing(getPane());
	}

	private void addSilentListeners() {
		model.getBackgroundColor().addListener((a,b,c) -> updateColor(c));
	}

	@Override
	public void update(Observable o, Object arg) {
		
		clear();
		Collection<Actor> actorsToDraw = model.getActors();
		actorsToDraw.stream().forEach((a) -> drawer.draw(a));
	}
	
	public List<Node> getReleventProperties() {
		GuiUserOption factory = new GuiUserOption();
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(model.getImageProperty(), getString("ChooseImage")));
		list.add(factory.get(model.getBackgroundColor(), model.getColorListProperty(), getString("BackgroundColor")));
		list.add(factory.get(model.getPenColor(), model.getColorListProperty(), getString("PenColor")));
		return list;
	}


}

