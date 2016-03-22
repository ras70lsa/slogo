package drawing;

import java.util.Collection;

import javafx.scene.Node;
import model.ModelLine;

public interface IDrawingBoundaryConditions {
	Collection<Node> draw(ModelLine line);
}
