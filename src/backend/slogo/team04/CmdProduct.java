//This entire file is part of my masterpiece.
//Jonathan Im

/**
 * We were able to leverage class inheritance to define a standard way of combining doubles with lambda functions, but in this class
 * we show how we can override this method to define new behaviors for child unlimited parameter nodes
 * 
 * This class also showcases the results of what the parse and execute methods look like as a result of our strategy design pattern recursive factory
 * node parser decision
 */
package backend.slogo.team04;

import java.util.function.BiFunction;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;


public class CmdProduct extends CommandTreeNode {
    protected final static String MY_KEY = "Product";
    private INonLinearCommand expOne, expTwo; 
    public CmdProduct(CommandTreeNode myParent) {
        super(myParent);
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
