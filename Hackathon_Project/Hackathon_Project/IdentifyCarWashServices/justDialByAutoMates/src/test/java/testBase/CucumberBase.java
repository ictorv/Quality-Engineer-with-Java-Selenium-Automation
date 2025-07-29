package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Attachment;
import utilities.WaitUtil;

public class CucumberBase {

    private static WebDriver driver;
    private static WaitUtil util;
    private static Properties properties;
    private static Logger logger;

    public static void setup(String os, String browser) throws IOException {
        logger = LogManager.getLogger(CucumberBase.class);

        FileReader file = new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        if (properties.getProperty("executionEnvironment").equalsIgnoreCase("remote")) {
            URL url = URI.create("http://localhost:4444/wd/hub").toURL();

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = getChromeOptions(os);
                driver = new RemoteWebDriver(url, options);
            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = getEdgeOptions(os);
                driver = new RemoteWebDriver(url, options);
            } else {
                logger.error("No browser matched for remote execution.");
                return;
            }

        } else if (properties.getProperty("executionEnvironment").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver(getChromeOptions(os));
                    break;
                case "edge":
                    driver = new EdgeDriver(getEdgeOptions(os));
                    break;
                default:
                    logger.error("Invalid browser name for local execution.");
                    return;
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("url"));
        driver.manage().deleteAllCookies();
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static WaitUtil getWaitUtil() {
        return util;
    }

//    public static String captureScreen(String tname) throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//
//        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
//        File targetFile = new File(targetFilePath);
//        sourceFile.renameTo(targetFile);
//
//        return targetFilePath;
//    }

    private static ChromeOptions getChromeOptions(String os) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--start-maximized");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        options.setExperimentalOption("prefs", prefs);
        if (properties.getProperty("executionEnvironment").equalsIgnoreCase("remote")) {
            options.setPlatformName(os.equalsIgnoreCase("windows") ? "Windows 11" : "MAC");
        }
        return options;
    }

    private static EdgeOptions getEdgeOptions(String os) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications", "--start-maximized");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        options.setExperimentalOption("prefs", prefs);
        if (properties.getProperty("executionEnvironment").equalsIgnoreCase("remote")) {
            options.setPlatformName(os.equalsIgnoreCase("windows") ? "Windows 11" : "MAC");
        }
        return options;
    }
    
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
