package backend_slogo_team04;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import exceptions.LogicException;
import exceptions.StructuralException;
import exceptions.UserInputException;
import interfaces_slogo_team04.ISlogoModelActions;



/**
 * This class forms the basis for how we are going to parse the input text, each word allowable in the language will expects certain
 * types of words to follow, each subsequent word suggesting its own logic
 * 
 * Thus we are using recursion, and passing the scanner object containing the text to be parsed around in order to 
 * @author jonathanim
 *
 */
public abstract class CommandTreeNode implements INonLinearCommand {
    private static final char START_OF_COMMENT_CHAR = '#';
    protected static final char NEW_LINE_CHAR = '\n';
    private static final char VAR_FIRST_CHAR = ':';
    protected static final double DOUBLE_ZERO = 0d;
    protected static final double DOUBLE_ONE = 1d;
    private static final String START_OF_LIST = "[";
    
    private List<CommandTreeNode> myChildren;
    private CommandTreeNode myParent; //in case we need to do weird scope things in the future





    public CommandTreeNode(CommandTreeNode myParent){
        this.myParent = myParent;
        myChildren = new ArrayList<CommandTreeNode>();
    }

    protected void addChildNode(CommandTreeNode nodeToAdd){
        if(nodeToAdd != null){
            this.myChildren.add(nodeToAdd);
        }
    }



    protected static INonLinearCommand recursiveSlogoFactoryNoListsAllowed(Scanner myScanner
                                                                         , CommandTreeNode parentNode 
                                                                         , ISlogoInterpreter myInterpreter) throws UserInputException{
        // throw an error here regarding incomplete syntax
        String nextWord = getNextWord(myScanner);
        assertNotStartOfList(nextWord);
        return recursiveSlogoFactoryListsAllowed(myScanner, parentNode, myInterpreter);

    }
    
    protected static INonLinearCommand recursiveSlogoFactoryListsAllowed(Scanner myScanner
                                                                         , CommandTreeNode parentNode
                                                                         , ISlogoInterpreter myInterpreter) throws UserInputException{
        return CommandTreeNode
                .slogoCommandFactory(getNextWord(myScanner), parentNode, myInterpreter)
                .parseString(myScanner, myInterpreter);
    }
    
    protected static CommandTreeNode recursiveListConstruction(Scanner myScanner
                                                               , CommandTreeNode parentNode
                                                               , ISlogoInterpreter myInterpreter) throws UserInputException{
        //TODO create a list object class and let it start feeding in commands
        //check to see if next word is start of list
        //construct list object here,
        return null;
    }
    
    private static void assertNotStartOfList(String myWordToCheck) throws UserInputException{
        if(myWordToCheck.equals(START_OF_LIST)){
            throw new UserInputException("Did not expect list declaration following last command");
        }
    }
    
    private static String getNextWord(Scanner myScanner) throws UserInputException{
        String myWord;
        try{
            myWord = myScanner.next();
        }catch(NoSuchElementException e){
            throw new UserInputException("Incomplete Slogo commands detected"); //TODO make this use resource bundles later
        }
        return myWord;
    }


