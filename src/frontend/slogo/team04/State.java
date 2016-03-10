package frontend.slogo.team04;
import visual.states.GuiUserOption;

/**
 * This interface groups values that can be altered by the user or through code based on functionally who needs access
 * @author Ryan St Pierre
 *
 */
public abstract class State {

	protected GuiUserOption getFactory() {
		return new GuiUserOption();
	}
	/**
	 *Possibly to delete
	 */
}
