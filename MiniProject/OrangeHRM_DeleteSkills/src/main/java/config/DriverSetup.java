/**
 * Configuration Class: DriverSetup.java
 * 
 * Purpose:
 * - Initializes WebDriver based on the browser parameter passed from TestNG (`Chrome` or `Edge`).
 * - Loads the OrangeHRM application and ensures window maximization.
 * - Provides a method to safely close the browser session after execution.
 *
 * Methods:
 * - getDriver(String browser): Creates and returns a WebDriver instance based on the input browser.
 * - closeDriver(WebDriver driver): Closes the WebDriver instance safely after execution.
 */


package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	
	
	
	public static WebDriver getDriver(String browser) {
		
		WebDriver driver=null;
		
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		return driver;
	}
	
	public void closeDriver(WebDriver driver) {
		driver.close();
	}
	
}