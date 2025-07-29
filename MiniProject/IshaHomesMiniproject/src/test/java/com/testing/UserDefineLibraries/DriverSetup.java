package com.testing.UserDefineLibraries;

import java.time.Duration;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

	private static WebDriver driver;
	Sheet sheet;
	static Object[][] url;
	static String filePath = "src\\test\\java\\com\\testing\\scripts\\Urls.xlsx";
	static String sheetName = "IshaHomesUrl";

	public static String browerType;

//using Url1 class to locate this Url in excel to read
	public static void pathInitialize() {
		UrlTest url1 = new UrlTest();
		try {
			url = url1.excelDataFetch(filePath, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// selecting browser and executing in particular browser based on conditions
	public static WebDriver driverInstantiate(String browser) {

		browerType = browser;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			throw new InvalidArgumentException("Inavlid browser");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url[1][1].toString());
		return driver;
	}

//closing of driver
	public static void driverTearDown() {
		driver.quit();
	}

}
