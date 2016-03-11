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

import exceptions.ImproperFileException;
import model.Controller;
import model.ExecutionState;




public class XMLReader {

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
		
		//Enter code here
		
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
