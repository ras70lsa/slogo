package interfaces_slogo_team04;

import javafx.beans.property.ListProperty;

public interface ICommands {

	public ListProperty<String> getCommands();
	public void add(String input);

}
