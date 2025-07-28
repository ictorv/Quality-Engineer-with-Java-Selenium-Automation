import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TicketBooking {
	public static WebDriver driver;
	String baseUrl = "https://webapps.tekstac.com/K-popconcert/index.html";
	
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public String bookTicket() throws Exception{
	    
	    //Read the values fron the XML file and set it to the webpage.
	    //Submit the form by clicking the Book button
	    // Return the result displayed after clicking the button using alert
		
		String []data=new String[8];
		File file=new File("PopConcert.xml");
		DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
		Document doc=dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		
		data[0]=doc.getElementsByTagName("ContactNumber").item(0).getTextContent();
		data[1]=doc.getElementsByTagName("EmailId").item(0).getTextContent();
		data[2]=doc.getElementsByTagName("City").item(0).getTextContent();
		data[3]=doc.getElementsByTagName("ConcertDate").item(0).getTextContent();
		data[4]=doc.getElementsByTagName("ConcertTime").item(0).getTextContent();
		data[5]=doc.getElementsByTagName("NumberOfTickets").item(0).getTextContent();
		data[6]=doc.getElementsByTagName("ConcertName").item(0).getTextContent();
		data[7]=doc.getElementsByTagName("Snacks").item(0).getTextContent();
		
		driver.findElement(By.id("contactNumber")).sendKeys(data[0]);
		driver.findElement(By.id("email")).sendKeys(data[1]);
		driver.findElement(By.id("city")).sendKeys(data[2]);
		driver.findElement(By.id("date")).sendKeys(data[3]);
		
		List<WebElement>radioOptions=driver.findElements(By.xpath("//input[@type='radio']"));
        for(WebElement ele:radioOptions){
            if(ele.getAttribute("value").equals(data[4])){
                ele.click();
                break;
            }
        }


        driver.findElement(By.id("ticket_count")).sendKeys(data[5]);
        
        WebElement concertDrp=driver.findElement(By.id("Concert_list"));
        Select concertOptions=new Select(concertDrp);
        concertOptions.selectByVisibleText(data[6]);
        
		List<WebElement>checkOptions=driver.findElements(By.xpath("//input[@type='checkbox']"));
        for(WebElement ele:checkOptions){
            if(ele.getAttribute("value").equals(data[7])){
                ele.click();
                break;
            }
        }
        
        driver.findElement(By.id("submit")).click();
        
        Alert alert=driver.switchTo().alert();
        String result=alert.getText();
        alert.accept();
        
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		
		TicketBooking book=new TicketBooking();
		book.createDriver();
		book.bookTicket();
	   
		}

}
