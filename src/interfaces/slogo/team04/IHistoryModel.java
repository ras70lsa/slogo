package interfaces.slogo.team04;

import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;

public interface IHistoryModel  {

	public ListProperty<String> getCommandList();
	public void add(String command);
	public StringProperty getLanguage();
	public void clear();
	
}
