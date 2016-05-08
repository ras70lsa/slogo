package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class CmdFence extends CommandTreeNode{
	protected final static String MY_KEY = "fence";
	
	public CmdFence(CommandTreeNode myParent) {
		super(myParent);
	}

	@Override
	public double executeCommand(ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter,
			ISlogoDebugObject debugMe) throws LogicException {
		return myController.fence();
	}

	@Override
	public INonLinearCommand parseString(SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter)
			throws UserInputException {
		return this;
	}

	@Override
	public String parsableRepresentation() {
		return MY_KEY + CommandTreeNode.SPACE;
	}

}
