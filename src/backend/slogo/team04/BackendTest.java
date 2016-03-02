package backend.slogo.team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import constants.DisplayConstants;
import exceptions.LogicException;
import exceptions.UserInputException;

public class BackendTest {


    public static void main(String [] args){

        //we are going to test the code used to 

        File myFile = new File("examples/procedures_with_parameters/house.logo");
        String content = null;
        try {
            content = new Scanner(myFile).useDelimiter("\\Z").next();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //        SlogoScanner ss = new SlogoScanner(myFile);
        //        Scanner myScanner = ss.getSlogoFormattedScanner();
        //        int i = 0;
        //        while(myScanner.hasNext()){
        //            System.out.println("Line " + i++ + ":" + myScanner.next() + "|");
        //        }



        //now we are going to test the variable code
        //        Interpreter myInterpreter = new Interpreter();
        //        CmdVariable myvar = new CmdVariable(null, "Test");
        //        
        //        System.out.println(myvar.getVariableValue(myInterpreter ));
        //        myvar.setVariableValue(12d, myInterpreter);
        //        
        //        System.out.println(myvar.getVariableValue(myInterpreter));


        SlogoScanner scanner = new SlogoScanner(content); 
        String str = scanner.getLanguageConvertedCode(
                                                      ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + "English"));
        System.out.println(str);
        
        SlogoScanner testScanner = new SlogoScanner(str);
        
        BackendTestNullModelActor modelActor = new BackendTestNullModelActor();
        Interpreter myTestInterpreter = new Interpreter();
        
        try {
            INonLinearCommand myHead = new CmdTreeHeadNode(null).parseString(testScanner.getSlogoFormattedScanner(), myTestInterpreter);
            myHead.executeCommand(modelActor, myTestInterpreter);
        }
        catch (UserInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (LogicException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }




}
