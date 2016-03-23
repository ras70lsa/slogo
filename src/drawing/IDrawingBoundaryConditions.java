//This entire file is part of my masterpiece 
//Ryan St.Pierre

/**
 * This interface defines a boundary condition handler.  Possible implementations include:
 * WrapAround: where the actor goes off the screen and comes back on in a toroidal fashion
 * Drop-off: where actors and lines off the screen are simply not drawn
 * Zooming: where the sub-view is scaled to show the actor when the actor goes out of view
 * 
 * I created this interface to allow greater extensibility to the IDrawingAlgorithm.  With this interface is place
 * a new way of boundary condition can be written and substituted into a given IDrawingAlgorithm.  This keeps the 
 * rest of drawing algorithm closed when dealing with a feature changing boundary condition handling. 
 * 
 * Used implementations: WrapAroundDrawLine
 */

package drawing;

import java.util.Collection;

import javafx.scene.Node;
import model.ModelLine;

public interface IDrawingBoundaryConditions {
	
	/**
	 * Takes in a ModelLine.  Based on the ModelLine's coordinates with respect to the screen and the decided implementation
	 * a collection of Nodes to draw are returned 
	 * 
	 * @param ModelLine line
	 * @return Collection<Node>
	 */
	Collection<Node> draw(ModelLine line);
}
