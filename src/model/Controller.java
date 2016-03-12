package model;


import java.util.ResourceBundle;
import backend.slogo.team04.CmdTreeHeadNode;
import backend.slogo.team04.ExecutionModeDebug;
import backend.slogo.team04.ExecutionModeNormal;
import backend.slogo.team04.INonLinearCommand;
import backend.slogo.team04.ISlogoDebugObject;
import backend.slogo.team04.SlogoScanner;
import constants.DisplayConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
import frontend.slogo.team04.Display;
import interfaces.slogo.team04.ICommunicator;

import interfaces.slogo.team04.IDisplay;
import interfaces.slogo.team04.ISlogoModelActions;

import interfaces.slogo.team04.ISlogoModelActionsExtended;


/**
 * This class serves to unify our parser, interpreter, model and view classes
 * @author jonathanim
 * @author Ryan St Pierre
 *
 */

public class Controller {

    private ICommunicator model;
    private ISlogoModelActionsExtended viewModel;


    private INonLinearCommand myDebugPosition;
    private ISlogoDebugObject myDebugObject;

    private IDisplay display;

    public Controller(ICommunicator model, ISlogoModelActionsExtended iSlogoModelActionsExtended) {
        this.model = model;
        this.viewModel = iSlogoModelActionsExtended;
        myDebugObject = null;
    }


    public void parseString(String stringToParse) throws UserInputException, LogicException{
        SlogoScanner scanner = getProperScanner(stringToParse);
        String save = scanner.getString();
        INonLinearCommand myHead = new CmdTreeHeadNode(null).parseString(scanner, model.getExecutionModel());
        myHead.executeCommand(viewModel, model.getExecutionModel(), new ExecutionModeNormal());
        update();
        updateHistory(save);
    }

    public void update() {
        viewModel.update();
        if(display.getVariableFeature() !=null) {
            display.getVariableFeature().drawTabs();
        }
    }

    private void updateHistory(String input) {
        if(input != null && !input.equals("")) {
            model.addToHistory(input);
        }
    }

    private SlogoScanner getProperScanner(String stringToParse) {
        SlogoScanner scanner = new SlogoScanner(stringToParse); 
        String debug = scanner.getLanguageConvertedCode(
                                                        ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + model.getLanguage()));
        SlogoScanner test = new SlogoScanner(debug);
        //System.out.println(debug);
        return test;
    }

    public void interpretInformation(INonLinearCommand head){

    }

    public void addActor() {
        model.addActor();

    }

    public void debug(String stringToParse) throws UserInputException,LogicException {
        SlogoScanner scanner = getProperScanner(stringToParse);
        String save = scanner.getString();
        INonLinearCommand myHead = new CmdTreeHeadNode(null).parseString(scanner, model.getExecutionModel());
        myDebugObject = new ExecutionModeDebug();
        myDebugObject.setDrawReadySynchObject(this);

        //might need to fork/spin up new process here
        //myHead.executeCommand(viewModel, model.getExecutionModel(), myDebugObject);
        synchronized(this){
            new Thread(new ControllerParseThread(viewModel, model.getExecutionModel(), myDebugObject, myHead)).start();
            try {
                this.wait();
                update();
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        checkIfDoneDebugging();
        //update();

    }

    public void step() throws UserInputException, InterruptedException {
        if(myDebugObject == null){
            throw new UserInputException("Must select/input text to debug first");
        }
        synchronized( this){
            synchronized(myDebugObject){
                myDebugObject.userAttemptedStep();
                myDebugObject.notify();
            }

            //System.out.println("Trying to step");
            this.wait();
            update();
        }
        checkIfDoneDebugging();


    }

    private void checkIfDoneDebugging(){
        synchronized(myDebugObject){
            if(myDebugObject.isDoneStepping()){
                display.disableStep();
                //System.out.println("We are done debug stepping");
            }
            update();
        }
    }

    public void setDisplay(IDisplay view) {
        this.display =view;
    }



}
