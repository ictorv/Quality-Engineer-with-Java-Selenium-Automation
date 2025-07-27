import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Locator {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables 
	public static WebDriver driver;  
	public static WebElement element; 
	String baseUrl = "https://webapps.tekstac.com/RentaCar/";
	    
	    
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE 
	    
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate to basrUrl and return it
	    
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public WebElement getAbsoluteXpathLocator() {    // DO NOT CHANGE THE METHOD SIGNATURE
       
       // identify and locate a 'Name' text box  using absoulte xpath locator
       // Store the element in the static variable 'element' and return it
        element=driver.findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr[1]/td[2]/input"));
	  
       return element;
	}
	
	public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    Locator locator = new Locator();
	    
	    locator.createDriver();
	    WebElement ele1=locator.getAbsoluteXpathLocator();
	    
	     System.out.println("The text value using absolute xpath selector is "+ele1.getAttribute("placeholder"));
	}

}

