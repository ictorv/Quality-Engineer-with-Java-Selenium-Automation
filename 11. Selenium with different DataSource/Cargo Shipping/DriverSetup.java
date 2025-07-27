//CREATE DRIVER USING THIS CLASS. DO NOT CHANGE THE CLASS AND METHOD NAME

package mypack;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	public static WebDriver driver;
	public static String baseUrl = "https://webapps.tekstac.com/Cargo_Shipping_Cost/";
	
	public  WebDriver getDriver() {
		/*  For Chrome Browser,
		    please modify the below driver setup code
		*/
	    System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
		FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxProfile profile=new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.setProfile(profile);
        driver = new FirefoxDriver(firefoxOptions);
	    driver.navigate().to(DriverSetup.baseUrl);
	    return driver;
	}
	

}

