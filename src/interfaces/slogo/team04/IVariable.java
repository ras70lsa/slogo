package interfaces.slogo.team04;

import backend.slogo.team04.Variable;
import javafx.beans.property.ListProperty;

public interface IVariable {

	ListProperty<Variable> getVariables();

	void clearVariables();
	
	ListProperty<ListProperty<Variable>> getStack();

}
