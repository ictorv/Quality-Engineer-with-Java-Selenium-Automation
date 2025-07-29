package driverSetup;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	
	// URL to launch Rediff login page
	String url = "https://mail.rediff.com/cgi-bin/login.cgi";
	
	public WebDriver driver;
	
	// Method to initialize WebDriver based on the browser type
	public WebDriver getDriver(String browser) {
		
		// Launches respective browser based on input
		if(browser.equalsIgnoreCase("chrome")) {
			this.driver = new ChromeDriver(); 
		}
		else if(browser.equalsIgnoreCase("edge")) {
			this.driver = new EdgeDriver(); 
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			this.driver = new FirefoxDriver(); 
		}
		// Maximize browser window
		driver.manage().window().maximize();
		// Open the specified URL
		driver.get(url);
		// Set implicit wait to handle dynamic elements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		
		// Return the WebDriver instance
		return driver;
	}
	
	// Close the current browser window
	public void close() {
		driver.close();
	}
	
	// Quit the WebDriver session completely
	public void quit() {
		driver.quit();
	}

}
