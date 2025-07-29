package com.test.utilities;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.NoSuchDriverException;

import com.test.ExcelUtil.ExcelReading;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * This Utility class is to initialize and manage WebDriver instances for different browsers.
 * It reads the browser type and target URL from an Excel sheet and launches the browser accordingly.
 * Supported browsers: chrome, Edge
 */


public class DriverSetup {
	public WebDriver driver;
	public static String URL;
	public static String webBrowserType;
	ExcelReading excelRead;
	String filePath = "src\\test\\java\\com\\test\\data\\ERailData.xlsx";
	String sheetName = "BrowserandURL";
	Object[][] excelData;
	
	public WebDriver driverActivation(String webBrowser) {
		webBrowserType = webBrowser;
		excelRead = new ExcelReading(filePath,sheetName);
		excelData = excelRead.testDataRead(filePath, sheetName);
		URL = excelData[1][1].toString();
		
		try {
			if(webBrowserType.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}else if(webBrowserType.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
		}catch(NoSuchDriverException e) {
			System.out.println("Specified Browser is not present");
		}
		
		try {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		}catch(NullPointerException e) {
			System.out.println("Null pointer exception");
		}
		return driver;
	}
	
	public void driverTearDown(WebDriver driver) {
		driver.quit();
	}
}
