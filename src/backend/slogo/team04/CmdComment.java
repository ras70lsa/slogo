package backend.slogo.team04;

import java.util.regex.Pattern;
import exceptions.LogicException;
import exceptions.UserInputException;
import interfaces.slogo.team04.ISlogoModelActions;

/**
 * Will just eat things until it sees a new line
 * @author jonathanim
 *
 */
public class CmdComment extends CommandTreeNode {
    public CmdComment (CommandTreeNode myParent) {
        super(myParent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double executeCommand (ISlogoModelActions myController, ISlogoInterpreter myInterpreter) throws LogicException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public INonLinearCommand parseString (SlogoScanner myScanner, ISlogoInterpreter myInterpreter) throws UserInputException {
        Pattern cached = myScanner.delimiter();
        myScanner.useDelimiter(SlogoScanner.ALL_NON_NEW_LINE_REGEX);
        myScanner.getNextWord(); //eat the white space till new line
        myScanner.useDelimiter(cached); 
        return this; 
    }

}
