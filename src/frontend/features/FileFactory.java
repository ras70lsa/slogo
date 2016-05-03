package frontend.features;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import exceptions.ImproperFileException;
import exceptions.PaletteException;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Returns a file choice with proper exceptions
 * @author RyanStPierre
 *
 */
public class FileFactory {

    private ResourceBundle myBundle = ResourceBundle.getBundle("resources/extensions");

    public Image getImage () throws ImproperFileException, PaletteException {
        File file = getFile();
        if (file == null) {
            throw new PaletteException();
        }
        try {
            String url = file.toURI().toURL().toString();
            return new Image(url);
        }
        catch (MalformedURLException e) {
            throw new ImproperFileException();
        }
    }

    private File getFile () {
        FileChooser chooser = new FileChooser();
        addFilters(chooser);
        return chooser.showOpenDialog(null);
    }

    private void addFilters (FileChooser chooser) {
        String filterDescription = myBundle.getString("Description");
        myBundle.keySet().stream().forEach(key -> chooser.getExtensionFilters()
                .add(new ExtensionFilter(filterDescription, myBundle.getString(key))));
    }
}
