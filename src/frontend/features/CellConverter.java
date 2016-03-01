package frontend.features;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;

import backend.slogo.team04.Variable;
import constants.DisplayConstants;
import constants.ResourceConstants;
import javafx.scene.control.TableView;
import javafx.util.converter.NumberStringConverter;

public class CellConverter extends NumberStringConverter {

	//Table needed to return the old value if an excpetion is thrown
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
	        	ResourceBundle myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH +
	        			ResourceConstants.ENGLISH);
	        	AlertMessage alert = new AlertMessage(myBundle.getString("InputError"));
	        	alert.displayError();
	        	return view.getSelectionModel().getSelectedItem().getDoubleValue().getValue();
	        }
	    }
}
