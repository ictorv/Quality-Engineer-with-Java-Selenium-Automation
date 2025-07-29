package userDefinedLibraries;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	
	public WebDriver driver;
	//public static String url = "https://www.flipkart.com/";
	public static String browsertype;

	public WebDriver driverInstantiate(String browser,String url){
		browsertype = browser;
		if (browsertype.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get(url);
		return driver;
	}
	
	public void closeCurrentPage() {
		driver.close();
	}
	
	public void driverTearDown() {
		driver.quit();
	}

	
}
