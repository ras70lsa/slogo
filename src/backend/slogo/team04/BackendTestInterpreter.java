package backend.slogo.team04;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This class h
 * @author jonathanim
 *
 */
public class BackendTestInterpreter implements ISlogoInterpreterVariableScope{
    
    private Map<String, Double> slogoVariables;
    private Map<String, INonLinearCommand> userDefinedCommands;
    
    private Stack<Map<String,Double>> myVarStack;
    private int depth;
    
    
    
    public BackendTestInterpreter(){
        resetAllSimulationVariables();
        
        
    }
    

    @Override
    public void resetAllSimulationVariables(){
        resetGlobalVariables();
        resetUserDefinedCommands();

    }

    @Override
    public void resetGlobalVariables(){
        myVarStack = new Stack<Map<String,Double>>();
        incept();
        depth = 1;
    }

    @Override
    public void resetUserDefinedCommands(){
        this.userDefinedCommands = new HashMap<String,INonLinearCommand>();
    }
    

    @Override
    public void putFunction(String functionName, INonLinearCommand headNodeOfFunction){
        this.userDefinedCommands.put(functionName.toLowerCase(), headNodeOfFunction);
    }
    

    @Override
    public INonLinearCommand getFunction(String functionName){
        return this.userDefinedCommands.get(functionName.toLowerCase());
    }
    
    //need to make sure that lack of case sensitivity is properly implemented across the code base 
    @Override
    public double setVariableValue(String variable, Double value){
        //testing
        System.out.println(variable + " " + value);
        this.slogoVariables.put(variable.toLowerCase(), value);
        return this.slogoVariables.get(variable);
    }
    
// if the variable does not exist then we need to create it with the value of zero
    @Override
    public double getVariableValue(String variable){
        if(!this.slogoVariables.containsKey(variable.toLowerCase())){
            setVariableValue(variable.toLowerCase(), CommandTreeNode.DOUBLE_ZERO);
        }
        return this.slogoVariables.get(variable.toLowerCase());
    }


    @Override
    public void incept () {
        depth++;
        this.slogoVariables = new HashMap<String, Double>();
        myVarStack.push(this.slogoVariables);
        //TODO push a new stack of variables on it
        
    }


    @Override
    public void kick () {
        if(depth > 1){
            depth--;
            //TODO actually remove the latest stack value
            this.slogoVariables = myVarStack.pop();
        }
        
    }



}
