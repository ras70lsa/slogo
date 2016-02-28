package frontend_features;

import java.text.NumberFormat;
import java.text.ParseException;

import backend_slogo_team04.Variable;
import javafx.scene.control.TableView;
import javafx.util.converter.NumberStringConverter;

public class CellConverter extends NumberStringConverter {

	TableView<Variable> view;
	
	public CellConverter(TableView<Variable> view) {
		super();
		this.view = view;
	}
	 @Override public Number fromString(String value) {
	        try {
	            // If the specified value is null or zero-length, return null
	            if (value == null) {
	                return null;
	            }

	            value = value.trim();

	            if (value.length() < 1) {
	                return null;
	            }

	            // Create and configure the parser to be used
	            NumberFormat parser = getNumberFormat();

	            // Perform the requested parsing
	            return parser.parse(value);
	        } catch (ParseException ex) {
	        	AlertMessage alert = new AlertMessage("Incorrect Input");
	        	alert.displayError();
	        	return view.getSelectionModel().getSelectedItem().getDoubleValue().getValue();
	        }
	    }
}
