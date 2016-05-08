package frontend.features;

import java.util.ArrayList;

import javafx.scene.shape.Line;
import model.ModelLine;

public interface IMyDrawer {

	ArrayList<Line> getLines();

	void draw(ModelLine line);

	public double makeXCorrection(double x);
	public double makeYCorrection(double y);

	void clearLines();


}
