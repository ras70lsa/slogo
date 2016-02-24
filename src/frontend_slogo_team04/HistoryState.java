package frontend_slogo_team04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import backend_slogo_team04.Model;

/**
 * Keeps track of the programs back end history
 * @author Ryan St Pierre
 */
public class HistoryState extends State {

	private ColorProperty color;
	private ListProperty<Action> info;
	
	public HistoryState() {
		
		color = new ColorProperty();
		ObservableList<Action> list = FXCollections.observableArrayList();
		info = new SimpleListProperty<Action>(list);
		
	}
	
	protected Collection<Node> getStageNodes() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(getFactory().get(color, "string"));
		return nodes;
	}
	
	public ColorProperty getColorProperty() {
		return color;
	}
	
	public ListProperty<Action> getListProperty() {
		return info;
	}
	
	public void addToStack() {
		//System.out.println(info.toString());
		Model m = new Model();
		info.add(new Action(m, "Testing", null, null));
	}

}
