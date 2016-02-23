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
        myParserTest.parseString(content);
    }
        

}
