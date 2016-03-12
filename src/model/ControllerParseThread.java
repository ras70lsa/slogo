package model;

import backend.slogo.team04.INonLinearCommand;
import backend.slogo.team04.ISlogoDebugObject;
import backend.slogo.team04.ISlogoInterpreterVariableScope;
import exceptions.LogicException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class ControllerParseThread implements Runnable{
    private ISlogoModelActionsExtended myModel;
    private ISlogoInterpreterVariableScope myInterp;
    private ISlogoDebugObject myDebugObject;
    private INonLinearCommand myHeadNode;
    
    
    public ControllerParseThread (ISlogoModelActionsExtended viewModel,
                                  ISlogoInterpreterVariableScope interpreterState, ISlogoDebugObject debugObject, INonLinearCommand headNode) {
        this.myModel = viewModel;
        this.myInterp = interpreterState;
        this.myDebugObject = debugObject;
        this.myHeadNode = headNode;
    }

    @Override
    public void run () {
        try {
            myHeadNode.executeCommand(myModel, myInterp, myDebugObject);
        }
        catch (LogicException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
