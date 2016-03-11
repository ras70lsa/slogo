package model;


import java.util.ResourceBundle;

import backend.slogo.team04.BackendTestNullModelActor;
import backend.slogo.team04.CmdTreeHeadNode;
import backend.slogo.team04.INonLinearCommand;
import backend.slogo.team04.SlogoScanner;
import constants.DisplayConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ICommunicator;
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
    private BackendTestNullModelActor tester; 
    
    public Controller(ICommunicator model, ISlogoModelActionsExtended iSlogoModelActionsExtended) {
    	this.model = model;
    	this.viewModel = iSlogoModelActionsExtended;
    	tester = new BackendTestNullModelActor();
    }
    
    public void parseString(String stringToParse) throws UserInputException, LogicException{
    	SlogoScanner scanner = getProperScanner(stringToParse);
    	String save = scanner.getString();
    	INonLinearCommand myHead = new CmdTreeHeadNode(null).parseString(scanner, model.getExecutionModel());
        myHead.executeCommand(viewModel, model.getExecutionModel());
        update();
        updateHistory(save);
    }
    
    public void update() {
    	viewModel.update();
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

	public void debug() {
		//Add code here
		System.out.println("debug");
		
	}

	public void step() {
		// TODO Auto-generated method stub
		
	}
    


}
