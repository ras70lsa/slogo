package backend_slogo_team04;

import java.util.Map;

/**
 * Encapsulates the actions taken (information needed when parsing a single block of text from the front end) so that we can track
 * what we have done, so that we can implement redo-undo functionality later
 * @author jonathanim
 * @author Ryan St Pierre
 *
 */
public class Action {
	
	private INonLinearCommand head;
	private int nodesDrawn;
	private double turtleDirection;
	private Map<String, Integer> variables;
	
	public Action(INonLinearCommand head, int drawn, double direction, Map<String, Integer> variables) {
		this.head=head;
		nodesDrawn = drawn;
		turtleDirection = direction;
		this.variables = variables;
	}
	
	public INonLinearCommand getHead() {
		return head;
	}
	
	public int getNodesCount() {
		return nodesDrawn;
	}
	
	public double getTurtleDirection() {
		return turtleDirection;
	}
	
	public Map<String, Integer> getVariables() {
		return variables;
	}

}
