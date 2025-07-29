/**
 * File Name: DriverSetup.java
 * Description:
 * This class handles browser setup for test execution. It initializes the WebDriver
 * based on the specified browser (Chrome, Firefox, or Edge), navigates to the EduVidya homepage,
 * maximizes the window, and sets an implicit wait. It also bypasses browser security warnings
 * by interacting with specific elements.

 * Methods:
 * - getDriver(String browser): Launches the browser, loads the website, and returns the WebDriver.
 * - quitDriver(WebDriver driver): Closes the browser after tests are completed.

 */

package driverConfig;

import java.time.Duration;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverSetup {

	public static WebDriver driver;
	public static String browserName;

	public static WebDriver getDriver(String browser) {

		browserName = browser;

		if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setAcceptInsecureCerts(true);
            driver = new EdgeDriver(options);
        } 
		driver.get("https://www.eduvidya.com/");
		driver.manage().window().maximize();
		//driver.findElement(By.id("details-button")).click();
		//driver.findElement(By.id("proceed-link")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		System.out.println("Web Driver configured and Website loaded in the browser....");

		return driver;
	}

	public static void quitDriver(WebDriver driver) {
		if(driver!=null) {
			driver.quit();
		}
	}

}
