package backend_slogo_team04;

import java.util.Scanner;

public class Parser {
    
    Controller myController;
    Interpreter myInterpreter;
    
    public Parser(Controller myController){
        this.myController = myController;
        myInterpreter = new Interpreter();
    }
    
    
    public Action parseString(String stringToParse){
        //TODO something that breaks input string into space delineated words
        Scanner myScanner = new Scanner(stringToParse);
        
        //each node will parse the next word, and will decide whether or not to "hold onto the stream of incoming text"
        // thus all that remains to be done here is to instantiated ahead node and pass it the scanner
        // We must also set the scanner here so that it will break the strings up properly 
        
        // *****  Slogo is not case sensitive *******
        
        // ***** comments are denoted by a '#' or a completely blank line
        // commands can be formatted over any number of lines
        // must be separated by one or more spaces
        
        
        NonLinearCommand myHeadNode = null; // now make the tree here
        
        //TODO 
        myInterpreter.interpretCommandTree(myHeadNode);
        
        myScanner.close();
        return null;
    }
    
    private void makeCommandTree(){
        
    }
    
    /**
     * returns the next command to be read in by the parser
     */
    public String getNextCommand() {
    	return null;
    }
    
    public NonLinearCommand getNonLinearCommandForString(String input){
        return null;
    }
    
    

}
