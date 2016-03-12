package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdAskWith extends CommandTreeNode {
    protected final static String MY_KEY = "AskWith";
    private CommandTreeNode myIDIterator , myCondition, myCmdListOfCommands;
    private double currentTurtleID;



    public CmdAskWith (CommandTreeNode myParent) {
        super(myParent);
        currentTurtleID = Double.NaN;

    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        myInterpreter.incept();
        myController.pushCurrentActive();
        //set the actives here here



        double toReturn = CommandTreeNode.DOUBLE_ZERO;
        List<Boolean> turtleHasActed = new ArrayList<>();
        boolean someTurtleHasActed = true; //we are going to essentially iterate through list until it stop changing, because
        //        //the list of active turtles might grow shrink or change as a result of our operations

        boolean[] isTurtleActiveArray; // = myController.activeTurtles();
        while(someTurtleHasActed){
            isTurtleActiveArray = myController.activeTurtles();
            listFalseBooleanAdder(isTurtleActiveArray.length - turtleHasActed.size(), turtleHasActed);
            someTurtleHasActed = false;
            for(int i = 0; i < isTurtleActiveArray.length; i++){
                this.currentTurtleID = (double) i;
                double myCondVal = myCondition.executeCommand(myController, myInterpreter, debugMe); //checking if the test allows us to run
                if(myCondVal == CommandTreeNode.DOUBLE_ONE && !turtleHasActed.get(i) ){ // now this code will always iterate through everything untill nothing changes
                    someTurtleHasActed = true;
                    turtleHasActed.set(i, true);
                }

            }
        }
        
        myController.tell(getArrayOfTrueIndex(turtleHasActed));
        //need to set the array in the model to match
        toReturn = myIDIterator.executeCommand(myController, myInterpreter, debugMe);
        myInterpreter.kick();
        myController.popCurrentActive();
        return toReturn;
    }

    private int[] getArrayOfTrueIndex(List<Boolean> listToConvert){
        List<Integer> toReturn = new ArrayList<>();
        for(int i = 0; i < listToConvert.size(); i++){
            if(listToConvert.get(i)){
                toReturn.add(i);
            }

        }
        int[] rightTypeToReturn = new int[toReturn.size()];
        for(int i = 0; i < toReturn.size(); i++){
            rightTypeToReturn[i] = toReturn.get(i).intValue();
            
            
        }

            return rightTypeToReturn;
    }



    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        String myWord;
        myWord = myScanner.getNextWord();
        if(myScanner.checkIfStartOfList(myWord, myInterpreter)){
            // grab variables
            myWord = myScanner.getNextWord();
            myCondition = (CommandTreeNode) CommandFactory.recursiveSlogoFactoryNoListsControlledAdvance(myWord, myScanner, this, myInterpreter);
            myWord = myScanner.getNextWord();
            if(myScanner.checkIfEndOfList(myWord, myInterpreter)){
                myCmdListOfCommands = (CommandTreeNode) new CmdListOfCommands(this).parseString(myScanner, myInterpreter);
            }else{
                throw new UserInputException("Closing bracket not found");
            }
        }
        else{
            throw new UserInputException("Opening bracket not found for CmdTell");
        }        
        //fixing tree parent references to pass the proper current id value, 
        myCondition.setMyParent(this);
        CommandTreeNode myExecuteActiveTurtles = new CmdExecuteIfCurrentTurtleActive(this);
        myIDIterator = new CmdIDIterator(this, myExecuteActiveTurtles, myCmdListOfCommands);
        myExecuteActiveTurtles.setMyParent(myIDIterator);
        myCmdListOfCommands.setMyParent(myIDIterator);
        return this;
    }

    @Override
    public String parsableRepresentation () {
        return CmdAskWith.MY_KEY + CommandTreeNode.LEFT_BRACKET + myCondition.parsableRepresentation() + CommandTreeNode.RIGHT_BRACKET + myCmdListOfCommands.parsableRepresentation();
    }

    /**
     * The current turtle ID underneath the ID node
     */
    @Override
    protected double getCurrentActiveTurtleID(){
        return this.currentTurtleID;
    }


}
