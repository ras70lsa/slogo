package backend_slogo_team04;

public interface ISlogoInterpreter {

    /**
     * This method will call the executeCommand on the head node passed in
     * 
     * @param headNode The node from which we are to start emulating the commands input
     */
    void interpretCommandTree (INonLinearCommand headNode);

    void resetAllSimulationVariables ();

    void resetGlobalVariables ();

    void resetUserDefinedCommands ();

    void putFunction (String functionName, CommandTreeNode headNodeOfFunction);

    CommandTreeNode getFunction (String functionName);

    void setVariableValue (String variable, Double value);

    double getVariableValue (String variable);

}
