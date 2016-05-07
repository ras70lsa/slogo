package model;

import constants.DisplayConstants;

public class FenceDrawer extends ActorDrawer implements IActorDrawer {

    @Override
    public double makeXCorrection (double x) {
        x = x + DisplayConstants.VIEW_WIDTH/2;
        if(x<0){
                return 0;
        }
        else if(x>DisplayConstants.VIEW_WIDTH){
                return DisplayConstants.VIEW_WIDTH;
        }
        return x;
    }

    @Override
    public double makeYCorrection (double y) {
        y = DisplayConstants.VIEW_HEIGHT/2 - y;
        if(y < 0){
                return 0;
        }
        else if(y > DisplayConstants.VIEW_HEIGHT){
                return DisplayConstants.VIEW_HEIGHT;
        }
        return y;
    }



    

}
