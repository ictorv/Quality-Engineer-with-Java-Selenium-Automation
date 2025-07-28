import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaScriptExecutor {   //DO NOT Change the class name
	
	// Use the below declared variables
	public static WebDriver driver;
    String baseUrl = "https://webapps.tekstac.com/selenium/LOGIN_WEBPAGE/index.html";
	
	public WebDriver createDriver() { //DO NOT Change the method Signature
	
	    // Invoke the getDriver() method from the DriverSetup File 
	    // Store it in static variable 'driver', navigate and return it
	    
	    
	    // Please do NOT remove the below URL navigation code
	    driver=DriverSetup.getDriver();
	    driver.get(baseUrl);
		
		return driver;
	    
	}
 
    public String submitForm(String email,String password) { //DO NOT Change the method Signature
      /*Using the driver, Locate the element using javascript executor ONLY. */
      /* Set the form values and submit */
      /* Return the displayed message */
     
       JavascriptExecutor js=(JavascriptExecutor)driver;
       js.executeScript("document.getElementById('email').value='"+email+"';");
       js.executeScript("document.getElementById('pass').value='"+password+"';");
       js.executeScript("document.querySelector('input[type=\"submit\"]').click()");
       String msg=js.executeScript("return document.getElementById('result').innerText;").toString();
       return msg;
    }
    
    public static void main(String[] args) {  //DO NOT Change the method Signature
	    JavaScriptExecutor jse=new JavaScriptExecutor();
	    //Add required code
    }
  
}