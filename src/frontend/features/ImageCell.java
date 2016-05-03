package frontend.features;

import backend.slogo.team04.Actor;
import exceptions.ImproperFileException;
import exceptions.PaletteException;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 * Controls the changing of image for the cell 
 * @author RyanStPierre
 *
 */
public class ImageCell extends ListCell<Actor> {

    private double myWidth;
    private FileFactory myFileChooser = new FileFactory();
    
    public ImageCell (double width) {
        myWidth = width;
    }
    @Override
    protected void updateItem (Actor item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        }
        else {
            setGraphic(createImageView(item));
        }
    }

    private Node createImageView (Actor item) {
        ImageView view = new ImageView(item.getImage());
        view.setFitWidth(myWidth);
        this.setOnMouseClicked(e -> changeImage(item));
        return view;
    }
    
    private void changeImage (Actor item) {
        try {
            item.setImageProperty(myFileChooser.getImage());
            updateItem(item, false);
        } catch (PaletteException e){
            //No image selected
        } catch (ImproperFileException e) {
            //bad file selected
            AlertMessage error = new AlertMessage(e.getMessage());
            error.displayError();
        }
    }
}
