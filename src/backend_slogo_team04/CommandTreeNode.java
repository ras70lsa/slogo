package backend_slogo_team04;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;
import exceptions.LogicException;
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

    protected static final double DOUBLE_ZERO = 0d;
    protected static final double DOUBLE_ONE = 1d;


    private List<CommandTreeNode> myChildren;


    private CommandTreeNode myParent; //in case we need to do weird scope things in the future
    
    protected CommandTreeNode getMyParent(){
        return this.myParent;
    }




    public CommandTreeNode(CommandTreeNode myParent){
        this.myParent = myParent;
        myChildren = new ArrayList<CommandTreeNode>();
    }

    protected void addChildNode(CommandTreeNode nodeToAdd){
        if(nodeToAdd != null){
            this.myChildren.add(nodeToAdd);
        }
    }

    //TODO these commands below should be moved into their own separate factory class, this is mixing the node state logic with construction logic

    protected static INonLinearCommand recursiveSlogoFactoryNoListsAllowed(Scanner myScanner
                                                                           , CommandTreeNode parentNode 
                                                                           , ISlogoInterpreter myInterpreter) throws UserInputException{
        return recursiveSlogoFactoryAssertCondition(CommandTreeNode.getNextWord(myScanner)
                                                    , myScanner
                                                    , parentNode
                                                    , myInterpreter
                                                    , s -> SlogoRegexChecker.isStartOfList(s)
                                                    , "List declaration when not expected");

    }

    protected static INonLinearCommand recursiveSlogoFactoryNoListsControlledAdvance(String myWord
                                                                                     ,Scanner myScanner
                                                                                     , CommandTreeNode parentNode 
                                                                                     , ISlogoInterpreter myInterpreter) throws UserInputException{
        return recursiveSlogoFactoryAssertCondition(myWord
                                                    , myScanner
                                                    , parentNode
                                                    , myInterpreter
                                                    , s -> SlogoRegexChecker.isStartOfList(s)
                                                    , "List declaration when not expected");

    }

    protected static INonLinearCommand recursiveSlogoFactoryListsAllowed(Scanner myScanner
                                                                         , CommandTreeNode parentNode
                                                                         , ISlogoInterpreter myInterpreter) throws UserInputException{
        return CommandTreeNode
                .slogoCommandFactory(getNextWord(myScanner), myScanner, parentNode, myInterpreter)
                .parseString(myScanner, myInterpreter);
    }


    protected static INonLinearCommand recursiveSlogoFactoryAssertCondition(String nextWord 
                                                                            ,Scanner myScanner
                                                                            , CommandTreeNode parentNode
                                                                            ,ISlogoInterpreter myInterpreter
                                                                            , Predicate<String> myTestCase
                                                                            , String errorMessage) throws UserInputException{

        if(myTestCase.test(nextWord)){
            throw new UserInputException(errorMessage);
        }
        return CommandTreeNode.slogoCommandFactory(nextWord, myScanner, parentNode, myInterpreter).parseString(myScanner, myInterpreter);

    }

    /**
     * Used by methods to ensure that the next returned slogo command is in fact a variable
     * @param nextWord
     * @param myParent
     * @param myInterpreter
     * @return
     * @throws UserInputException 
     */
    public static CmdVariable getVariableOrAssertError(String nextWord ,Scanner myScanner, CommandTreeNode myParent, ISlogoInterpreter myInterpreter) throws UserInputException{
        return (CmdVariable) recursiveSlogoFactoryAssertCondition(nextWord
                                                                  ,myScanner
                                                                  , myParent
                                                                  , myInterpreter
                                                                  , s -> !SlogoRegexChecker.isVariable(s) //we only check string match because default return of 0 for first time
                                                                  , "Variable required at this point in parsing");
    }




    protected static String getNextWord(Scanner myScanner) throws UserInputException{
        String myWord;
        try{
            myWord = myScanner.next();
        }catch(NoSuchElementException e){
            throw new UserInputException("Incomplete Slogo commands detected"); //TODO make this use resource bundles later
        }
        return myWord;
    }


    protected static CommandTreeNode slogoCommandFactory(String nextWord, Scanner myScanner, CommandTreeNode myParent, ISlogoInterpreter myInterpreter) throws UserInputException{
        // we can assume at this point that the only things that are coming are the words themselves and new line characters
        // need to fix this to include the required inputs for hte different classes, and then also
        // include the logic for choosing to instantiate the headnode
        // include the required list node subtype construction
        CommandTreeNode toReturn = keyWordFunctions(nextWord, myParent);
        if(toReturn != null){
            return toReturn;
        }
        // at this point, no keyword is detected
        if(SlogoRegexChecker.isVariable(nextWord)){
            return new CmdVariable(myParent, nextWord);
        }
        if(isUserDefinedFunction(nextWord, myInterpreter)){
            return ((CmdCommand) myInterpreter.getFunction(nextWord)).createClone(); // this is erasing the stored information, need to recall the
            //function as stored and have parse a new version everytime that it needs to run
        }
        if(SlogoRegexChecker.isDouble(nextWord)){
            return new CmdConstant(myParent, Double.parseDouble(nextWord));
        }
        if(SlogoRegexChecker.isStartOfComment(nextWord)){
            new CmdComment(myParent).parseString(myScanner, myInterpreter);// if it is a comment, we should recurse again to properly feed children
            return slogoCommandFactory(CommandTreeNode.getNextWord(myScanner), myScanner, myParent, myInterpreter);
        }

        //TODO need to replace the fake checks for the type of command with the pattern syntax matches provided on the website:
        // http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands.php


        // at this point, we have no idea what the input is, we should simply throw a malformed code input error and let the gui
        // handle informing the user
        throw new UserInputException("Please check spelling of all Slogo commands");
    }

    protected static boolean isKeyWord(String nextWord){
        return keyWordFunctions(nextWord, null) != null;
    }

    private static CommandTreeNode keyWordFunctions (String nextWord, CommandTreeNode myParent) {
        switch(nextWord){
            // TURTLE COMMANDS
            case "Forward":
                return new CmdForward(myParent);
            case "Backward":
                return new CmdBack(myParent);
            case "Left":
                return new CmdLeft(myParent);
            case "Right":
                return new CmdRight(myParent);
            case "SetHeading":
                return new CmdSetHeading(myParent);
            case "SetTowards":
                return new CmdTowards(myParent);
            case "SetPosition":
                return new CmdSetXY(myParent);
            case "PenDown":
                return new CmdPenDown(myParent);
            case "PenUp":
                return new CmdPenUp(myParent);
            case "ShowTurtle":
                return new CmdShowTurtle(myParent);
            case "HideTurtle":
                return new CmdHideTurtle(myParent);
            case "Home":
                return new CmdHome(myParent);
            case "ClearScreen":
                return new CmdClearScreen(myParent);
                // TURTLE QUERIES
            case "XCoordinate":
                return new CmdXCor(myParent);
            case "YCoordinate":
                return new CmdYCor(myParent);
            case "Heading":
                return new CmdHeading(myParent);
            case "IsPenDown":
                return new CmdIsPenDown(myParent);
            case "IsShowing":
                return new CmdIsShowing(myParent);
                // MATH OPERATIONS
            case "Sum":
                return new CmdSum(myParent);
            case "Difference":
                return new CmdDifference(myParent);
            case "Product":
                return new CmdProduct(myParent);
            case "Quotient":
                return new CmdQuotient(myParent);
            case "Remainder":
                return new CmdRemainder(myParent);
            case "Minus":
                return new CmdMinus(myParent);
            case "Random":
                return new CmdRandom(myParent);
            case "Sine":
                return new CmdSin(myParent);
            case "Cosine":
                return new CmdCos(myParent);
            case "Tangent":
                return new CmdTan(myParent);
            case "ArcTangent":
                return new CmdAtan(myParent);
            case "NaturalLog":
                return new CmdLog(myParent);
            case "Power":
                return new CmdPow(myParent);
            case "Pi":
                return new CmdPi(myParent);
                //BOOLEAN OPERATIONS
            case "LessThan":
                return new CmdLess(myParent);
            case "GreaterThan":
                return new CmdGreater(myParent);
            case "Equal":
                return new CmdEqual(myParent);
            case "NotEqual":
                return new CmdNotEqual(myParent);
            case "And":
                return new CmdAnd(myParent);
            case "Or":
                return new CmdOr(myParent);
            case "Not":
                return new CmdNot(myParent);
                // Variables, Control, and User-Defined Commands
            case "MakeVariable":
                return new CmdMake(myParent);
            case "Repeat":
                return new CmdRepeat(myParent);
            case "DoTimes":
                return new CmdDoTimes(myParent);
            case "For":
                return new CmdFor(myParent);
            case "If":
                return new CmdIf(myParent);
            case "IfElse":
                return new CmdIfElse(myParent);
            case "MakeUserInstruction": //logic to prevent collision of existing commands will belong to the cmdTo class itself
                return new CmdTo(myParent);
            default:
                return null;
        }
    }





    //parsing checks that are dependent upon state will remain here
    protected static boolean isNonZero(INonLinearCommand myCommand, ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException{
        double myValue = myCommand.executeCommand(myController, myInterpreter);
        return myValue != CommandTreeNode.DOUBLE_ZERO;
    }

    public static boolean isUserDefinedFunction(String nextWord, ISlogoInterpreter myInterpreter){
        return (myInterpreter.getFunction(nextWord) != null);
    }





}
