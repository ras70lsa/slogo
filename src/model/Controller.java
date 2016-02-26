package model;


import java.util.ResourceBundle;
import java.util.Stack;

import backend_slogo_team04.Action;
import backend_slogo_team04.INonLinearCommand;
import backend_slogo_team04.SlogoScanner;
import constants.DisplayConstants;
import frontend_slogo_team04.History;
import frontend_slogo_team04.UserTextInput;
import frontend_slogo_team04.UserTextInputState;
import interfaces_slogo_team04.IHistoryModel;
import interfaces_slogo_team04.IModel;
import interfaces_slogo_team04.ISlogoModelActions;


/**
 * This class serves to unify our parser, interpreter, model and view classes
 * @author jonathanim
 * @author Ryan St Pierre
 *
 */

public class Controller {

    private IModel model;
    
    public Controller(IModel model) {
    	this.model = model;
    }
    
    public void parseString(String stringToParse){
    	SlogoScanner scanner = new SlogoScanner(stringToParse); 
    	String str = scanner.getLanguageConvertedCode(
    			ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + model.getLangauage()));
    	model.getHistory().add(str);
    }
    
    public void interpretInformation(INonLinearCommand head){
    	
    }
    


}
