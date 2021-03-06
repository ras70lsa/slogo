package backend.slogo.team04;

import java.util.function.BiFunction;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdProduct extends CommandTreeNode {
    protected final static String MY_KEY = "Product";

    private INonLinearCommand expOne, expTwo; // the two nodes that we need to grab



    public CmdProduct(CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException {
        ifDebugPauseExecution(debugMe);
        double valOne, valTwo;
        valOne = expOne.executeCommand(myController, myInterpreter, debugMe);
        valTwo = expTwo.executeCommand(myController, myInterpreter, debugMe);
        return valOne * valTwo;
    }




    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException {
        expOne = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        expTwo = CommandFactory.recursiveSlogoFactoryNoListsAllowed(myScanner, this, myInterpreter);
        return this;
    }
    
    @Override
    protected BiFunction<Double, Double, Double> getMyUnlimitedParameterBehavior(){
        return (x,y) ->  x * y;
    }

    @Override
    public String parsableRepresentation () {
        return CmdProduct.MY_KEY + CommandTreeNode.SPACE + expOne.parsableRepresentation() + CommandTreeNode.SPACE + expTwo.parsableRepresentation();
    }

}
