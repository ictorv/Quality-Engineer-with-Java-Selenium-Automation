/** DO NOT CHANGE THIS CLASS. THIS CLASS IS FOR REFERENCE ONLY  **/

package mypack;     //Do not change the package name

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	public static WebDriver driver;

	public static WebDriver getDriver() {
		
		if (driver == null) {
			/*  For Chrome Browser,
		    please modify the below driver setup code
	    	*/
		    System.setProperty("webdriver.geckodriver.driver", "geckodriver.exe");
	        FirefoxBinary firefoxBinary = new FirefoxBinary();
	        firefoxBinary.addCommandLineOptions("--headless");
	        FirefoxProfile profile=new FirefoxProfile();
	        FirefoxOptions firefoxOptions = new FirefoxOptions();
	        firefoxOptions.setBinary(firefoxBinary);
	        firefoxOptions.setProfile(profile);
	        driver = new FirefoxDriver(firefoxOptions);
			String baseUrl = "https://webapps.tekstac.com/Shopify/";
			driver.get(baseUrl);
		}
	   
		return driver;
	}
}

	 	  	  		 	     	     	      	 	
