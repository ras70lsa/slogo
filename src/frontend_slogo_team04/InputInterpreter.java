package frontend_slogo_team04;

import java.util.ResourceBundle;

public class InputInterpreter {

	ResourceBundle myBundle;
	
	public InputInterpreter(String language) {
		
		myBundle = ResourceBundle.getBundle("resources.languages/" + language);
		
	}
	
	public String convertInput(String input) {
		
		String output = "";
		output = output + convert(input);
		return output;
		
	}
	public String convert(String text) {
		
		for(String key: myBundle.keySet()) {
			if(text.matches(myBundle.getString(key))) {
				text = key;
				break;
			}
		}
		return text;
	}

}
