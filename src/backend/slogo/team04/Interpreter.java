package backend.slogo.team04;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.ListProperty;
/**
 * This class h
 * @author jonathanim
 *
 */
public class Interpreter implements ISlogoInterpreter {
    
    private Map<String, Double> slogoVariables;
    private Map<String, INonLinearCommand> userDefinedCommands;
    
    
    
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



}
