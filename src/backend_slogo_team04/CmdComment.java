package backend_slogo_team04;

import java.util.Scanner;
import java.util.regex.Pattern;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;

/**
 * Will just eat things until it sees a new line
 * @author jonathanim
 *
 */
public class CmdComment extends CommandTreeNode {



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
        String content = myScanner.useDelimiter("[[\\S]\\t\\x0B\\f\\r ]+").next(); //"[[\\S]\\t\\x0B\\f\\r ]+"
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
