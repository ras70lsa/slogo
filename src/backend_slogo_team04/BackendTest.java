package backend_slogo_team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;

public class BackendTest {
    
    
    public static void main(String [] args){
     //Testing the cmdAnd class
        CommandTreeNode andTest = new CmdPi(null);
        try {
            andTest.parseString(null, null);
        }
        catch (UserInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            System.out.println(andTest.executeCommand(null, null));
        }
        catch (LogicException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
        
    


}
