package backend.slogo.team04;


import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import constants.DisplayConstants;



/**
 * Another throwaway testing class
 * @author jonathanim
 *
 */
public class BackendTestTwo {


    public static void main(String [] args){

        //we are going to test the code used to 




        SlogoScanner scanner = new SlogoScanner(" less? "); 
        Pattern testPattern = Pattern.compile("(\\b|\\B)" + "(less\\?|lessp)" + "(\\b|\\B)");
        Matcher m = testPattern.matcher("less?");
        System.out.println(m.matches());
        
        
        String str = scanner.getLanguageConvertedCode(
                                                      ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + "English"));
        System.out.println(str);
        
        
        

    }




}
