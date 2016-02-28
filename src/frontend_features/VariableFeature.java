package frontend_features;

import java.util.List;

import backend_slogo_team04.Variable;
import interfaces_slogo_team04.IVariable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import visual_states.GuiUserOption;

/**
 * Variable feature
 * @author Ryan St Pierre
 */
public class VariableFeature extends VPane {

	private IVariable model;
	private TableView<Variable> table;
	TableColumn<Variable, String> names;
	TableColumn<Variable, Number>  values;
	
	public VariableFeature(IVariable variables) {
	
		model = variables;
		table = new TableView<Variable>();
		add(new Label("Variables"));
		createTableView();
		addCSS("visual_resources/DefaultVariables.css");
		
	}

	private void createTableView() {
		table.setEditable(true);
		names = new TableColumn<Variable, String>();
		names.setText("Name");
		values = new TableColumn<Variable, Number>();
		values.setText("Value");
		CellValueAndCellFactories();
		setEditBehavior();
		table.setItems(model.getVariables());
		table.getColumns().add(names);
		table.getColumns().add(values);
		add(table);
		
	}

	private void setEditBehavior() {
		names.setOnEditCommit(event -> setName(event.getOldValue(), event.getNewValue()));
		values.setOnEditCommit(event -> setDoubleValue(event.getOldValue(), event.getNewValue()));
	}

	private void CellValueAndCellFactories() { 
		names.setCellValueFactory(e -> e.getValue().getName());
		values.setCellValueFactory(e -> e.getValue().getDoubleValue());
		names.setCellFactory(TextFieldTableCell.forTableColumn());
		values.setCellFactory(TextFieldTableCell.forTableColumn(new CellConverter(table)));
	}
	
	private void setDoubleValue(Number oldValue, Number newValue) {
		table.getSelectionModel().getSelectedItem().getDoubleValue().set(newValue.doubleValue());
	}

	private void setName(String oldValue, String edit) {
		table.getSelectionModel().getSelectedItem().getName().set(edit);
		System.out.println(table.getItems());
	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return null;
	}

}
