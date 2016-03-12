package interfaces.slogo.team04;

import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;

public interface IHistoryModel  {

	ListProperty<String> getCommandList();
	void add(String command);
	StringProperty getLanguage();
	void clear();

}
