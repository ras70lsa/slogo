package backend.slogo.team04;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;

public class Variable {

	private StringProperty name;
	private DoubleProperty value;
	
	public Variable(String n, double val) {
		name = new SimpleStringProperty(n);
		value = new SimpleDoubleProperty(val);
	}
	
	public StringProperty getName() {
		return name;
	}
	
	public DoubleProperty getDoubleValue() {
		return value;
	}
	
	public void setName(String str) {
		name.set(str);
	}
	
	public String toString() {
		return name.get() + " " + value.get();
	}

}
