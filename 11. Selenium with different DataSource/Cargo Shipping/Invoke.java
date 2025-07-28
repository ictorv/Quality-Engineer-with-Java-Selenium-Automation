package mypack;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Invoke {
	
	//Use the below declared variables
	public static WebDriver driver;
	public static RespositoryParser parser;
	
	public static void main(String[] args) throws Exception {
		Invoke invoke = new Invoke();
		String weight = "200";
		String transportMode = "road";
		//create driver instance 
		//invoke all setter methods 
		//submit the form
		//close the browser
		invoke.setUpDriver();
		invoke.setWeight(weight);
		invoke.setTransportMode(transportMode);
		invoke.submit();
		String r=invoke.getResult();
		System.out.println(r);
		invoke.closeBrowser();
	}
	
	public void setUpDriver() throws Exception{// Do not change the method signature
		 // Invoke the getWebDriver method from the DriverSetup File
	    // Assign it to a static variable 'driver' and 'parser'
	    DriverSetup ds=new DriverSetup();
	    driver=ds.getDriver();
	    parser=new RespositoryParser("ObjectRepo.properties");
	}
	
	public void setWeight(String weight) {// Do not change the method signature
		// Locate the WebElement 'Weight' 
        // Use sendKeys method to fill the weight
       driver.findElement(By.xpath(parser.getObjectLocator("weight"))).sendKeys(weight);
	}
	
	public void setTransportMode(String transportMode) {// Do not change the method signature
		// Locate the corresponding WebElement 'Transport Mode' based on the transportMode string input  
        // Use click method to select the transport mode.
        driver.findElement(By.xpath(parser.getObjectLocator(transportMode))).click();
	}
	
	public void submit() {// Do not change the method signature
		// Submit the form by clicking the 'Calculate' button
		 driver.findElement(By.xpath(parser.getObjectLocator("submit"))).click();
	}
	
	public String getWeight() {// Do not change the method signature
		// Locate the WebElement 'Weight' 
        // Use getAttribute method to find the value of weight
        WebElement wField=driver.findElement(By.xpath(parser.getObjectLocator("weight")));
		return wField.getAttribute("value");
	}
	
	
	public String getResult() {// Do not change the method signature
		// Locate the WebElement corresponding to the 'Result' displayed after form submission. 
	    // Get the WebElement, store it in a variable and return the text as String.
		WebElement res=driver.findElement(By.xpath(parser.getObjectLocator("result")));
		return res.getText();
	}
	
	public void closeBrowser() {// Do not change the method signature
		// Close the browser
		driver.close();
	}

}
