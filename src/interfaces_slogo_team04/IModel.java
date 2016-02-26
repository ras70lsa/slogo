package interfaces_slogo_team04;

import javafx.beans.property.StringProperty;
import model.HistoryModel;

public interface IModel {

	public IHistoryModel getHistory();
	public IVariable getVariables();
	public String getLangauage();
	public StringProperty getLangauageProperty();

}
