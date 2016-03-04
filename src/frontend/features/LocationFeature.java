package frontend.features;

import backend.slogo.team04.Actor;
import backend.slogo.team04.Variable;
import interfaces.slogo.team04.IView;
import javafx.beans.property.ListProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
		xLoc.setText("x");
		table.setItems(actors);
		xLoc.setCellValueFactory(e -> e.getValue().getXProperty());
		table.getColumns().add(xLoc);
		setContent(table);
		
	}
}
