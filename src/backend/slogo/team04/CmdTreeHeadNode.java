package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;



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
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        for(INonLinearCommand child : myChildren){
            child.executeCommand(myController, myInterpreter);
        }
        return 0; //this is not a defined language command
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        while(myScanner.hasNext()){
            //myChildren.add(CommandTreeNode.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter));
            myChildren.add(topLevelCommandFactory( myScanner.next(),  myScanner, this ,  myInterpreter));
        }
        return this;
    }

}
