package backend.slogo.team04;

import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class CmdWindow extends CommandTreeNode{
	protected final static String MY_KEY = "window";
	
	public CmdWindow(CommandTreeNode myParent) {
		super(myParent);
	}

	@Override
	public double executeCommand(ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter,
			ISlogoDebugObject debugMe) throws LogicException {
		return myController.window();
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