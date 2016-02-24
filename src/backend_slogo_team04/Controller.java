package backend_slogo_team04;


import java.util.Stack;

import frontend_slogo_team04.History;
import frontend_slogo_team04.HistoryState;
import frontend_slogo_team04.UserTextInput;
import frontend_slogo_team04.UserTextInputState;
import interfaces_slogo_team04.ISlogoModelActions;


/**
 * This class serves to unify our parser, interpreter, model and view classes
 * @author jonathanim
 *
 */

public class Controller {

    private UserTextInputState textState;
    private HistoryState historyState;
    
    public Controller(UserTextInputState textState, HistoryState hState) {
    	this.textState = textState;
    	this.historyState = hState;
    	historyState.addToStack();
    }
    
    public void parseString(String stringToParse){
        System.out.println(stringToParse);
    }
    
    public void interpretInformation(INonLinearCommand head){
    	
    }
    


}
