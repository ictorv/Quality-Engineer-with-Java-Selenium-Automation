import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FlightReservation {
	
	public static WebDriver driver;
	String baseUrl = "https://webapps.tekstac.com/selenium/FlightReservation/index.html";
	
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public String getReservationStatus() throws ParserConfigurationException,SAXException,IOException{
	    //Read the values fron the XML file and set it to the webpage.
	    //Submit the form by clicking the Book Ticket button
	    // Return the result displayed after clicking tthe button
	    File file =new File("FlightReservation.xml");
	    DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder =dbFactory.newDocumentBuilder();
	    Document doc=dBuilder.parse(file);
	    doc.getDocumentElement().normalize();
	    
	    String[] data=new String[5];
	    data[0]= doc.getElementsByTagName("Name").item(0).getTextContent();
	    data[1]= doc.getElementsByTagName("PhoneNumber").item(0).getTextContent();
	    data[2]= doc.getElementsByTagName("NumberOfPersons").item(0).getTextContent();
	    data[3]= doc.getElementsByTagName("DepartureCity").item(0).getTextContent();
	    data[4]= doc.getElementsByTagName("DestinationCity").item(0).getTextContent();
	    
	    driver.findElement(By.id("name")).sendKeys(data[0]);
	    driver.findElement(By.id("phonenumber")).sendKeys(data[1]);
	    driver.findElement(By.id("tickets")).sendKeys(data[2]);
	    
	    WebElement depDropDown=driver.findElement(By.id("departureCity"));
	    WebElement destDropDown=driver.findElement(By.id("destinationCity"));
	    
	    Select departure=new Select(depDropDown);
	    departure.selectByVisibleText(data[3]);
	    
	    Select destination=new Select(destDropDown);
	    destination.selectByVisibleText(data[4]);
    
	    driver.findElement(By.id("submit")).click();
	    
	    String result=driver.findElement(By.id("result")).getText();
	    
	    return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		FlightReservation res=new FlightReservation();
		res.createDriver();
		System.out.println(res.getReservationStatus());
	   
		}
}
