package com.test.Utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.data.ExcelDataSheet;

public class DriverSetup {
	
	public static WebDriver driver;
	public static String excelFileName = "MiniProjectTestData.xlsx";
	public static String sheetName = "ReDiff Link Count";
	public static String url;
	public static ExcelDataSheet es;
	public static String browsertype;
	
	public WebDriver getDriver(String browser) throws Exception {
		browsertype = browser;
		//Gets the URL from ExcelSheet
		url = es.getCellData(1,1);
		if (browsertype.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
	        driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		System.out.println("Opened Rediff Website");
		return driver;
	}
	
	static public void driverTearDown() {
		driver.quit();
	}
}
