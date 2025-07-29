package utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XMLUtilities {
	
	
	Document doc;
	
	public String[] getXMLData(String filePath) throws Exception{
		File file = new File(filePath);
		DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
		doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		
		String[] data = new String[9];
		
		data[0] = doc.getElementsByTagName("Price").item(0).getTextContent();
		data[1] = doc.getElementsByTagName("DownPayment").item(0).getTextContent();
		data[2] = doc.getElementsByTagName("Insurance").item(0).getTextContent();
		data[3] = doc.getElementsByTagName("Interest").item(0).getTextContent();
		data[4] = doc.getElementsByTagName("LoanFees").item(0).getTextContent();
		data[5] = doc.getElementsByTagName("StartDate").item(0).getTextContent();
		data[6] = doc.getElementsByTagName("HomeInsurance").item(0).getTextContent();
		data[7] = doc.getElementsByTagName("Maintenance").item(0).getTextContent();
		data[8] = doc.getElementsByTagName("ConfirmMsg").item(0).getTextContent();
		
		
		return data;
		
	}
	
}
