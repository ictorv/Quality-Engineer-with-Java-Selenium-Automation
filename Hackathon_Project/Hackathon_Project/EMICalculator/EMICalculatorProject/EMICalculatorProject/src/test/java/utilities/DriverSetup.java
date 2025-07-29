package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSetup {
	public static WebDriver driver;
	public static String url;
	public static String browserType;
	static Logger logger;
	
	static Properties p;
	
	public static WebDriver driverInstantiate(String browser) throws IOException {
		
		logger = LogManager.getLogger(DriverSetup.class);
		
		p = getProperties();
		
		String executionEnv = p.getProperty("execution_env");
		String os = p.getProperty("os").toLowerCase();
		
		if (executionEnv.equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// os
			switch (os) {
			case "windows":
				capabilities.setPlatform(Platform.WINDOWS);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
			default:
				System.out.println("No matching OS");
				return null;
			}
			
			// browser
			switch (browser) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("disable-notifications");
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("disable-notifications");
				capabilities.setBrowserName("edge");
				break;
			default:
				System.out.println("No matching browser");
				return null;
			}
 
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		} 
		else if (executionEnv.equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("disable-notifications");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("disable-notifications");
				driver = new EdgeDriver(edgeOptions);
				break;
			default:
				System.out.println("No matching browser");
				driver = null;
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		return driver;
	}
	
	public static Properties getProperties() throws IOException{
		FileReader file = new FileReader("src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		return p;
	}
	
	
	
	public static Logger getLogger() {
		return logger;
	}
	
	public void driverTearDown() {
		driver.quit();
	}
}
