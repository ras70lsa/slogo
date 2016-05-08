package frontend.features;

import java.io.File;
import java.net.MalformedURLException;

import backend.slogo.team04.Actor;
import exceptions.ImproperFileException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public final class ImageFileOrganizer {

	public static Image chooseImage() throws MalformedURLException, ImproperFileException{
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(null);
		try {
			return new Image(file.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			throw new ImproperFileException();
		}
	}
}
