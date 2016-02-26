package backend_slogo_team04;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import exceptions.StructuralException;
import model.Controller;


/**
 * This class forms the basis for how we are going to parse the input text, each word allowable in the language will expects certain
 * types of words to follow, each subsequent word suggesting its own logic
 * 
 * Thus we are using recursion, and passing the scanner object containing the text to be parsed around in order to 
 * @author jonathanim
 *
 */
public abstract class CommandTreeNode implements INonLinearCommand {
    private List<CommandTreeNode> myChildren;
    private CommandTreeNode myParent; //in case we need to do weird scope things in the future





    public CommandTreeNode(Controller myController, CommandTreeNode myParent){
        this.myParent = myParent;
        myChildren = new ArrayList<CommandTreeNode>();
    }

    protected void addChildNode(CommandTreeNode nodeToAdd){
        if(nodeToAdd != null){
            this.myChildren.add(nodeToAdd);
        }
    }


    public static CommandTreeNode recursiveSlogoFactory(Scanner myScanner, CommandTreeNode parentNode , Controller myController) throws StructuralException{
        // throw an error here regarding incomplete syntax
        String lowerCaseWord;
        try{
            lowerCaseWord = myScanner.next().toLowerCase();
        }catch(NoSuchElementException e){
            throw new StructuralException("Incomplete Slogo commands detected");
        }

        CommandTreeNode myNextCommand = CommandTreeNode.slogoCommandFactory(lowerCaseWord, parentNode, myController);
        myNextCommand.parseString(myScanner);

        return myNextCommand;
    }

    public static CommandTreeNode slogoCommandFactory(String nextWord, CommandTreeNode myParent, Controller myController){
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
        // need to check if it is a variable
        // need to check if it is a userdefined function
        // need to check if it is a constant
        // need to check if the line is a comment, if create commentCommand which then will eat all symbols untill we hit the nextline character
        
        return null;
    }




}
