package com.facebook.util;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

	private WebDriver driver;
	private final String baseUrl = "https://www.facebook.com/";

	public WebDriver getDriver(String browser) {
		if (browser == null) {
			throw new IllegalArgumentException("Browser type must not be null.");
		}

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(baseUrl);

		return driver;
	}

	public void closeDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
