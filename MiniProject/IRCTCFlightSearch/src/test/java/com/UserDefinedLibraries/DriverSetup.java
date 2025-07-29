	package com.UserDefinedLibraries;
	import java.time.Duration;
	
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	
	
	 
	public class DriverSetup {
		public static WebDriver driver;
		
		public static String urlLink;
		public static String browerType;
		
		public static WebDriver getDriver(String browser, String url) {
			browerType=browser;
			urlLink = url;
			if(browerType.equalsIgnoreCase("chrome")) {
				driver=new ChromeDriver();
			}
			else if(browerType.equalsIgnoreCase("edge")) {
				driver=new EdgeDriver();
			}
			else if(browerType.equalsIgnoreCase("firefox")) {
				driver=new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(urlLink);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			return driver;
		}
		public void driverTearDown() {
			driver.quit();
		}
	 
	}
