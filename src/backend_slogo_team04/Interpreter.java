package backend_slogo_team04;

import java.util.HashMap;
import java.util.Map;
/**
 * This class h
 * @author jonathanim
 *
 */
public class Interpreter implements ISlogoInterpreter {
    
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#interpretCommandTree(backend_slogo_team04.INonLinearCommand)
     */
    @Override
    public void interpretCommandTree(INonLinearCommand headNode){
        
        return;
    }
    
    
    
    
    private Map<String, Double> slogoVariables;
    private Map<String, CommandTreeNode> userDefinedCommands;
    
    
    
    public Interpreter(){
        resetAllSimulationVariables();
        
        
    }
    
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#resetAllSimulationVariables()
     */
    @Override
    public void resetAllSimulationVariables(){
        resetGlobalVariables();
        resetUserDefinedCommands();
    }
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#resetGlobalVariables()
     */
    @Override
    public void resetGlobalVariables(){
        this.slogoVariables = new HashMap<String, Double>();
    }
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#resetUserDefinedCommands()
     */
    @Override
    public void resetUserDefinedCommands(){
        this.userDefinedCommands = new HashMap<String,CommandTreeNode>();
    }
    
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#putFunction(java.lang.String, backend_slogo_team04.CommandTreeNode)
     */
    @Override
    public void putFunction(String functionName, CommandTreeNode headNodeOfFunction){
        this.userDefinedCommands.put(functionName.toLowerCase(), headNodeOfFunction);
    }
    
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#getFunction(java.lang.String)
     */
    @Override
    public CommandTreeNode getFunction(String functionName){
        return this.userDefinedCommands.get(functionName);
    }
    
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#setVariableValue(java.lang.String, java.lang.Double)
     */
    @Override
    public void setVariableValue(String variable, Double value){
        this.slogoVariables.put(variable.toLowerCase(), value);
    }
    
    /* (non-Javadoc)
     * @see backend_slogo_team04.ISlogoInterpreter#getVariableValue(java.lang.String)
     */
    @Override
    public double getVariableValue(String variable){
        return this.slogoVariables.get(variable.toLowerCase());
    }
}
