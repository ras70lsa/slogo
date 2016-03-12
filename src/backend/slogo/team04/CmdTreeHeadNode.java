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
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        myInterpreter.kickAllButLowest();
        for(INonLinearCommand child : myChildren){
            child.executeCommand(myController, myInterpreter, debugMe);
        }
        return 0; //this is not a defined language command
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        myInterpreter.kickAllButLowest();
        while(myScanner.hasNext()){
            myChildren.add(CommandFactory.topLevelCommandFactory( myScanner.getNextWord(),  myScanner, this ,  myInterpreter));
        }
        return this;
    }
    
    /**
     * The tree will thus have NaN as the current ID, unless the IDIterator nodes are placed in the proper locations,
     * on top of any type of node that requires operating on any active turtle
     */
    @Override
    protected double getCurrentActiveTurtleID(){
        return Double.NaN;
    }

    @Override
    public String parsableRepresentation () {
        return appendParsableRepresentationWithSpaces(CommandTreeNode.EMPTY_STRING, myChildren);
        
    }

}
