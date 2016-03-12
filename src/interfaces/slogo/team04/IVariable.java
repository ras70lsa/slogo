package interfaces.slogo.team04;

import backend.slogo.team04.Variable;
import javafx.beans.property.ListProperty;

/**
 * Defines the relationship between the variables model and the feature
 * @author RyanStPierre
 *
 */
public interface IVariable {

	ListProperty<Variable> getVariables();

	void clearVariables();
	
	ListProperty<ListProperty<Variable>> getStack();

}
