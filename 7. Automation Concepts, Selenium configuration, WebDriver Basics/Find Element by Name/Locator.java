import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Locator {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables 
	public static WebDriver driver;  
	public static WebElement paymentElmt; 
	String baseUrl = "https://webapps.tekstac.com/RentaCar/";
	    
	    
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE 
	    
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public WebElement getNameLocator() {    // DO NOT CHANGE THE METHOD SIGNATURE
       
        // identify and locate a 'Payment Mode' button  using an 'name' locator
        // Store the element in the static variable 'paymentElmt' and return it
        // print the element text 
        paymentElmt=driver.findElement(By.name("payment"));
	    return paymentElmt;
	}
	
		
	public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    Locator namelocator = new Locator();
	    namelocator.createDriver();
	    WebElement btnElmt=namelocator.getNameLocator();
	    System.out.println("The Payment mode is "+btnElmt.getAttribute("value"));
	}

}

