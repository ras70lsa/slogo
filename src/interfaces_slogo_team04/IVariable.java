package interfaces_slogo_team04;

import backend_slogo_team04.Variable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;

public interface IVariable {

	public ListProperty<Variable> getVariables();
}
