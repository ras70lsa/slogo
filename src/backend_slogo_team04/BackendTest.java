package backend_slogo_team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BackendTest {
    
    
    public static void main(String [] args){
        Parser myParserTest = new Parser(null);
        
        String content = null;
        try {
            content = new Scanner(new File("examples/procedures/dash.logo")).useDelimiter("\\Z").next();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          
        //so the scanner object is passed by reference
//        Scanner scanTest = new Scanner(content);
//        System.out.println(scanTest.next());
//        BackendTest bt = new BackendTest();
//        bt.testObjectReference(scanTest);
//        System.out.println(scanTest.next());
        myParserTest.parseString(content);
    }
        
    
    public void testObjectReference(Scanner scanTest){
        System.out.println(scanTest.next());
    }

}
