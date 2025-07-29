package UserDefinedLibraries;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import TestData.DataDrivenUtils;

public class DriverSetup {

	public WebDriver driver;
	
	public String url = DataDrivenUtils.getURL();
	public String browserType;
	
	public WebDriver driverInstantiate(String browser) {
		browserType = browser;
		if(browserType.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		return driver;
	}
	
	public void driverTearDown() {
		driver.quit();
	}
	
}
