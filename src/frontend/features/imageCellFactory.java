package frontend.features;

import javafx.scene.Node;
import javafx.scene.control.Label;
import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import backend.slogo.team04.Actor;
import exceptions.ImproperFileException;
import javafx.stage.FileChooser;

public class imageCellFactory {

	//TODO:rewrite listcell class
	Callback<ListView<Actor>, ListCell<Actor>> cellFactory = new Callback<ListView<Actor>, ListCell<Actor>>() {

			@Override
			public ListCell<Actor> call(ListView<Actor> p) {
				return new ListCell<Actor>() {
					private HBox hb = new HBox();
					private Label name;
					private ImageView iv;

					{
						name = new Label();
						iv = new ImageView();
						hb.getChildren().addAll(name, iv);

					}

					public void updateItem(Actor item, boolean isEmpty) {
						super.updateItem(item, isEmpty);
						if (!isEmpty()) {
							setImage(iv, item);
							setLabelText(name, item);
							setGraphic(hb);
						} else {
							setGraphic(null);
						}
					}
				};
			}

	};
		

	public void setImage(ImageView iv, Actor actor) {
		iv.setImage(actor.getImage());
		iv.setOnMouseClicked(e -> {
			try {
				actor.setImageProperty(ImageFileOrganizer.chooseImage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	public void setLabelText(Label label, Actor actor) {
		label.setText(Double.toString(actor.getID()));
	}


	public Callback<ListView<Actor>, ListCell<Actor>> getFactory() {
		return cellFactory;
	}
	
}




