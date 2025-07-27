import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Locator {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables 
	public static WebDriver driver;  
	public static WebElement classElmt; 
	String baseUrl = "https://webapps.tekstac.com/SeleniumApp2/Pandemic/Pandemic.html";
	    
	    
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE 
	    
	    // Invoke the getWebDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
	    return driver;
	}
	
	public WebElement getClassNameLocator() {    // DO NOT CHANGE THE METHOD SIGNATURE
       
       // identify and locate the Class Element using an 'className' locator
       // Store the element in the static variable 'classElmt' and return it
       // print the element text 
       classElmt=driver.findElement(By.className("sidenav"));
       return classElmt;
	}
	
		
	public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    Locator classlocator = new Locator();
	    classlocator.createDriver();
	    WebElement btnElmt=classlocator.getClassNameLocator();
	    System.out.println("The element text is  "+btnElmt.getText());
	}

}

