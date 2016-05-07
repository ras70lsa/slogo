package model;

import java.util.ArrayList;
import java.util.List;
import constants.DisplayConstants;
import frontend.features.WrapAroundDrawLine;
import javafx.scene.shape.Line;

public class WrapDrawer extends ActorDrawer implements IActorDrawer {

    private WrapAroundDrawLine myWrapper;
    
    public WrapDrawer(){
        myWrapper = new WrapAroundDrawLine();
    }
    
    @Override
    public double makeXCorrection (double x) {
        x = x - DisplayConstants.VIEW_WIDTH/2;
        double xd = Math.abs(x)%(DisplayConstants.VIEW_WIDTH);
        if(x<0){
                return DisplayConstants.VIEW_WIDTH-xd;
        }
        else if(x>DisplayConstants.VIEW_WIDTH){
                return xd;
        }
        return x;
    }

    @Override
    public double makeYCorrection (double y) {
        y = -y - DisplayConstants.VIEW_HEIGHT/2;
        double yd = Math.abs(y)%(DisplayConstants.VIEW_HEIGHT);
        if(y<0){
                return DisplayConstants.VIEW_HEIGHT - yd;
        }
        else if(y>DisplayConstants.VIEW_HEIGHT){
                return yd;
        }
        return y;
    }

    @Override
    public List<Line> correctLines (List<ModelLine> lines) {
        List<Line> toReturn = new ArrayList<>();
        for(ModelLine line : lines){
            toReturn.addAll(correctModelLine(line));
        }
        return toReturn;
    }
    
    private List<Line> correctModelLine(ModelLine line){
        List<Line> toReturn = new ArrayList<>();
        myWrapper.draw(line);
        for(Line ln : myWrapper.getLines()){
            setLine(ln, line);
        }
        toReturn.addAll(myWrapper.getLines());
        myWrapper.clearLines();
        return toReturn;
    }



}
