//This entire file is part of my masterpiece 
//Ryan St.Pierre

/**
 * This interface defines what a drawing algorithm entails.  These methods must be implemented in order to be used 
 * meaningfully by the ActorView class.
 */

package drawing;

import backend.slogo.team04.Actor;
import model.ModelLine;

public interface IDrawingAlgorithm {

	void draw(Actor actor);
	void draw(ModelLine line);
}
