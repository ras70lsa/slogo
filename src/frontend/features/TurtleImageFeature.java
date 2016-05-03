package frontend.features;

import backend.slogo.team04.Actor;
import constants.DisplayConstants;
import interfaces.slogo.team04.IView;
import javafx.scene.Node;
import javafx.scene.control.ListView;

/**
 * This is the class of the added front-end feature
 * @author RyanStPierre
 *
 */
public class TurtleImageFeature extends TitlePaneFeature {

    private ListView<Actor> myActors; 
    private IView myView;
    
    public TurtleImageFeature (IView iView) {
        myView = iView;
        init();
        setContent(getView());
    }

    private void init () {
        setText(getString("Images"));
        myActors = new ListView<>(myView.getActorProperty());
        myActors.setCellFactory(e -> new ImageCell(DisplayConstants.ACCORDION_WIDTH));
    }

    private Node getView () {
        return myActors;
    }

}