    protected static CommandTreeNode slogoCommandFactory(String nextWord, CommandTreeNode myParent, ISlogoInterpreter myInterpreter) throws UserInputException{
// we can assume at this point that the only things that are coming are the words themselves and new line characters
        // need to fix this to include the required inputs for hte different classes, and then also
        // include the logic for choosing to instantiate the headnode
        // include the required list node subtype construction
        switch(nextWord){
            // TURTLE COMMANDS
            case "Forward":
                return new CmdForward(myController, myParent);
            case "Backward":
                return new CmdBack(myController, myParent);
            case "Left":
                return new CmdLeft(myController, myParent);
            case "Right":
                return new CmdRight(myController, myParent);
            case "SetHeading":
                return new CmdSetHeading(myController, myParent);
            case "SetTowards":
                return new CmdTowards(myController, myParent);
            case "SetPosition":
                return new CmdSetXY(myController, myParent);
            case "PenDown":
                return new CmdPenDown(myController, myParent);
            case "PenUp":
                return new CmdPenUp(myController, myParent);
            case "ShowTurtle":
                return new CmdShowTurtle(myController, myParent);
            case "HideTurtle":
                return new CmdHideTurtle(myController, myParent);
            case "Home":
                return new CmdHome(myController, myParent);
            case "ClearScreen":
                return new CmdClearScreen(myController, myParent);
                // TURTLE QUERIES
            case "XCoordinate":
                return new CmdXCor(myController, myParent);
            case "YCoordinate":
                return new CmdYCor(myController, myParent);
            case "Heading":
                return new CmdHeading(myController, myParent);
            case "IsPenDown":
                return new CmdIsPenDown(myController, myParent);
            case "IsShowing":
                return new CmdIsShowing(myController, myParent);
                // MATH OPERATIONS
            case "Sum":
                return new CmdSum(myController, myParent);
            case "Difference":
                return new CmdDifference(myController, myParent);
            case "Product":
                return new CmdProduct(myController, myParent);
            case "Quotient":
                return new CmdQuotient(myController, myParent);
            case "Remainder":
                return new CmdRemainder(myController, myParent);
            case "Minus":
                return new CmdMinus(myController, myParent);
            case "Random":
                return new CmdRandom(myController, myParent);
            case "Sine":
                return new CmdSin(myController, myParent);
            case "Cosine":
                return new CmdCos(myController, myParent);
            case "Tangent":
                return new CmdTan(myController, myParent);
            case "ArcTangent":
                return new CmdAtan(myController, myParent);
            case "NaturalLog":
                return new CmdLog(myController, myParent);
            case "Power":
                return new CmdPow(myController, myParent);
            case "Pi":
                return new CmdPi(myController, myParent);
                //BOOLEAN OPERATIONS
            case "LessThan":
                return new CmdLess(myController, myParent);
            case "GreaterThan":
                return new CmdGreater(myController, myParent);
            case "Equal":
                return new CmdEqual(myController, myParent);
            case "NotEqual":
                return new CmdNotEqual(myController, myParent);
            case "And":
                return new CmdAnd(myController, myParent);
            case "Or":
                return new CmdOr(myController, myParent);
            case "Not":
                return new CmdNot(myController, myParent);
                // Variables, Control, and User-Defined Commands
            case "MakeVariable":
                return new CmdMake(myController, myParent);
            case "Repeat":
                return new CmdRepeat(myController, myParent);
            case "DoTimes":
                return new CmdDoTimes(myController, myParent);
            case "For":
                return new CmdFor(myController, myParent);
            case "If":
                return new CmdIf(myController, myParent);
            case "IfElse":
                return new CmdIfElse(myController, myParent);
            case "MakeUserInstruction": //logic to prevent collision of existing commands will belong to the cmdTo class itself
                return new CmdTo(myController, myParent);
        }
        // at this point, no keyword is detected
        if(isVariableDeclaration(nextWord)){
            return new CmdVariable(myController, myParent);
        }
        if(isUserDefinedFunction(nextWord, myInterpreter)){
            return new CmdCommand(myController, myParent);
        }
        if(NumberFormatChecker.isDouble(nextWord)){
            return new CmdConstant(myController, myParent);
        }
        if(isStartOfComment(nextWord)){
            return new CmdComment(myController, myParent);
        }

        //TODO need to replace the fake checks for the type of command with the pattern syntax matches provided on the website:
        // http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands.php
        
        
        // at this point, we have no idea what the input is, we should simply throw a malformed code input error and let the gui
        // handle informing the user
        throw new UserInputException("Please check spelling of all Slogo commands");
    }

    public static boolean isUserDefinedFunction(String nextWord, ISlogoInterpreter myInterpreter){
        return (myInterpreter.getFunction(nextWord) != null);
    }

    public static boolean isVariableDeclaration(String nextWord){
        return nextWord.charAt(0) == VAR_FIRST_CHAR;
    }
    public static boolean isStartOfComment(String nextWord){
        return nextWord.charAt(0) == START_OF_COMMENT_CHAR; // || nextChar == '\n'; // We need to dynamically switch the behavior of the parser in the comment cmd class
    }
    
    protected boolean isNonZero(INonLinearCommand myCommand, ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException{
        double myValue = myCommand.executeCommand(myController, myInterpreter);
        return myValue != CommandTreeNode.DOUBLE_ZERO;
    }






}
