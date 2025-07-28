import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;  

public class FindElements {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables 
	public static WebDriver driver;  
	public static List<WebElement> optionsList;
	String baseUrl = "https://webapps.tekstac.com/SeleniumApp1/EventManagement/index.html#tm-section-4";
	
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE 
	    
	     // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver=DriverSetup.getDriver();
	    
	    driver.get(baseUrl);
		return driver;
	}
	
	public List<WebElement> getDropdownCount() {    // DO NOT CHANGE THE METHOD SIGNATURE
      
       // Identify all the drop down options belonging to the 'Select' tag using tagName locator 
       // Store the list in the static variable 'optionsList' and return it
       // Print the drop down options count 
       // Return the list 'optionsList'
       optionsList=driver.findElements(By.tagName("Select"));
       return optionsList;

	}
	
	public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE
	    
	    FindElements elmts = new FindElements();
	    elmts.createDriver();
	    List<WebElement> count = elmts.getDropdownCount();
	    System.out.println("The dropdown options count is "+ count.size());
	}

}

