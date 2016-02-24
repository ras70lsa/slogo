package backend_slogo_team04;

import java.util.ResourceBundle;
import java.util.Scanner;

public class SlogoScanner {
    private static final String ALL_WHITESPACE_REGEX = "[ \\t\\x0B\\f\\r]+";
    private static final String NEW_LINE_POSSIBLE = "[\\n]*";
    private static final String NEW_LINE_YES = "[\\n]+\\w+";
    private static final String WHITESPACE = " ";
    private Scanner myScanner;
    
    public SlogoScanner(String myString){
        this.myScanner = new Scanner(myString);
        this.myScanner.useDelimiter(ALL_WHITESPACE_REGEX);
    }
    
    public Scanner getSlogoFormattedScanner(){
        return this.myScanner;
    }
    
    /**
     * NEED TO REFACTOR THESE 4 METHODS INTO 2
     */
   public String getLanguageConvertedCode(ResourceBundle myResourceBundle) {
       StringBuilder myStringBuilder = new StringBuilder();
       while(myScanner.hasNext()){
    	   String str = myScanner.next();
           String nextSlogoCommand = convert(str, myResourceBundle);
           if(str.matches(NEW_LINE_YES)) {
    		   myStringBuilder.append("\\n" + nextSlogoCommand + WHITESPACE);
    		   System.out.println("HERE");
    	   } else {
    		   myStringBuilder.append(nextSlogoCommand + WHITESPACE);
    	   }
       }

       return myStringBuilder.toString();
   }
   
   public String getCodeConvertToLanguage(ResourceBundle myResourceBundle) {
       StringBuilder myStringBuilder = new StringBuilder();
       while(myScanner.hasNext()){
    	   String toTest = myScanner.next();
    	   String nextSlogoCommand = convertReverse(toTest, myResourceBundle);
    	   myStringBuilder.append(nextSlogoCommand + WHITESPACE);
    	   
       }
       return myStringBuilder.toString();
   }
    
    
    private String convert(String text, ResourceBundle myBundle ) {
        for(String key: myBundle.keySet()) {
        		String newReg = NEW_LINE_POSSIBLE + "(" + myBundle.getString(key) + ")";
                if(text.matches(newReg)) {
                        text = key;
                        break;
                }
        }
        return text;
    }  
   
	private String convertReverse(String text, ResourceBundle myBundle ) {
		System.out.println(text.substring(0, 2).equals("\\n"));
		if(text.substring(0, 2).equals("\\n")) {
			text= text.substring(2);
		}
        if(myBundle.containsKey(text)) {
              String str = myBundle.getString(text);
              return str.substring(0, str.indexOf("|"));
        }
        return text;
    } 

}
