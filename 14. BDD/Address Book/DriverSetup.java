//DO NOT CHANGE THE CODE TO CREATE DRIVER 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;

public class DriverSetup {
    public static WebDriver getDriver() {
		
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
		
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("--headless");
		FirefoxProfile profile=new FirefoxProfile();
	    FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary(firefoxBinary);
		firefoxOptions.setProfile(profile);
		
		WebDriver driver = new FirefoxDriver(firefoxOptions);
		return driver;
	}
}