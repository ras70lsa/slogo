package backend.slogo.team04;

import java.util.Scanner;
import java.util.regex.Pattern;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;

/**
 * Will just eat things until it sees a new line
 * @author jonathanim
 *
 */
public class CmdComment extends CommandTreeNode {
    public static final String REGEX_CHARS_AND_WHITESPACE_LESS_NEWLINE = "[[\\S]\\t\\x0B\\f\\r ]+";


    public CmdComment (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public INonLinearCommand parseString (Scanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        
        Pattern cached = myScanner.delimiter();
        //myScanner.useDelimiter(SlogoScanner.ALL_NON_NEW_LINE_WHITESPACE_REGEX);
        myScanner.useDelimiter(REGEX_CHARS_AND_WHITESPACE_LESS_NEWLINE);//.next(); //"[[\\S]\\t\\x0B\\f\\r ]+"
        CommandTreeNode.getNextWord(myScanner);
        //System.out.println("Test" + content + ":");
//        for(int i = 0; i < content.length(); i++){
//            System.out.printf("%d\n", (int) content.charAt(i));
//        }
        myScanner.useDelimiter(cached);
       // while(myScanner.hasNext()){
       //     System.out.println(myScanner.next());
       // }
        //myScanner.next
       // myScanner.useDelimiter("[\\S]*");
       // myScanner.next();
        
       
        
        return this; //this will cause the comment to not even touch the tree
    }

}
