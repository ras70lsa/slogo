package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import interfaces_slogo_team04.State;
import javafx.beans.binding.ListBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import properties.ColorProperty;
import backend_slogo_team04.Action;

public class HistoryState extends State {

	private ColorProperty color;
	private ListProperty<Double> info;
	
	public HistoryState() {
		
		color = new ColorProperty();
		ObservableList<Double> observableList = FXCollections.observableArrayList();
		info = new SimpleListProperty<Double>(observableList);
		
	}
	
	protected Collection<Node> getStageNodes() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(getFactory().get(color, "string"));
		return nodes;
	}
	
	public ColorProperty getColorProperty() {
		return color;
	}
	
	public ListProperty<Double> getListProperty() {
		return info;
	}
	
	public void addToStack() {
		System.out.println(info.toString());
		//info.add(5.0);
	}

}
