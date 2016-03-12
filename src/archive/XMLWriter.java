package archive;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import backend.slogo.team04.INonLinearCommand;
import constants.DisplayConstants;
import constants.ResourceConstants;
import frontend.features.AlertMessage;

public class XMLWriter {
	
	public static final String MAC_EXT = "/command_scripts/";	
	public static final String WINDOW_EXT = "\\command_scripts\\";
	public static final String MAC = "Mac";
	public static final String USER_DIR = "user.dir";
	public static final String XML = "xml";
	
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private ResourceBundle myBundle;
	private Element mainElement;
	
	public XMLWriter() {
		myBundle = ResourceBundle.getBundle(DisplayConstants.RESOURCES_PATH + ResourceConstants.ENGLISH);
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			AlertMessage alert = new AlertMessage(e.getMessage());
			alert.displayError();
		}
	}

	public void save(String title, List<String> commandNames, List<INonLinearCommand> commandNodes) {
		createDocument(title);
		addContent(commandNames, commandNodes);
		writeDocument(getPath(title));
	}

	private void addContent(List<String> commandNames, List<INonLinearCommand> commandNodes) {
		
		for(int i = 0; i<commandNames.size(); i++) {
			Element name = document.createElement("Element" + i);
			String nodeToText = commandNodes.get(i).parsableRepresentation();
			name.setTextContent(nodeToText);
			mainElement.appendChild(name);
		}
		
	}

	private void createDocument(String title) {
		document = documentBuilder.newDocument();
		mainElement = document.createElement(myBundle.getString("Info"));
		Attr attr = document.createAttribute(myBundle.getString("Title"));
		attr.setValue(title);
		mainElement.setAttributeNode(attr);
		document.appendChild(mainElement);
	}
	
	/**
	 * Have to get the different path
	 * Check based on different operating system
	 * @param name
	 * @return
	 */
	private String getPath(String name) {
		String operatingSystem = (System.getProperty("os.name"));
		if(operatingSystem.contains(MAC)) {
			return getSavePath(MAC, name);
		} else {
			return getSavePath(WINDOW_EXT, name);
		}
	}

	private String getSavePath(String input, String name) {
		return System.getProperty(USER_DIR)+  input + name + XML;
	}

	private void writeDocument(String path) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			File file= new File(path);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (Exception e) {
			AlertMessage alert = new AlertMessage(myBundle.getString("MisSave"));
			alert.displayError();
		}
	}

}
