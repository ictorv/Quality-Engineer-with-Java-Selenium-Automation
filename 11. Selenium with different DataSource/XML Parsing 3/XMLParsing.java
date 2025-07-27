import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLParsing {
    public static WebDriver driver;
    public static XPath xPath;
    public static Document doc;
    public static Element eElement;
    static String baseUrl = "https://webapps.tekstac.com/Users/";

    public static WebDriver createDriver() {
        //Implement code to create Driver from DriverSetup, set to 'static' driver variable and return it
        driver=DriverSetup.getDriver();
        driver.get(baseUrl);
        return driver;
    }

	public XPath readFile(String fileName) throws Exception{
        //Implement code to read and return XPath object reference
        File file=new File(fileName);
        DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dFactory.newDocumentBuilder();
        doc=dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        
        XPathFactory xPathFactory=XPathFactory.newInstance();
        xPath=xPathFactory.newXPath();
        return xPath;
    }

    public String getUserName(int id) throws Exception{
        //Implement code to return From from xml 
        String expression=String.format("/UserDetails/User[%d]/Name",id);
        return xPath.evaluate(expression,doc);
        // return null; 
    }

    public String getAddress(int id) throws Exception{
        //Implement code to return From from xml 
        String expression=String.format("/UserDetails/User[%d]/Address",id);
        return xPath.evaluate(expression,doc);
    }

    public String getMobileNumber(int id) throws Exception{
        //Implement code to return From from xml
        String expression=String.format("/UserDetails/User[%d]/MobileNo",id);
        return xPath.evaluate(expression,doc);

    }

    public String getEmail(int id) throws Exception{
        //Implement code to return From from xml 
        String expression=String.format("/UserDetails/User[%d]/Email",id);
        return xPath.evaluate(expression,doc);
    }

    public String submitForm() throws Exception{
    	// Implement code to submit form with values got from xml
		// Call the get methods and pass the 'index' to retrieve the xml values.
		// For ex - driver.findElement(By.id("name")).sendKeys(getUserName(1));
		
		// Submit the form
    	driver.findElement(By.id("name")).sendKeys(getUserName(1));
    	driver.findElement(By.id("address")).sendKeys(getAddress(1));
    	driver.findElement(By.id("mobile")).sendKeys(getMobileNumber(1));
    	driver.findElement(By.id("email")).sendKeys(getEmail(1));
    	driver.findElement(By.id("submit")).click();
        
        WebElement result=driver.findElement(By.id("result"));
        return result.getText();
    }

    public static void main(String[] args) throws Exception{
        XMLParsing pageLocator = new XMLParsing();
        //Call the required methods from the main
	    //Pass the file name as "UserDetails.xml"
	    //Pass the id value as '1'
	    pageLocator.createDriver();
	    pageLocator.readFile("UserDetails.xml");
	    String result=pageLocator.submitForm();
	    System.out.println(result);
    }
}
