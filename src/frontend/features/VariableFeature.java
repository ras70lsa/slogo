package frontend.features;

import java.util.List;

import backend.slogo.team04.Variable;
import interfaces.slogo.team04.IVariable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldTableCell;
import visual.states.GuiUserOption;
import javafx.beans.property.ListProperty;

/**
 * Variable feature
 * @author Ryan St Pierre
 */
public class VariableFeature extends TitlePaneFeature {

	private IVariable model;
	private ListProperty<ListProperty<Variable>> stack;
	
	private TabPane tabs;
	
	public VariableFeature(IVariable variables) {
		setText(getString("VariableTitle"));
		this.stack = variables.getStack();
		model = variables;
		setUpTabs();
		setListeners();
	}

	private void setListeners() {
		stack.addListener((a,b,c) -> drawTabs());
	}

	private void setUpTabs() {
		tabs = new TabPane();
		drawTabs();
		setContent(tabs);
	}

	private void drawTabs() {
		tabs.getTabs().clear();
		int counter = 1;
		for(ListProperty<Variable> level: stack) {
			Tab tab = new Tab("Level " + counter);
			tabs.getTabs().add(tab);
			tab.setContent(createTableView(level));
			tabs.getSelectionModel().select(tab);
			counter++;
		}
	}

	private TableView<Variable> createTableView(ListProperty<Variable> level) {
		
		TableView<Variable> table = new TableView<>();
		TableColumn<Variable, String> names;
		TableColumn<Variable, Number>  values;
		table.setEditable(true);
		names = new TableColumn<Variable, String>();
		names.setText("Name");
		values = new TableColumn<Variable, Number>();
		values.setText("Value");
		CellValueAndCellFactories(names, values, table);
		setEditBehavior(names, values, table);
		table.setItems(level);
		table.getColumns().add(names);
		table.getColumns().add(values);		
		return table;
	}

	private void setEditBehavior(TableColumn<Variable, String> names, TableColumn<Variable, Number> values, 
			TableView<Variable> table) {
		names.setOnEditCommit(event -> setName(event.getOldValue(), event.getNewValue(), table));
		values.setOnEditCommit(event -> setDoubleValue(event.getOldValue(), event.getNewValue(), table));
	}

	private void CellValueAndCellFactories(TableColumn<Variable, String> names, TableColumn<Variable, Number> values, 
			TableView<Variable> table) { 
		names.setCellValueFactory(e -> e.getValue().getName());
		values.setCellValueFactory(e -> e.getValue().getDoubleValue());
		names.setCellFactory(TextFieldTableCell.forTableColumn());
		values.setCellFactory(TextFieldTableCell.forTableColumn(new CellConverter(table)));
	}
	
	private void setDoubleValue(Number oldValue, Number newValue, TableView<Variable> table) {
		table.getSelectionModel().getSelectedItem().getDoubleValue().set(newValue.doubleValue());
	}

	private void setName(String oldValue, String edit, TableView<Variable> table) {
		table.getSelectionModel().getSelectedItem().getName().set(edit);
	}

	

}
