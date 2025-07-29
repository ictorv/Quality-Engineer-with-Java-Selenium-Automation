package utilities;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
public class XMLUtil {
	
	public String[][] TC010XML() throws ParserConfigurationException, SAXException, IOException{
		
		        File xmlFile = new File(".\\testData\\Identify-Car-Wash-Services_TestDataXML.xml");

		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        Document doc = builder.parse(xmlFile);

		        doc.getDocumentElement().normalize();

		        Element service = (Element) doc.getElementsByTagName("Service").item(0);
		        
		        String data[][] = new String [1][3];
		        data[0][0] =  service.getElementsByTagName("Type").item(0).getTextContent();
		        data[0][1] = service.getElementsByTagName("MobileNumber").item(0).getTextContent();
		        data[0][2]= service.getElementsByTagName("Plan").item(0).getTextContent();

		        return data;
	}

}
