package com.urban.utillities;
 
import java.io.File;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.Document;
 
public class Beingxml  {
	static String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\BeingAtHome.xml";
	
 
	public static String getInput(){
		String Search = "";
		try {
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
			
			
			Search = doc.getElementsByTagName("search").item(0).getTextContent();
			
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Data reading from json is unsuccessful");
		}
		return Search;
		
	}
 
}
 