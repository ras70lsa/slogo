package frontend.features;

import backend.slogo.team04.Actor;
import interfaces.slogo.team04.IView;
import javafx.beans.binding.Bindings;
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
		setContent(table);
		
	}

	private void createTableView() {
		table = new TableView<>();
		table.setEditable(false);
		table.setItems(actors);
		createColumns();
	}

	private void createColumns() {

		//Needs to be refactored
		TableColumn<Actor, String> ID = new TableColumn<>(getString("ID"));
		TableColumn<Actor, String> xLoc = new TableColumn<>(getString("x"));
		TableColumn<Actor, String> yLoc = new TableColumn<>(getString("y"));
		TableColumn<Actor, Number> heading = new TableColumn<>(getString("Heading"));
		TableColumn<Actor, Boolean> showing = new TableColumn<>(getString("Showing"));
		TableColumn<Actor, Boolean> isPenDown = new TableColumn<>(getString("PenTable"));
		TableColumn<Actor, Boolean> active = new TableColumn<>(getString("ActiveTable"));
		ID.setCellValueFactory(e -> Bindings.format("%.0f", e.getValue().getIDProperty()));
		xLoc.setCellValueFactory(e -> Bindings.format("%.2f", e.getValue().getXProperty()));
		yLoc.setCellValueFactory(e -> Bindings.format("%.2f", e.getValue().getYProperty()));
		heading.setCellValueFactory(e ->e.getValue().getHeadingProperty());
		showing.setCellValueFactory(e -> e.getValue().getVisibleProperty());
		isPenDown.setCellValueFactory(e -> e.getValue().getPenDownProperty());
		active.setCellValueFactory(e -> e.getValue().getActive());
		table.getColumns().add(ID);
		table.getColumns().addAll(active, xLoc, yLoc, heading, showing, isPenDown);
		
	}
}
