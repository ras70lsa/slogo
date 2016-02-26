package frontend_slogo_team04;

import backend_slogo_team04.Variable;
import interfaces_slogo_team04.IVariable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Variable feature
 * @author Ryan St Pierre
 */
public class VariableFeature extends ScrollablePane {

	private IVariable model;
	private TableView<Variable> table;
	
	public VariableFeature(IVariable variables) {
	
		model = variables;
		table = new TableView<Variable>();
		createTableView();
		addCSS("visual_resources/DefaultVariables.css");
		
	}

	private void createTableView() {
		table.setEditable(true);
		TableColumn<Variable, String> names = new TableColumn<Variable, String>();
		TableColumn<Variable, Number> values = new TableColumn<Variable, Number>();
		names.setCellValueFactory(e -> e.getValue().getName());
		values.setCellValueFactory(e -> e.getValue().getDoubleValue());
		table.setItems(model.getVariables());
		table.getColumns().add(names);
		table.getColumns().add(values);
		add(table);
		
	}
	
	public void update() { 
		super.update();
	}

	public State getState() {
		return null;

	}

}
