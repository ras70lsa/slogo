package frontend.features;

import java.util.List;

import backend.slogo.team04.Variable;
import interfaces.slogo.team04.IVariable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldTableCell;
import visual.states.GuiUserOption;

/**
 * Variable feature
 * @author Ryan St Pierre
 */
public class VariableFeature extends TitlePaneFeature {

	private IVariable model;
	private TableView<Variable> table;
	TableColumn<Variable, String> names;
	TableColumn<Variable, Number>  values;
	
	public VariableFeature(IVariable variables) {
		setText(getString("VariableTitle"));
		model = variables;
		table = new TableView<Variable>();
		createTableView();
		
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
		setContent(table);
		
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
	}

	

}
