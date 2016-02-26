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
    	Model m = new Model();
    	historyState.addToStack(new Action(m, "first", null, null));
    }
    
    public void parseString(String stringToParse){
    	Model m = new Model();
    	historyState.addToStack(new Action(m, stringToParse, null, null));
    }
    
    public void interpretInformation(INonLinearCommand head){
    	
    }
    


}
