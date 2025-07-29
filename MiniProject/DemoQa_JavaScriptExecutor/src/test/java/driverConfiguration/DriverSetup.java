package driverConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {

	public static WebDriver driver;

	// Method to initialize WebDriver based on browser type
	public WebDriver driverIntialization(String browser) throws IOException {

		// Instantiate WebDriver based on the given browser name
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		// Maximize the browser
		driver.manage().window().maximize();
		// Clear all cookies
		driver.manage().deleteAllCookies();
		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// loading properties for external file for configuration (URL)
		Properties demoQAprop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\demoQA.properties");
		demoQAprop.load(file);

		driver.get(demoQAprop.getProperty("url"));

		return driver;

	}

	public void exitBrowser(WebDriver driver) {

		driver.quit();

	}

	public void windowscroll(WebDriver driver, int scrollpixel) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,arguments[0]);", scrollpixel);

	}

}
