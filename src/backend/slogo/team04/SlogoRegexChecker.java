package backend.slogo.team04;

import java.util.regex.Pattern;
//TODO should rename this class to represent a more general value bounds, type checking class 

/**
 * This class contains useful regex patterns and an api for checking strings against them for use throughout the backend
 * @author jonathanim
 *
 */
public class SlogoRegexChecker {
    final static String Digits     = "(\\p{Digit}+)";
    final static String HexDigits  = "(\\p{XDigit}+)";
    // an exponent is 'e' or 'E' followed by an optionally 
    // signed decimal integer.
    final static String Exp        = "[eE][+-]?"+Digits;
    final static String fpRegex    =
            ("[\\x00-\\x20]*"+  // Optional leading "whitespace"
                    "[+-]?(" + // Optional sign character
                    "NaN|" +           // "NaN" string
                    "Infinity|" +      // "Infinity" string

            // A decimal floating-point string representing a finite positive
            // number without a leading sign has at most five basic pieces:
            // Digits . Digits ExponentPart FloatTypeSuffix
            // 
            // Since this method allows integer-only strings as input
            // in addition to strings of floating-point literals, the
            // two sub-patterns below are simplifications of the grammar
            // productions from the Java Language Specification, 2nd 
            // edition, section 3.10.2.

            // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
            "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

            // . Digits ExponentPart_opt FloatTypeSuffix_opt
            "(\\.("+Digits+")("+Exp+")?)|"+

      // Hexadecimal strings
      "((" +
      // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
      "(0[xX]" + HexDigits + "(\\.)?)|" +

       // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
       "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

       ")[pP][+-]?" + Digits + "))" +
       "[fFdD]?))" +
                    "[\\x00-\\x20]*");// Optional trailing "whitespace"

    /**
     * The regex pattern for this code is taken straight from the JavaDocs, provided as an alternative to using the {@link Double#parseDouble(String)}
     * which results in a {@link java.lang.NumberFormatException NumberFormatException}
     * @see <a href="http://docs.oracle.com/javase/6/docs/api/java/lang/Double.html#valueOf%28java.lang.String%29">http://docs.oracle.com/javase/6/docs/api/java/lang/Double.html</a>
     * @param myString
     * @return
     */
    public static boolean isDouble(String myString){
        return Pattern.matches(fpRegex, myString);
    }

    private final static String VARIABLE_PATTERN = ":[a-zA-Z_]+";
    public static boolean isVariable(String myString){
        return Pattern.matches(VARIABLE_PATTERN, myString);
    }

    private final static String COMMAND_PATTERN = "[a-zA-Z_]+(\\?)?";
    public static boolean conformsToCmdNamingConventions(String myString){
        return Pattern.matches(COMMAND_PATTERN , myString);
    }

    private final static String COMMENT_PATTERN = "^#.*";
    public static boolean isStartOfComment(String myString){
        return Pattern.matches(COMMENT_PATTERN, myString);
    }

    private final static String START_OF_LIST = "[";
    public static boolean isStartOfList(String myString){
        return myString.equals(START_OF_LIST);
    }

    private final static String END_OF_LIST = "]";
    public static boolean isEndOfList(String myString){
        return myString.equals(END_OF_LIST);
    }
    
    
    public static boolean isPostiveIndex(double myDouble){
        return isPostive(myDouble) && isIndexValue(myDouble);
    }
    
    
    private static boolean isPostive(double myDouble){
        return myDouble > 0d;
    }
    
    
    private static boolean isIndexValue(double myDouble){
        return (int) myDouble == myDouble;
    }
    
    
    private final static double LOWER_RGB_BOUND = 0d;
    private final static double UPPER_RBG_BOUND = 256d;
    public static boolean isRGBValue(double myDouble){
        return isIndexValue(myDouble) && doubleWithinBoundsExclusive(myDouble, LOWER_RGB_BOUND, UPPER_RBG_BOUND);
    }
    
    private static boolean doubleWithinBoundsExclusive(double value, double lowerBound, double upperBound){
        return (lowerBound < value) && (value < upperBound);
    }







}
