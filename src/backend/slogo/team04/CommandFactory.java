package backend.slogo.team04;

import java.util.function.Predicate;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActionsExtended;

public class CommandFactory {

    protected static INonLinearCommand recursiveSlogoFactoryNoListsAllowed(SlogoScanner myScanner
                                                                           , CommandTreeNode parentNode 
                                                                           , ISlogoInterpreterVariableScope myInterpreter) throws UserInputException{
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
                                                                                     , ISlogoInterpreterVariableScope myInterpreter) throws UserInputException{
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
                                                                            ,ISlogoInterpreterVariableScope myInterpreter
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
    protected static CmdVariable getVariableOrAssertError(String nextWord ,SlogoScanner myScanner, CommandTreeNode myParent, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException{
        return (CmdVariable) recursiveSlogoFactoryAssertCondition(nextWord
                                                                  ,myScanner
                                                                  , myParent
                                                                  , myInterpreter
                                                                  , s -> !SlogoRegexChecker.isVariable(s) //we only check string match because default return of 0 for first time
                                                                  , "Variable required at this point in parsing");
    }

    /**
     * This is used by the head node, in order to actually allow comments to return, so that we can avoid having parsing errors
     * where if you recursively try to parse the next node by throwing away the current one
     * @param nextWord
     * @param myScanner
     * @param myParent
     * @param myInterpreter
     * @return
     * @throws UserInputException
     */
    protected static INonLinearCommand topLevelCommandFactory(String nextWord, SlogoScanner myScanner, CommandTreeNode myParent, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException{
        if(SlogoRegexChecker.isStartOfComment(nextWord)){
            return new CmdComment(myParent).parseString(myScanner, myInterpreter);// if it is a comment, we should recurse again to properly feed children //actually need to 
            //return slogoCommandFactory(CommandTreeNode.getNextWord(myScanner), myScanner, myParent, myInterpreter);
        }
        return CommandFactory.slogoCommandFactory( nextWord, myScanner,  myParent,  myInterpreter).parseString(myScanner, myInterpreter);

    }

    protected static CommandTreeNode slogoCommandFactory(String nextWord, SlogoScanner myScanner, CommandTreeNode myParent, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException{
        if(SlogoRegexChecker.isStartOfComment(nextWord)){
            new CmdComment(myParent).parseString(myScanner, myInterpreter);// if it is a comment, we should recurse again to properly feed children //actually need to 
            return slogoCommandFactory(myScanner.getNextWord(), myScanner, myParent, myInterpreter);
        }
        return CommandFactory.checkCommandsExceptComment( nextWord,  myParent,  myInterpreter);

    }

    private static CommandTreeNode checkCommandsExceptComment(String nextWord, CommandTreeNode myParent, ISlogoInterpreterVariableScope myInterpreter) throws UserInputException{
        CommandTreeNode toReturn = CommandFactory.keyWordFunctions(nextWord, myParent);
        if(toReturn != null){
            return toReturn;
        }
        if(SlogoRegexChecker.isVariable(nextWord)){
            return new CmdVariable(myParent, nextWord);
        }
        if(CommandFactory.isUserDefinedFunction(nextWord, myInterpreter)){
            return ((CmdCommand) myInterpreter.getFunction(nextWord)).createClone(myParent); // this is erasing the stored information, need to recall the
            //function as stored and have parse a new version everytime that it needs to run
        }
        if(SlogoRegexChecker.isDouble(nextWord)){
            return new CmdConstant(myParent, Double.parseDouble(nextWord));
        }
        // at this point, we have no idea what the input is, we should simply throw a malformed code input error and let the gui
        // handle informing the user
        throw new UserInputException("Please check spelling of all Slogo commands");
    }

  

    private static CommandTreeNode keyWordFunctions (String nextWord, CommandTreeNode myParent) {
        switch(nextWord){
            // TURTLE COMMANDS
            case CmdForward.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdForward(myParent));
            case CmdBack.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdBack(myParent));
            case CmdLeft.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdLeft(myParent));
            case CmdRight.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdRight(myParent));
            case CmdSetHeading.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdSetHeading(myParent));
            case CmdTowards.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdTowards(myParent));
            case CmdSetXY.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdSetXY(myParent));
            case CmdPenDown.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdPenDown(myParent));
            case CmdPenUp.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdPenUp(myParent));
            case CmdShowTurtle.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdShowTurtle(myParent));
            case CmdHideTurtle.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdHideTurtle(myParent));
            case CmdHome.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdHome(myParent));
            case CmdClearScreen.MY_KEY:
                return new CmdClearScreen(myParent);
                // TURTLE QUERIES
            case CmdXCor.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdXCor(myParent));
            case CmdYCor.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdYCor(myParent));
            case CmdHeading.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdHeading(myParent));
            case CmdIsPenDown.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdIsPenDown(myParent));
            case CmdIsShowing.MY_KEY:
                return runCommandOnActiveTurtles(myParent, new CmdIsShowing(myParent));
                // MATH OPERATIONS
            case CmdSum.MY_KEY:
                return new CmdSum(myParent);
            case CmdDifference.MY_KEY:
                return new CmdDifference(myParent);
            case CmdProduct.MY_KEY:
                return new CmdProduct(myParent);
            case CmdQuotient.MY_KEY:
                return new CmdQuotient(myParent);
            case CmdRemainder.MY_KEY:
                return new CmdRemainder(myParent);
            case CmdMinus.MY_KEY:
                return new CmdMinus(myParent);
            case CmdRandom.MY_KEY:
                return new CmdRandom(myParent);
            case CmdSin.MY_KEY:
                return new CmdSin(myParent);
            case CmdCos.MY_KEY:
                return new CmdCos(myParent);
            case CmdTan.MY_KEY:
                return new CmdTan(myParent);
            case CmdAtan.MY_KEY:
                return new CmdAtan(myParent);
            case CmdLog.MY_KEY:
                return new CmdLog(myParent);
            case CmdPow.MY_KEY:
                return new CmdPow(myParent);
            case CmdPi.MY_KEY:
                return new CmdPi(myParent);
                //BOOLEAN OPERATIONS
            case CmdLess.MY_KEY:
                return new CmdLess(myParent);
            case CmdGreater.MY_KEY:
                return new CmdGreater(myParent);
            case CmdEqual.MY_KEY:
                return new CmdEqual(myParent);
            case CmdNotEqual.MY_KEY:
                return new CmdNotEqual(myParent);
            case CmdAnd.MY_KEY:
                return new CmdAnd(myParent);
            case CmdOr.MY_KEY:
                return new CmdOr(myParent);
            case CmdNot.MY_KEY:
                return new CmdNot(myParent);
                // Variables, Control, and User-Defined Commands
            case CmdMake.MY_KEY:
                return new CmdMake(myParent);
            case CmdRepeat.MY_KEY:
                return new CmdRepeat(myParent);
            case CmdDoTimes.MY_KEY:
                return new CmdDoTimes(myParent);
            case CmdFor.MY_KEY:
                return new CmdFor(myParent);
            case CmdIf.MY_KEY:
                return new CmdIf(myParent);
            case CmdIfElse.MY_KEY:
                return new CmdIfElse(myParent);
            case CmdTo.MY_KEY: //logic to prevent collision of existing commands will belong to the cmdTo class itself
                return new CmdTo(myParent);
                //we are now implementing the extension commands
                //display commands
            case CmdSetBackground.MY_KEY:
                return new CmdSetBackground(myParent);
            case CmdSetPenColor.MY_KEY:
                return new CmdSetPenColor(myParent);
            case CmdSetPenSize.MY_KEY:
                return new CmdSetPenSize(myParent);
            case CmdSetShape.MY_KEY:
                return new CmdSetShape(myParent);
            case CmdSetPalette.MY_KEY:
                return new CmdSetPalette(myParent);
            case CmdPenColor.MY_KEY:
                return new CmdPenColor(myParent);
            case CmdShape.MY_KEY:
                return new CmdShape(myParent);
            case CmdStamp.MY_KEY:
                return new CmdStamp(myParent);
            case CmdClearStamps.MY_KEY:
                return new CmdClearStamps(myParent);
                //Multiple turtle commands
            case CmdID.MY_KEY:
                return new CmdID(myParent);
            case CmdTurtles.MY_KEY:
                return new CmdTurtles(myParent);
            case CmdTell.MY_KEY:
                return new CmdTell(myParent);
            case CmdAsk.MY_KEY:
                return new CmdAsk(myParent);
            case CmdAskWith.MY_KEY:
                return new CmdAskWith(myParent);
            case CmdUnlimitedParameter.MY_KEY:
                return new CmdUnlimitedParameter(myParent);
            default:
                return null;
        }
    }
    
    private static CommandTreeNode runCommandOnActiveTurtles(CommandTreeNode myParent, CommandTreeNode originalChild){
        CmdExecuteIfCurrentTurtleActive myTurtleActiveChecker = new CmdExecuteIfCurrentTurtleActive(myParent);
        CommandTreeNode myIterator = new CmdIDIterator(myParent, myTurtleActiveChecker, originalChild);
        myTurtleActiveChecker.setMyParent(myIterator);
        originalChild.setMyParent(myIterator); // so that current active turtle id references are passed properly
        return myIterator;
    }

    protected static boolean isNonZero(INonLinearCommand myCommand, ISlogoModelActionsExtended myController, ISlogoInterpreterVariableScope myInterpreter, ISlogoDebugObject debugMe) throws LogicException{
        double myValue = myCommand.executeCommand(myController, myInterpreter, debugMe);
        return myValue != CommandTreeNode.DOUBLE_ZERO;
    }

    protected static boolean isUserDefinedFunction(String nextWord, ISlogoInterpreterVariableScope myInterpreter){
        return (myInterpreter.getFunction(nextWord) != null);
    }
    
    protected static boolean isKeyWord(String nextWord){
        return CommandFactory.keyWordFunctions(nextWord, null) != null;
    }
    
    
    protected static boolean isBreakable(String nextWord, ISlogoInterpreterVariableScope myInterpreter){
        return isUserDefinedFunction(nextWord,  myInterpreter) || isKeyWord(nextWord);
    }
    

}
