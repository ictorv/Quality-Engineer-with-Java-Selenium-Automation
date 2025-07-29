package com.selenium.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class DriverSetup {
	
	public WebDriver driver;  
	public String url = DataDrivenUtils.getURL();
	public String browserType;
	
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
