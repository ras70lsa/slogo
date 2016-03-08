package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;



/**
 * This node will constantly pull things off of the scanner until it is empty
 * @author jonathanim
 *
 */
public class CmdTreeHeadNode extends CommandTreeNode {
    List<INonLinearCommand> myChildren;



    public CmdTreeHeadNode (CommandTreeNode myParent) {
        super(myParent);
        myChildren = new ArrayList<INonLinearCommand>();
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        for(INonLinearCommand child : myChildren){
            child.executeCommand(myController, myInterpreter);
        }
        return 0; //this is not a defined language command
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        while(myScanner.hasNext()){
            myChildren.add(CommandFactory.topLevelCommandFactory( myScanner.getNextWord(),  myScanner, this ,  myInterpreter));
        }
        return this;
    }

}
