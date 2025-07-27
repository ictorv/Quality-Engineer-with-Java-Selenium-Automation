import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ToolTip {      // DO NOT CHANGE THE CLASS NAME
	  
	// Use the below declared variables
	
	private static WebDriver driver;
    private static String baseUrl = "https://webapps.tekstac.com/SeleniumApp1/SmartUniversity/login.html";
	
	//DO NOT change
	public ToolTip() {}
	
	//DO NOT change the constructor
	public ToolTip(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static WebDriver createDriver()
	{
        // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate to basrUrl and return 	   
    	  driver=DriverSetup.getDriver();
    	  driver.get(baseUrl);
    	  return driver; 
	}
	public static String testToolTip() throws IOException, InterruptedException
	{
		
		//Locate the Register here Link and mouse hover to the element and get the 'Registration' text from the tooltip and return it.
    	WebElement register=driver.findElement(By.id("register"));
    	Actions action=new Actions(driver);
    	action.moveToElement(register).perform();
    	String tooltip=register.getAttribute("title");
    	return tooltip;	
	}
	
	

	public static void main(String[] args) throws IOException, InterruptedException {
		createDriver();
		testToolTip();
		
	}
}
