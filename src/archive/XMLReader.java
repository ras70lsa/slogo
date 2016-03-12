package archive;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import backend.slogo.team04.CmdTreeHeadNode;
import backend.slogo.team04.ExecutionModeNormal;
import backend.slogo.team04.INonLinearCommand;
import backend.slogo.team04.SlogoScanner;
import exceptions.ImproperFileException;
import exceptions.UserInputException;
import frontend.features.AlertMessage;
import model.Controller;
import model.ExecutionState;




public class XMLReader {
	
	public static final String COMMAND_INSTRUCTION = "MakeUserInstruction ";

	public static final String MESSAGE = "Improper file type";
	public static final String XML  = "xml";
	
	/*Data structures necessary for parsing the file*/
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private Element mainElement;
	
	

	/*Creates the XMLReader; creates the DocumentBuilderFactory and the ResourceBundle*/
	public XMLReader() {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
	}
	
	public void execute(File file, ExecutionState executionState) throws ParserConfigurationException, 
											SAXException, IOException, ImproperFileException {
		checkFile(file);
		
		documentBuilder= documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(file);
		int counter = 0;
		while(true) {
			Element element = getElement("Element" + counter);
			if(element == null) {
				break;
			}
			String toAdd = element.getTextContent();
			giveUserInfo(toAdd, executionState);
			counter++;
		}
	}
	
	private Element getElement(String key) {
		return (Element)  document.getElementsByTagName(key).item(0);
	}
	
	
	
	private void giveUserInfo(String toAdd, ExecutionState executionState) {
		
		SlogoScanner scanner = new SlogoScanner(COMMAND_INSTRUCTION + toAdd);
		INonLinearCommand myHead;
		try {
			myHead = new CmdTreeHeadNode(null).parseString(scanner, executionState);
			myHead.executeCommand(null, executionState, new ExecutionModeNormal());
		} catch (Exception e) {
			AlertMessage alert = new AlertMessage(e.getMessage());
			alert.displayError();
		}


	}

	private void checkFile(File file) throws ImproperFileException {
		String extension = getExtension(file.toString());
		if(!extension.equals(XML) || extension.equals(null)) {
			throw new ImproperFileException(MESSAGE);
		}
	}
	
	private String getExtension(String file) {
		
		int index = file.lastIndexOf('.');
		if(index>-1) {
			return file.substring(index+1);
		}
		return null;
	}

}
