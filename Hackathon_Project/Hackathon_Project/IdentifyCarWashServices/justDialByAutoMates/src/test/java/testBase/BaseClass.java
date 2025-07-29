package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.WaitUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	public static WaitUtil util;
	public Properties properties;
	public Logger logger;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());

		FileReader file = new FileReader("./src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);
		
		if (properties.getProperty("executionEnvironment").equalsIgnoreCase("remote")) {
			
			URL url = URI.create("http://localhost:4444/wd/hub").toURL();
			
			if (browser.equalsIgnoreCase("chrome")) {
		        ChromeOptions options = new ChromeOptions();
		        options.setPlatformName(os.equalsIgnoreCase("windows") ? "Windows 11" : "MAC");
		        options.addArguments("--disable-notifications");
		        options.addArguments("--start-maximized");
		        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

		        Map<String, Object> prefs = new HashMap<>();
		        prefs.put("profile.default_content_setting_values.geolocation", 2);
		        options.setExperimentalOption("prefs", prefs);

		        driver = new RemoteWebDriver(url, options);

		    } else if (browser.equalsIgnoreCase("edge")) {
		        EdgeOptions options = new EdgeOptions();
		        options.setPlatformName(os.equalsIgnoreCase("windows") ? "Windows 11" : "MAC");
		        options.addArguments("--disable-notifications");
		        options.addArguments("--start-maximized");
		        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

		        Map<String, Object> prefs = new HashMap<>();
		        prefs.put("profile.default_content_setting_values.geolocation", 2);
		        options.setExperimentalOption("prefs", prefs);

		        driver = new RemoteWebDriver(url, options);

		    } else {
		        System.out.println("No browser matched for remote execution.");
		        return;
		    }

		}
		if (properties.getProperty("executionEnvironment").equalsIgnoreCase("local")) {

			switch (browser) {
			case "chrome":
				ChromeOptions options = new ChromeOptions();
			    Map<String, Object> prefs = new HashMap<>();
			    prefs.put("profile.default_content_setting_values.geolocation", 2); // 2 = Block
			    options.setExperimentalOption("prefs", prefs);

			    options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/114.0.0.0 Safari/537.36");
			    options.addArguments("start-maximized");
			    options.addArguments("--disable-notifications");

			    driver = new ChromeDriver(options);
				break;
			case "edge":
				EdgeOptions option = new EdgeOptions();

				Map<String, Object> pref = new HashMap<>();
				pref.put("profile.default_content_setting_values.geolocation", 2); // 2 = Block
				option.setExperimentalOption("prefs", pref);

				option.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/114.0.0.0 Safari/537.36");
				option.addArguments("start-maximized");
				option.addArguments("--disable-notifications");

				driver = new EdgeDriver(option);
				break;
			default:
				System.out.println("Invalid browser name");
				return;
			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("url"));
		driver.manage().deleteAllCookies();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}

