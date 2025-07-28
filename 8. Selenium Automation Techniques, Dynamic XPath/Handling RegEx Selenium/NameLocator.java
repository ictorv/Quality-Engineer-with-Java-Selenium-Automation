import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NameLocator {      //DO NOT change the class name
 
    //Use the below declared variable
    public static WebDriver driver;
    String baseUrl = "https://webapps.tekstac.com/Handling_Regular_Expression/";
	
	public WebDriver createDriver() {   // DO NOT CHANGE THE METHOD SIGNATURE
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    
	    driver=DriverSetup.getDriver(); 
	    // Please do NOT remove the below URL navigation code
	    driver.get(baseUrl);
		
		 return driver;
	}
	
   	public void setFormValues(WebDriver driver) {   // DO NOT CHANGE THE METHOD SIGNATURE
	    //set the value for 'Shipment for user' and submit form
	    driver.findElement(By.id("userId")).sendKeys("Shamili");
	    driver.findElement(By.id("track")).click();
   	}

    
    public WebElement getEmailResultElement(WebDriver driver) { // DO NOT CHANGE THE METHOD SIGNATURE
        
        //Find the element of 'shamili93@gamil.com' and return it
        WebElement email=driver.findElement(By.id("e- mail"));
        
        return email;
    }
    
    public boolean validateEmail(String eMailID) {  // DO NOT CHANGE THE METHOD SIGNATURE
       //Validate email using regex. 
       
       String mail="\\b[A-z0-9a-z-]+@[a-z]+\\.[a-z]{2,4}\\b";
       return eMailID.matches(mail);
    }
    
  
    public static void main(String[] args) {    // DO NOT CHANGE THE METHOD SIGNATURE
	    NameLocator reg=new NameLocator();
	     //Add required code here
	}

  
}
