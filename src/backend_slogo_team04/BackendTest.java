package backend_slogo_team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import exceptions.UserInputException;

public class BackendTest {
    
    
    public static void main(String [] args){
     //Testing the cmdAnd class
        CommandTreeNode andTest = new CmdNot(null);
        try {
            andTest.parseString(null, null);
        }
        catch (UserInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(andTest.executeCommand(null, null));
        
    }
        
    


}
