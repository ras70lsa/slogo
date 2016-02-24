package backend_slogo_team04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BackendTest {
    
    
    public static void main(String [] args){
        
        //Testing to see the default behavior of hashmap if object does not exist in it:
        
        HashMap<String, String> test = new HashMap<String, String>();
        test.put("Test", "Bang, Bang");
        
        System.out.println("Testing 1");
        System.out.println(test.get("Test"));
        System.out.println("Testing 2");
        System.out.println(test.get("Atest"));
        System.out.println(test.get("garbage") == null);
        
        
//        Parser myParserTest = new Parser(null);
//        String content = null;
//        try {
//            content = new Scanner(new File("examples/procedures/dash.logo")).useDelimiter("\\Z").next();
//        }
//        catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        myParserTest.parseString(content);
    }
        
    


}
