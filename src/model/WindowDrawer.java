package model;

import constants.DisplayConstants;

public class WindowDrawer extends ActorDrawer implements IActorDrawer {

    @Override
    public double makeXCorrection (double x) {
        x = x + DisplayConstants.VIEW_WIDTH/2;
        return x;
    }

    @Override
    public double makeYCorrection (double y) {
        y = DisplayConstants.VIEW_HEIGHT/2 - y;
        return y;
    }


    

}
