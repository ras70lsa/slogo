package backend.slogo.team04;


import java.util.ArrayList;
import java.util.List;



/**
 * Another throwaway testing class
 * @author jonathanim
 *
 */
public class BackendTestTwo {


    public static void main(String [] args){

        //we are going to test the code used to 




//        SlogoScanner scanner = new SlogoScanner(" less? "); 
//        Pattern testPattern = Pattern.compile("(\\b|\\B)" + "(less\\?|lessp)" + "(\\b|\\B)");
//        Matcher m = testPattern.matcher("less?");
//        System.out.println(m.matches());
//        
//        
//        String str = scanner.getLanguageConvertedCode(
//                                                      ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + "English"));
//        System.out.println(str);
        //testing standard list<Boolean> values
        
        List<Boolean> testList = new ArrayList<Boolean>();
        for(int i = 0; i < 10; i++){
            testList.add(Boolean.FALSE);
        }
        System.out.println(testList.get(2));
        
        testList.set(3, Boolean.TRUE);
        
        if(testList.get(3)){
            System.out.println(testList.size());
            testList.get(9);
            
        }
        //testList.
        
        

    }




}
