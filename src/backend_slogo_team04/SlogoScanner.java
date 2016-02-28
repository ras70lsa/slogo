package backend_slogo_team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlogoScanner {
    private static final String ALL_WHITESPACE_REGEX = "[ \\t\\x0B\\f\\r]+";
    private Scanner myScanner;
    private String str;
    
    
    public SlogoScanner(String myString){
        this.myScanner = new Scanner(myString);
        this.myScanner.useDelimiter(ALL_WHITESPACE_REGEX);
        str = myString;
    }
    
    public SlogoScanner(File myFile){
        try {
            this.myScanner = new Scanner(myFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace(); // should throw an error to cause the user to re-select the proper input file
        }
        this.myScanner.useDelimiter(ALL_WHITESPACE_REGEX);
    }

    

    
    public Scanner getSlogoFormattedScanner(){
        return this.myScanner;
    }
    
   public String getLanguageConvertedCode(ResourceBundle myResourceBundle) {
       replace(myResourceBundle);
       return str;
   }
    
    
    private void replace(ResourceBundle myResourceBundle) {
    	for(String key: myResourceBundle.keySet()) {
    		Pattern p = Pattern.compile("\\b" + myResourceBundle.getString(key) + "\\b");
    		Matcher m = p.matcher(str);
    		str = m.replaceAll(key);
    		
    	}
    }

    public String getCodeConvertToLanguage(ResourceBundle myResourceBundle) {
       replaceReverse(myResourceBundle);
       return str;
   }
   
	private void replaceReverse(ResourceBundle myBundle ) {
		for(String key: myBundle.keySet()) {
    		Pattern p = Pattern.compile("\\b" + key + "\\b");
    		Matcher m = p.matcher(str);
    		String find = myBundle.getString(key);
    		if(find.indexOf("|") != -1) {
    			find = find.substring(0, find.indexOf("|"));
    		}
    		str = m.replaceAll(find);
		}
		
    } 

}
