package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//BaseClass handles browser setup, teardown, logging, config loading, and screenshot capture.
//All test classes can extend this to inherit setup and utility features.
public class BaseClass {
	
	// Shared WebDriver instance across tests
	public static WebDriver driver;
	
	// Logger instance for custom logging via log4j
	public Logger logger;
	
	// Holds key-value pairs from config.properties
	public Properties properties;
	
	// Initializes browser and loads application before tests begin
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		// Load application properties from external config file
		properties = new Properties();
		FileInputStream fis = new FileInputStream("./src//test//resources//config.properties");
		properties.load(fis);
		
		String baseURL = properties.getProperty("appURL");
		
		logger = LogManager.getLogger(this.getClass());
		switch(browser.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			default: System.out.println("Invalid browser name..."); return;
		}
		
		driver.manage().deleteAllCookies(); // Delete all cookies from the web page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		
		// Load the target application and maximize the window
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	// Closes the browser after tests have run
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	// Captures screenshot during failure or at key checkpoints
    // Returns path of the saved screenshot file
	public String captureScreen(String tname) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
