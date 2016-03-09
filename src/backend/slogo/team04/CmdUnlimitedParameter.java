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
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException {
        if(myParamList.size() > 0){
            BiFunction<Double, Double, Double> myFunc = this.getMyParent().getMyUnlimitedParameterBehavior();
            double runningTotal = myParamList.get(0).executeCommand(myController, myInterpreter);
            for(int i = 1; i < myParamList.size(); i++){
                runningTotal = myFunc.apply(runningTotal, myParamList.get(i).executeCommand(myController, myInterpreter));
            }
            return runningTotal;
        }else{
            return CommandTreeNode.DOUBLE_ZERO;
        }

    }
    
    


    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        //we need stop parsing when we have 
        String myNextWord = myScanner.getNextWord();
        while(!myScanner.commentSafeStringComparison(myNextWord, myInterpreter, s -> s == END_STRING)){
            myParamList.add(CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myNextWord, myScanner, this, myInterpreter));
            myNextWord = myScanner.getNextWord();
        }
        return this;
    }

}
