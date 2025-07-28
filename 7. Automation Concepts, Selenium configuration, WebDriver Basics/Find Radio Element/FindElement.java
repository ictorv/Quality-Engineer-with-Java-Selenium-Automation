import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FindElement {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables 
	public static WebDriver driver;  
	public static WebElement buttonElmt; 
	String baseUrl = "https://webapps.tekstac.com/RentaCar/";
	    
	    
	public WebDriver createDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE 
	    
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
		return driver;
	}
	
	public List<WebElement> radiobuttonCount() {    // DO NOT CHANGe THE METHOD SIGNATURE
	       
        // Identify the radio buttons by customized xpath (use By.xpath("//*[@type='radio']")) 
		// Assign your locator to 'radiobuttonXpath' variable and use that to locate the element
		// Get the list of radio buttons and store it in a variable 'radioButtonList'
		// print the total radio buttons count using the list
		// Return the list 'radioButtonList'
    //   WebElement radiobuttonXpath=driver.findElement(By.xpath("//*[@type='radio']"));
       List<WebElement>radioButtonList=driver.findElements(By.xpath("//*[@type='radio']"));
       
       return radioButtonList;
	}
	
		
	public static void main(String[] args) {    // DO NOT CHANGe THE METHOD SIGNATURE
	    
	    FindElement elmts = new FindElement();
	    elmts.createDriver();
	    List<WebElement> radioList=elmts.radiobuttonCount();
	    System.out.println("Total radio buttons count "+radioList.size());
	}

}

