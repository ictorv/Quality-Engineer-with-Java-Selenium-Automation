package test.userDefinedLibraries;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {

	private static WebDriver driver ;
	
	private static String baseUrl = FetchData.list[1][0].toString();
	
	
	//initializing the WebDriver
	public static WebDriver getDriver(String browser) {
		

		
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else 
			driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(baseUrl);
		return driver;
	}
	
	
	public static void driverTearDown() {
		driver.close();
	}	
	
}
