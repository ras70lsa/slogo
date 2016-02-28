package model;


import java.util.ResourceBundle;
import backend_slogo_team04.INonLinearCommand;
import backend_slogo_team04.SlogoScanner;
import constants.DisplayConstants;
import interfaces_slogo_team04.ICommunicator;
import interfaces_slogo_team04.IModel;


/**
 * This class serves to unify our parser, interpreter, model and view classes
 * @author jonathanim
 * @author Ryan St Pierre
 *
 */

public class Controller {

    private ICommunicator model;
    
    public Controller(ICommunicator model) {
    	this.model = model;
    }
    
    public void parseString(String stringToParse){
    	SlogoScanner scanner = new SlogoScanner(stringToParse); 
    	String debug = scanner.getLanguageConvertedCode(
    			ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + model.getLanguage()));
    	//model.addToHistory(str);
    }
    
    public void interpretInformation(INonLinearCommand head){
    	
    }
    


}
