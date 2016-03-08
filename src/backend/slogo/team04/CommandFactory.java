package backend.slogo.team04;

import java.util.function.Predicate;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class CommandFactory {

    protected static INonLinearCommand recursiveSlogoFactoryNoListsAllowed(SlogoScanner myScanner
                                                                           , CommandTreeNode parentNode 
                                                                           , ISlogoInterpreter myInterpreter) throws UserInputException{
        return CommandFactory.recursiveSlogoFactoryAssertCondition(myScanner.getNextWord()
                                                                   , myScanner
                                                                   , parentNode
                                                                   , myInterpreter
                                                                   , s -> SlogoRegexChecker.isStartOfList(s)
                                                                   , "List declaration when not expected");

    }

    protected static INonLinearCommand recursiveSlogoFactoryNoListsControlledAdvance(String myWord
                                                                                     ,SlogoScanner myScanner
                                                                                     , CommandTreeNode parentNode 
                                                                                     , ISlogoInterpreter myInterpreter) throws UserInputException{
        return CommandFactory.recursiveSlogoFactoryAssertCondition(myWord
                                                                   , myScanner
                                                                   , parentNode
                                                                   , myInterpreter
                                                                   , s -> SlogoRegexChecker.isStartOfList(s)
                                                                   , "List declaration when not expected");

    }

    protected static INonLinearCommand recursiveSlogoFactoryAssertCondition(String nextWord 
                                                                            ,SlogoScanner myScanner
                                                                            , CommandTreeNode parentNode
                                                                            ,ISlogoInterpreter myInterpreter
                                                                            , Predicate<String> myTestCase
                                                                            , String errorMessage) throws UserInputException{
        String curWord = nextWord;
        curWord = myScanner.advanceScannerPastComments(curWord, myInterpreter);
        if(myTestCase.test(curWord)){
            throw new UserInputException(errorMessage);
        }
        return CommandFactory.slogoCommandFactory(curWord, myScanner, parentNode, myInterpreter).parseString(myScanner, myInterpreter);

    }

    /**
     * Used by methods to ensure that the next returned slogo command is in fact a variable
     * @param nextWord
     * @param myParent
     * @param myInterpreter
     * @return
     * @throws UserInputException 
     */
    protected static CmdVariable getVariableOrAssertError(String nextWord ,SlogoScanner myScanner, CommandTreeNode myParent, ISlogoInterpreter myInterpreter) throws UserInputException{
        return (CmdVariable) recursiveSlogoFactoryAssertCondition(nextWord
                                                                  ,myScanner
                                                                  , myParent
                                                                  , myInterpreter
                                                                  , s -> !SlogoRegexChecker.isVariable(s) //we only check string match because default return of 0 for first time
                                                                  , "Variable required at this point in parsing");
    }

    /**
     * This is used by the head node, in order to actually allow comments to return, so that we can avoid having parsing errors
     * @param nextWord
     * @param myScanner
     * @param myParent
     * @param myInterpreter
     * @return
     * @throws UserInputException
     */
    protected static INonLinearCommand topLevelCommandFactory(String nextWord, SlogoScanner myScanner, CommandTreeNode myParent, ISlogoInterpreter myInterpreter) throws UserInputException{
        if(SlogoRegexChecker.isStartOfComment(nextWord)){
            return new CmdComment(myParent).parseString(myScanner, myInterpreter);// if it is a comment, we should recurse again to properly feed children //actually need to 
            //return slogoCommandFactory(CommandTreeNode.getNextWord(myScanner), myScanner, myParent, myInterpreter);
        }
        return CommandFactory.slogoCommandFactory( nextWord, myScanner,  myParent,  myInterpreter).parseString(myScanner, myInterpreter);

    }

    protected static CommandTreeNode slogoCommandFactory(String nextWord, SlogoScanner myScanner, CommandTreeNode myParent, ISlogoInterpreter myInterpreter) throws UserInputException{
        if(SlogoRegexChecker.isStartOfComment(nextWord)){
            new CmdComment(myParent).parseString(myScanner, myInterpreter);// if it is a comment, we should recurse again to properly feed children //actually need to 
            return slogoCommandFactory(myScanner.getNextWord(), myScanner, myParent, myInterpreter);
        }
        return CommandFactory.checkCommandsExceptComment( nextWord,  myParent,  myInterpreter);

    }

    private static CommandTreeNode checkCommandsExceptComment(String nextWord, CommandTreeNode myParent, ISlogoInterpreter myInterpreter) throws UserInputException{
        CommandTreeNode toReturn = CommandFactory.keyWordFunctions(nextWord, myParent);
        if(toReturn != null){
            return toReturn;
        }
        // at this point, no keyword is detected
        if(SlogoRegexChecker.isVariable(nextWord)){
            return new CmdVariable(myParent, nextWord);
        }
        if(CommandFactory.isUserDefinedFunction(nextWord, myInterpreter)){
            return ((CmdCommand) myInterpreter.getFunction(nextWord)).createClone(); // this is erasing the stored information, need to recall the
            //function as stored and have parse a new version everytime that it needs to run
        }
        if(SlogoRegexChecker.isDouble(nextWord)){
            return new CmdConstant(myParent, Double.parseDouble(nextWord));
        }
        // at this point, we have no idea what the input is, we should simply throw a malformed code input error and let the gui
        // handle informing the user
        throw new UserInputException("Please check spelling of all Slogo commands");
    }

    protected static boolean isKeyWord(String nextWord){
        return CommandFactory.keyWordFunctions(nextWord, null) != null;
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
    protected static boolean isNonZero(INonLinearCommand myCommand, ISlogoModelActionsExtended myController, ISlogoInterpreter myInterpreter) throws LogicException{
        double myValue = myCommand.executeCommand(myController, myInterpreter);
        return myValue != CommandTreeNode.DOUBLE_ZERO;
    }

    protected static boolean isUserDefinedFunction(String nextWord, ISlogoInterpreter myInterpreter){
        return (myInterpreter.getFunction(nextWord) != null);
    }

}
