package drawing;

import backend.slogo.team04.Actor;
import model.ModelLine;

public interface IDrawingAlgorithm {

	void draw(Actor actor);
	void draw(ModelLine line);
}
