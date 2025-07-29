package com.test.utility.ProductListAutomation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility 
{
	public WebDriver driver;
	public static String url;
	public static String browsertype;	
 

 
	public WebDriver driverInstantiate(String browser)
	{
		// TODO Auto-generated method stub
		browsertype = browser;
		if (browsertype.equalsIgnoreCase("chrome")) 
		{
			driver = new ChromeDriver();
		} 
		else if (browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		} 
		else if (browser.equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}

	public void driverTearDown()
	{
		driver.close();
	}

}
