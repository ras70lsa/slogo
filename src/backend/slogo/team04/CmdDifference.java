package backend.slogo.team04;

import java.util.function.BiFunction;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdDifference extends CommandTreeNode {
    protected final static String MY_KEY = "Difference";

    private INonLinearCommand inputOne, inputTwo; // the two nodes that we need to grab



    public CmdDifference(CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter) throws LogicException {
        double valOne, valTwo;
        valOne = inputOne.executeCommand(myController, myInterpreter);
        valTwo = inputTwo.executeCommand(myController, myInterpreter);
        return valOne - valTwo;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        inputOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        inputTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }
    

    @Override
    protected BiFunction<Double, Double, Double> getMyUnlimitedParameterBehavior(){
        return (x,y) ->  x - y;
    }

    @Override
    public String parsableRepresentation () {
        return CmdDifference.MY_KEY + CommandTreeNode.SPACE + inputOne.parsableRepresentation() + CommandTreeNode.SPACE + inputTwo.parsableRepresentation();
    }

}
