package backend_slogo_team04;

import java.util.regex.Pattern;


/**
 * The regex pattern for this code is taken straight from the JavaDocs, provided as an alternative to using the {@link Double#parseDouble(String)}
 * which results in a {@link java.lang.NumberFormatException NumberFormatException}
 * @see <a href="http://docs.oracle.com/javase/6/docs/api/java/lang/Double.html#valueOf%28java.lang.String%29">http://docs.oracle.com/javase/6/docs/api/java/lang/Double.html</a>
 * @author jonathanim
 *
 */
public class NumberFormatChecker {
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


    public static boolean isDouble(String myString){
        return Pattern.matches(fpRegex, myString);
    }
    

}
