package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class ActorDrawer implements IActorDrawer {
    
    private static final double VISIBLE = 1;
    
    protected void setLine(Line line, ModelLine mLine){
        line.getStrokeDashArray().addAll(mLine.getStyle());
        Color color = new Color(mLine.getColor().getRed(), mLine.getColor().getGreen(), mLine.getColor().getBlue(), VISIBLE);
        line.setStroke(color);
        line.setStrokeWidth(mLine.getWidth());
    }
    

    public List<Line> correctLines (List<ModelLine> lines) {
        List<Line> toReturn = new ArrayList<>();
        for(ModelLine line : lines){
            Line toAdd = new Line(makeXCorrection(line.getStartX()), makeYCorrection(line.getStartY())
                                  , makeXCorrection(line.getEndX()), makeYCorrection(line.getEndY()));
            setLine(toAdd, line);
            toReturn.add(toAdd);
        }
        return toReturn;
    }

}
