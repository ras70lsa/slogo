package backend_slogo_team04;

import java.util.HashMap;
import java.util.Map;
/**
 * This class h
 * @author jonathanim
 *
 */
public class Interpreter {
    
    /**
     * This method will call the executeCommand on the head node passed in
     * 
     * @param headNode The node from which we are to start emulating the commands input
     */
    public void interpretCommandTree(INonLinearCommand headNode){
        
        return;
    }
    
    
    
    
    private Map<String, Double> slogoVariables;
    private Map<String, CommandTreeNode> userDefinedCommands;
    
    
    
    public Interpreter(){
        resetAllSimulationVariables();
        
        
    }
    
    public void resetAllSimulationVariables(){
        this.slogoVariables = new HashMap<String, Double>();
        this.userDefinedCommands = new HashMap<String,CommandTreeNode>();
    }
    
    public void putFunction(String functionName, CommandTreeNode headNodeOfFunction){
        this.userDefinedCommands.put(functionName.toLowerCase(), headNodeOfFunction);
    }
    
    public CommandTreeNode getFunction(String functionName){
        return this.userDefinedCommands.get(functionName);
    }
    
    public void setVariableValue(String variable, Double value){
        this.slogoVariables.put(variable.toLowerCase(), value);
    }
    
    public double getVariableValue(String variable){
        return this.slogoVariables.get(variable.toLowerCase());
    }
}
