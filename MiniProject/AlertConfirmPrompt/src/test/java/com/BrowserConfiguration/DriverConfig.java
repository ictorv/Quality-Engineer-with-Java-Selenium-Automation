package com.BrowserConfiguration;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import com.Screenshot.Screenshot;

public class DriverConfig {

    // WebDriver instance to control the browser
    public WebDriver driver;

    // Static base URL to be opened in the browser
    public static String baseUrl = null;

    // Browser type (e.g., "chrome", "firefox", etc.)
    public String browserType;

    // Default constructor
    public DriverConfig() {
    }

    // Method to set the base URL from external input
    public static void setUrl(String url) {
        baseUrl = url;
    }

    // Constructor to initialize the DriverConfig with a specified browser
    public DriverConfig(String browserType) {
        this.browserType = browserType;
    }

    // Initializes the WebDriver based on the selected browser type
    public WebDriver driverInitialization() {
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("InternetExplorer")) {
            driver = new InternetExplorerDriver();
        } else if (browserType.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            return driver = null; // No valid driver matched
        }

        // Maximize the browser window
        driver.manage().window().maximize();

        // Implicit wait to ensure the page loads before interacting with elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the base URL
        driver.get(baseUrl);

        // Take a screenshot of the homepage
        try {
            Screenshot.ScreenShot(driver, "HomePage");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Return the initialized WebDriver instance
        return driver;
    }

    // Navigate to the alert test page using mouse actions
    public void SwitchingToAlert() {
        WebElement switchAlert = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[4]"));
        WebElement clickOnAlert = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li[4]/ul/li[1]/a"));

        Actions action = new Actions(driver);
        action.moveToElement(switchAlert).click(clickOnAlert).perform();
    }

    // Close the current browser tab
    public void driverClose() {
        driver.close();
    }

    // Quit the entire browser session
    public void driverShutDown() {
        driver.quit();
    }
}

