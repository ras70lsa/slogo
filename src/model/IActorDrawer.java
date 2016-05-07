package model;

import java.util.List;
import javafx.scene.shape.Line;

public interface IActorDrawer {
    
     double makeXCorrection(double x);

     double makeYCorrection(double y);
     
     List<Line> correctLines(List<ModelLine> lines);
     
     
}
