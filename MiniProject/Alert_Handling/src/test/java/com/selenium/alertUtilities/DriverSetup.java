package com.selenium.alertUtilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


/*
 * -----------------------------------------------------------------------------
 * File Name   : DriverSetup.java
 * Package     : com.selenium.alertUtilities
 * Author      : Adinath Khose
 * 
 * Description :
 * This utility class is responsible for initializing and managing the WebDriver 
 * instance for Selenium-based test automation. It supports multiple browsers 
 * and reads the target URL from a properties file using the DataDrivenUtils class.
 * 
 * Features:
 * - Supports Chrome and Edge browsers.
 * - Reads the application URL from a configuration properties file.
 * - Applies standard browser settings: maximized window, cleared cookies, 
 *   and implicit wait.
 * - Provides a method to close the browser after test execution.
 * 
 * Dependencies:
 * - Selenium WebDriver
 * - DataDrivenUtils (for reading the URL from properties file)
 * 
 * Usage:
 * - Call `getDriver("chrome")` or `getDriver("edge")` to initialize the browser.
 * - Use `closeDriver()` to quit the browser after tests.
 * 
 * -----------------------------------------------------------------------------
 */

public class DriverSetup {
	
	public WebDriver driver;
	public static String propertiesPath = System.getProperty("user.dir")+"\\testdata\\config.properties";  
	public static String url = DataDrivenUtils.getURL(propertiesPath);
	public static String browserType;
	
	public WebDriver getDriver(String browser) {
		
		browserType = browser;
		switch(browserType.toLowerCase()){
			case "chrome": driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver();break;
			default: throw new IllegalArgumentException("Unsupported browser: "+ browser);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		return driver;
	}
	public void closeDriver() {
		driver.quit();
	}

}
