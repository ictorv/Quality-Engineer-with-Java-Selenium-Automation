package com.selenium.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


/*
 * -----------------------------------------------------------------------------
 * File Name   : DriverSetup.java
 * Package     : com.selenium.alertUtilities
 * Created Date: 18th June, 2025
 * Author      : Lava Prasad G V
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
	
	public static WebDriver driver;
	public static String url = DataDrivenUtils.getURL();
	public static String browserType;
	
	public static WebDriver getDriver(String browser) {
		
		browserType = browser;
		switch(browserType.toLowerCase()){
			case "chrome": driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver();break;
			default: throw new IllegalArgumentException("Unsupported browser: "+ browser);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Driver created,  Opening Link");
		driver.get(url);
		
		return driver;
	}
	public static void closeDriver() {
		driver.quit();
	}

}
