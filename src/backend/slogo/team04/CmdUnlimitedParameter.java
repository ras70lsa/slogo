package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdUnlimitedParameter extends CommandTreeNode {
    protected final static String MY_KEY = "(";
    protected final static String END_STRING = ")";
    private List<INonLinearCommand> myParamList;



    public CmdUnlimitedParameter (CommandTreeNode myParent) {
        super(myParent);
        myParamList = new ArrayList<INonLinearCommand>();
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        if(myParamList.size() > 0){
            BiFunction<Double, Double, Double> myFunc = this.getMyParent().getMyUnlimitedParameterBehavior();
            double runningTotal = myParamList.get(0).executeCommand(myController, myInterpreter, debugMe);
            for(int i = 1; i < myParamList.size(); i++){
                runningTotal = myFunc.apply(runningTotal, myParamList.get(i).executeCommand(myController, myInterpreter, debugMe));
            }
            return runningTotal;
        }else{
            return CommandTreeNode.DOUBLE_ZERO;
        }

    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        //we need stop parsing when we have 
        String myNextWord = myScanner.getNextWord();
        while(!myScanner.commentSafeStringComparison(myNextWord, myInterpreter, s -> s.equals(END_STRING))){
            myParamList.add(CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myNextWord, myScanner, this, myInterpreter));
            myNextWord = myScanner.getNextWord();
        }
        return this;
    }

    @Override
    public String parsableRepresentation () {
        String toReturn = CommandTreeNode.LEFT_PAREN;
        toReturn = appendParsableRepresentationWithSpaces(toReturn, myParamList);
        return toReturn + CommandTreeNode.RIGHT_PAREN;
    }

}
