package model;


import java.util.ResourceBundle;

import backend_slogo_team04.BackendTestNullModelActor;
import backend_slogo_team04.CmdTreeHeadNode;
import backend_slogo_team04.INonLinearCommand;
import backend_slogo_team04.Interpreter;
import backend_slogo_team04.SlogoScanner;
import constants.DisplayConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ICommunicator;
import interfaces_slogo_team04.IModel;
import interfaces_slogo_team04.ISlogoModelActions;


/**
 * This class serves to unify our parser, interpreter, model and view classes
 * @author jonathanim
 * @author Ryan St Pierre
 *
 */

public class Controller {

    private ICommunicator model;
    private ISlogoModelActions viewModel;
    private BackendTestNullModelActor tester; 
    
    public Controller(ICommunicator model, ISlogoModelActions viewModel) {
    	this.model = model;
    	this.viewModel = viewModel;
    	tester = new BackendTestNullModelActor();
    }
    
    public void parseString(String stringToParse) throws UserInputException, LogicException{
    	SlogoScanner scanner = getProperScanner(stringToParse);
    	String save = scanner.getString();
    	INonLinearCommand myHead = new CmdTreeHeadNode(null).parseString(scanner.getSlogoFormattedScanner(), model.getExecutionModel());
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
    


}
