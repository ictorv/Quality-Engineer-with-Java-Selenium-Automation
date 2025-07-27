import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PurchaseApp { // Do not change 
    
    // Use the below declarations
    static WebDriver driver;
    
	String baseUrl = "https://webapps.tekstac.com/PurchaseApp/";

    
	public WebDriver createDriver() {   // Do not change the method signature
	    
		// Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    
	    driver=DriverSetup.getDriver();
	    // Please do NOT remove the below URL navigation code
	    driver.get(baseUrl);
		
		// return driver
		
		return driver;
	}
	
	
	public String locateIFrameLink() {  // Do not change the method signature  
	    
	    
	    //Get the WebElement object of the iframe using the id of the iframe.

		//Get the link and store it in a static variable 'framelink'

		//Return the framelink
		WebElement iframe=driver.findElement(By.id("iframe_heading"));
		String framelink=iframe.getAttribute("src").toString();
		
		return framelink;
	    
	}
	
	public void closeBrowser() {  // Do not change the method signature
	     
		// close the browser
		driver.close();
	}


	public static void main(String[] args){
		
		PurchaseApp app = new PurchaseApp();  
		
		// Add your code here
		
	}	 	  	  		 	     	     	      	 	

}