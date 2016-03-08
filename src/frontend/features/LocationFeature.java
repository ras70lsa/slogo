package frontend.features;

import backend.slogo.team04.Actor;
import interfaces.slogo.team04.IView;
import javafx.beans.property.ListProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class LocationFeature extends TitlePaneFeature {

	private ListProperty<Actor> actors;
	private TableView<Actor> table;
	
	public LocationFeature(IView info) {
		setText(getString("ActorSpecifics"));
		actors = info.getActorProperty();
		createTableView();
		
	}

	private void createTableView() {
		table = new TableView<>();
		table.setEditable(false);
		TableColumn<Actor, Number> xLoc = new TableColumn<Actor, Number>();
		TableColumn<Actor, Number> yLoc = new TableColumn<Actor, Number>();
		TableColumn<Actor, Number> heading = new TableColumn<Actor, Number>();
		TableColumn<Actor, Boolean> showing = new TableColumn<Actor, Boolean>();
		TableColumn<Actor, Boolean> isPenDown = new TableColumn<Actor, Boolean>();
		xLoc.setText("Virtual x");
		yLoc.setText("Virtual y");
		heading.setText("Heading");
		showing.setText("Showing");
		isPenDown.setText("IsPenDown?");
		table.setItems(actors);
		xLoc.setCellValueFactory(e -> e.getValue().getXProperty());
		yLoc.setCellValueFactory(e -> e.getValue().getYProperty());
		heading.setCellValueFactory(e ->e.getValue().getHeadingProperty());
		showing.setCellValueFactory(e -> e.getValue().getVisibileProperty());
		isPenDown.setCellValueFactory(e -> e.getValue().getPenDownProperty());
		table.getColumns().addAll(xLoc, yLoc, heading, showing, isPenDown);
		setContent(table);
		
	}
}
