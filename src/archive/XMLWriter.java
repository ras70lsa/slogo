package archive;

import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import frontend.features.AlertMessage;

public class XMLWriter {
	
	private ResourceBundle myBundle;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private Element rootElement;
	
	public XMLWriter() {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			AlertMessage alert = new AlertMessage(e.getMessage());
			alert.displayError();
		}
	}

}
