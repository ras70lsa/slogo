package frontend_features;

import java.util.List;

import backend_slogo_team04.Variable;
import interfaces_slogo_team04.IVariable;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import visual_states.GuiUserOption;

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
		add(new Label("History"));
		createTableView();
		addCSS("visual_resources/DefaultVariables.css");
		
	}

	private void createTableView() {
		table.setEditable(true);
		TableColumn<Variable, String> names = new TableColumn<Variable, String>();
		names.setText("Name");
		TableColumn<Variable, Number> values = new TableColumn<Variable, Number>();
		values.setText("Val");
		names.setCellValueFactory(e -> e.getValue().getName());
		values.setCellValueFactory(e -> e.getValue().getDoubleValue());
		names.setCellFactory(TextFieldTableCell.forTableColumn());
		values.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		names.setOnEditCommit(e -> setName(e.getNewValue()));
		values.setOnEditCommit(e -> setDoubleValue(e.getNewValue()));
		table.setItems(model.getVariables());
		table.getColumns().add(names);
		table.getColumns().add(values);
		add(table);
		
	}

	private void setDoubleValue(Number newValue) {
		table.getSelectionModel().getSelectedItem().getDoubleValue().set(newValue.doubleValue());
	}

	private void setName(String edit) {
		table.getSelectionModel().getSelectedItem().getName().set(edit);
		System.out.println(table.getItems());
	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return null;
	}

}
