package interfaces.slogo.team04;

import backend.slogo.team04.INonLinearCommand;
import javafx.beans.property.ListProperty;

public interface ICommands {

	public ListProperty<String> getCommands();
	public ListProperty<INonLinearCommand> getCommandNodes();

}
