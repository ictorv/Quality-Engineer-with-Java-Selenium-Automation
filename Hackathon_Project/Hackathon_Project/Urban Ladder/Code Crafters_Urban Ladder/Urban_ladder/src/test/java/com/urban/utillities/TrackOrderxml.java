package com.urban.utillities;
 
import java.io.File;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.Document;
 
public class TrackOrderxml  {
	static String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TrackOrder.xml";
	
 
	public static String[] getInput(){
		String[] value = new String[4];
		try {
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
			
			
			value[0] = doc.getElementsByTagName("OrderNo1").item(0).getTextContent();
			value[1] = doc.getElementsByTagName("PhoneNo1").item(0).getTextContent();
			value[2] = doc.getElementsByTagName("OrderNo2").item(0).getTextContent();
			value[3] = doc.getElementsByTagName("PhoneNo2").item(0).getTextContent();
			
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Data reading from json is unsuccessful");
		}
		return value;
		
	}
 
}