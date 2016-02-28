package backend_slogo_team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import exceptions.LogicException;
import exceptions.UserInputException;

public class BackendTest {
    
    
    public static void main(String [] args){
        
        //we are going to test the code used to 
        
//        File myFile = new File("examples/procedures/dash.logo");
//        SlogoScanner ss = new SlogoScanner(myFile);
//        Scanner myScanner = ss.getSlogoFormattedScanner();
//        int i = 0;
//        while(myScanner.hasNext()){
//            System.out.println("Line " + i++ + ":" + myScanner.next() + "|");
//        }
        
        
        
        //now we are going to test the variable code
        Interpreter myInterpreter = new Interpreter();
        CmdVariable myvar = new CmdVariable(null, "Test");
        
        System.out.println(myvar.getVariableValue(myInterpreter ));
        myvar.setVariableValue(12d, myInterpreter);
        
        System.out.println(myvar.getVariableValue(myInterpreter));
        
        
    }
        
    


}
