import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Locator {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables 
	public static WebDriver driver;  
	public static WebElement buttonElmt; 
	String baseUrl = "https://webapps.tekstac.com/RentaCar/";
	    
	    
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE 
	    
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate to basrUrl and return it
	    
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public WebElement getCSSLocator() {    // DO NOT CHANGE THE METHOD SIGNATURE
       
       // identify and locate a 'Confirm' button  using 'cssSelector' locator
       // Store the element in the static variable 'buttonElmt' and return it
       // print the element text 
	   buttonElmt=driver.findElement(By.cssSelector("#submit"));
       return buttonElmt;
	}
	
	public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    Locator locator = new Locator();
	    
	    locator.createDriver();
	    WebElement btnElmt=locator.getCSSLocator();
	    
	    System.out.println("The button text value is "+btnElmt.getText());
	}

}

