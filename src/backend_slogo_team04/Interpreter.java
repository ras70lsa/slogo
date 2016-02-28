package backend_slogo_team04;

import java.util.HashMap;
import java.util.Map;
/**
 * This class h
 * @author jonathanim
 *
 */
public class Interpreter implements ISlogoInterpreter {
    

    @Override
    public void interpretCommandTree(INonLinearCommand headNode){
        
        return;
    }
    
    
    
    
    private Map<String, Double> slogoVariables;
    private Map<String, CommandTreeNode> userDefinedCommands;
    
    
    
    public Interpreter(){
        resetAllSimulationVariables();
        
        
    }
    

    @Override
    public void resetAllSimulationVariables(){
        resetGlobalVariables();
        resetUserDefinedCommands();
    }

    @Override
    public void resetGlobalVariables(){
        this.slogoVariables = new HashMap<String, Double>();
    }

    @Override
    public void resetUserDefinedCommands(){
        this.userDefinedCommands = new HashMap<String,CommandTreeNode>();
    }
    

    @Override
    public void putFunction(String functionName, CommandTreeNode headNodeOfFunction){
        this.userDefinedCommands.put(functionName.toLowerCase(), headNodeOfFunction);
    }
    

    @Override
    public CommandTreeNode getFunction(String functionName){
        return this.userDefinedCommands.get(functionName);
    }
    
    //need to make sure that lack of case sensitivity is properly implemented across the code base 
    @Override
    public void setVariableValue(String variable, Double value){
        this.slogoVariables.put(variable, value);
    }
    
// if the variable does not exist then we need to create it with the value of zero
    @Override
    public double getVariableValue(String variable){
        if(!this.slogoVariables.containsKey(variable)){
            setVariableValue(variable, CommandTreeNode.DOUBLE_ZERO);
        }
        return this.slogoVariables.get(variable);
    }
}
