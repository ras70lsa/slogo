package backend.slogo.team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlogoScanner {
    protected static final String ALL_NEW_LINE_CHARACTERS = "[\\n]";
    protected static final String ALL_NON_NEW_LINE_REGEX = "[[\\S]\\t\\x0B\\f\\r ]+";
    protected static final String ALL_WHITESPACE_REGEX = "[\\s]+";
    
    //slogo words include different characters from the standard java /w, need to write our own boundary regex

    protected static final String SLOGO_WORD_BOUNDARY = "(?:(?=[a-zA-Z0-9\\?\\+\\*-~%/])(?<![a-zA-Z0-9\\?\\+\\*-~%/])|(?<=[a-zA-Z0-9\\?\\+\\*-~%/])(?![a-zA-Z0-9\\?\\+\\*-~%/]))";
    //SO MUCH PAIN ^^^ the % can't go between the - and the ~    
    

    private Scanner myScanner;
    private String str;
    


    public SlogoScanner(String myString){
        this.myScanner = new Scanner(myString);
        this.myScanner.useDelimiter(ALL_WHITESPACE_REGEX);
        str =  myString;
    }

    /**
     * Will remove this method as soon as we are done testing the backend, the backend will only have text input coming from
     * the file pickers or the textbox on the front end
     * @param myFile
     */
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
            String stringRegex = SLOGO_WORD_BOUNDARY + "(" + myResourceBundle.getString(key) + ")" + SLOGO_WORD_BOUNDARY;
            Pattern p = Pattern.compile(stringRegex);
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
            String stringRegex = SLOGO_WORD_BOUNDARY + "(" + key + ")" + SLOGO_WORD_BOUNDARY;   
            Pattern p = Pattern.compile(stringRegex);
            Matcher m = p.matcher(str);
            String find = myBundle.getString(key);
            if(find.indexOf("|") != -1) {
                find = find.substring(0, find.indexOf("|"));
            }
            str = m.replaceAll(find);
        }

    } 

    public String getString() {
        return str;
    }

}
