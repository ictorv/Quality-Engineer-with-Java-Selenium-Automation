package com.selenium.setup;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {      //Do not change the class name
    
    static String[] xmlData;
    
	public static String[] readFile(String fileName) throws Exception { // Do not change the method signature
	
		//Using the file name passed to this method, parse the XML file, read the XML data and store it in a string array.
		//Store XML values in the string array. 
		//xmlData[0]  is 'NickName' read from xml file
		//xmlData[1]  is 'ContactName' read from xml file  
		//xmlData[2]  is 'Company' read from xml file
		//xmlData[3]  is 'City' read from xml file
		//xmlData[4]  is 'Country' read from xml file
		//xmlData[5]  is 'Type' read from xml file  		
		//Return the array. 
		File file =new File(fileName);
		DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder =dbFactory.newDocumentBuilder();
		Document doc=dBuilder.parse(file);
	
	    String[] xmlData=new String[6];
	    xmlData[0] =doc.getElementsByTagName("NickName").item(0).getTextContent();
        xmlData[1] =doc.getElementsByTagName("ContactName").item(0).getTextContent();
        xmlData[2] =doc.getElementsByTagName("Company").item(0).getTextContent();
        xmlData[3] =doc.getElementsByTagName("City").item(0).getTextContent();
        xmlData[4] =doc.getElementsByTagName("Country").item(0).getTextContent();
        xmlData[5] =doc.getElementsByTagName("Type").item(0).getTextContent();
       
       return xmlData;
	}
	
}
	
