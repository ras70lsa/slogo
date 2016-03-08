package backend.slogo.team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.UserInputException;

public class SlogoScanner {
    protected static final String ALL_NEW_LINE_CHARACTERS = "[\\n]";
    public static final String ALL_NON_NEW_LINE_REGEX = "[[\\S]\\t\\x0B\\f\\r ]+";
    protected static final String ALL_WHITESPACE_REGEX = "[\\s]+";
    
    //slogo words include different characters from the standard java /w, need to write our own boundary regex

    //this enables us to include all the characters used by slogo commands as part of 'words'
    protected static final String SLOGO_WORD_BOUNDARY = "(?:(?=[a-zA-Z0-9\\[\\]\\(\\)\\?\\+\\*-~%/])(?<![a-zA-Z0-9\\[\\]\\(\\)\\?\\+\\*-~%/])|(?<=[a-zA-Z0-9\\[\\]\\(\\)\\?\\+\\*-~%/])(?![a-zA-Z0-9\\[\\]\\(\\)\\?\\+\\*-~%/]))";
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
    public Pattern delimiter(){
        return myScanner.delimiter();
    }
    
    public void useDelimiter(Pattern pattern){
        myScanner.useDelimiter(pattern);
    }
    
    public void useDelimiter(String pattern){
        myScanner.useDelimiter(pattern);
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
    
    public boolean hasNext(){
        return this.myScanner.hasNext();
    }
    
//    /**
//     * This should only be used by {@link CmdTreeHeadNode#parseString(SlogoScanner, ISlogoInterpreter)} because 
//     * @return
//     */
//    protected String uncheckedNext(){
//        return this.myScanner.next();
//    }

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

    protected boolean checkIfStartOfList(String currentWord, ISlogoInterpreter myInterpreter) throws UserInputException{
        String toTest = currentWord;
        toTest = advanceScannerPastComments(toTest, myInterpreter);
        return SlogoRegexChecker.isStartOfList(toTest);
    }

    protected boolean checkIfEndOfList(String currentWord, ISlogoInterpreter myInterpreter) throws UserInputException{
        String toTest = currentWord;
        toTest = advanceScannerPastComments(toTest, myInterpreter);
        return SlogoRegexChecker.isEndOfList(toTest);
    }

    protected String advanceScannerPastComments(String currentWord, ISlogoInterpreter myInterpreter) throws UserInputException{
        
        String curWord = currentWord;
        while(SlogoRegexChecker.isStartOfComment(curWord)){
            new CmdComment(null).parseString(this, myInterpreter);
            curWord = this.getNextWord(); 
        }
        return curWord;
        
    }

    /**
     * Should be moved into the slogo scanner so that the user does not have to remember to call this safe way of getting
     * the next element in the scanner
     * @return
     * @throws UserInputException
     */
    protected String getNextWord() throws UserInputException{
        String myWord;
        try{
            myWord = myScanner.next();
        }catch(NoSuchElementException e){
            throw new UserInputException("Incomplete Slogo commands detected"); //TODO make this use resource bundles later
        }
        return myWord;
    }

}
