package backend.slogo.team04;



public interface ISlogoInterpreter {

    void resetAllSimulationVariables ();

    void resetGlobalVariables ();

    void resetUserDefinedCommands ();

    void putFunction (String functionName, INonLinearCommand headNodeOfFunction);

    INonLinearCommand getFunction (String functionName);

    double setVariableValue (String variable, Double value);

    double getVariableValue (String variable);
 

    
    


}
