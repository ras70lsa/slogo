//This entire file is part of my masterpiece 
//Ryan St.Pierre

/**
 * My masterpiece includes the changing and creation of 4 classes/interfaces that work together to better handle drawing.  
 * Although this may seem like a lot, most of the work included creating interfaces and moving (rather than writing new) code 
 * to better create a design that supports flexibility and extensibility. 
 * 
 * The intended purpose of this class is to manage the modulated view aspect that contains the turtles.  This entails ensuring
 * the view represents the proper information in the model, such as setting the background color, and visually representing 
 * the layout of turtles and stamps.  Previously this class contained the algorithm for drawing the actors and lines on the 
 * screen.  The major intention of my refactor was to use the Strategy design pattern to move this drawing responsibility out
 * of this class.  Doing such makes this class closed, allowing it to be compatible with different drawing features and techniques.
 * 
 * I moved all of the drawing logic from this class into the InstantaneousDrawing.java class.  This class implements a 
 * generic interface called IDrawingAlgorithm.  This ActorView class can use any IDrawingAlgorithm to render its screen.
 * 
 * This design better observes Liskov's Substitution Principle (LSP).  Under this construct new drawing features, such as
 * animation, or a faster algorithm for a pre-existing drawing feature could be supported without changed this ActorView 
 * class. To implement animation a new class implement the IDrawingAlgorithm, interface would be created and  substituted 
 * into the ActorView class as desired. This refactored design construct better supports client flexibility, not making 
 * assumptions about which future design techniques may be preferable for this class to use. 
 * 
 * In addition I changed the name of this class from View.java to ActorView.java.  I did this to be more specific. 
 * "View" is to generic as it is not the responsibility of this class to manage the whole view, but rather one sub-view
 * of the project.
 * 
 */
package frontend.features;

/**
 * The intended purpose of this class is to control the sub-view that includes the actors, lines, stamps, etc. 
 * @original authors:  Sophie Guo, Alexander Tseng 
 * @refactored and used in masterpiece:  Ryan St.Pierre
 */

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

	/**defines the relationship between the ActorView and ActorModel*/
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

	/**
	 * The model tells the ActorView when to update (redraw) when desired.  
	 * The ActorView redraws its components eliciting the help of an IDrawingAlgorithm
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		clear();
		Collection<Actor> actorsToDraw = model.getActors();
		actorsToDraw.stream().forEach((a) -> drawer.draw(a));
	}
	
	/**
	 * Uses a factory class to abstractly create a list of Nodes 
	 * Each Node is a specific JavaFX construct that allows the user to alter a property of this class
	 * This method is used to get the Nodes necessary for the user to alter background color, pen color, and actor image
	 * from the Display options 
	 * @return List<Node>
	 */
	public List<Node> getReleventProperties() {
		GuiUserOption factory = new GuiUserOption();
		List<Node> list = new ArrayList<>();
		//The getString(String str) method is defined in parent class, using ResourceBundle
		list.add(factory.get(model.getImageProperty(), getString("ChooseImage")));
		list.add(factory.get(model.getBackgroundColor(), model.getColorListProperty(), getString("BackgroundColor")));
		list.add(factory.get(model.getPenColor(), model.getColorListProperty(), getString("PenColor")));
		return list;
	}


}

