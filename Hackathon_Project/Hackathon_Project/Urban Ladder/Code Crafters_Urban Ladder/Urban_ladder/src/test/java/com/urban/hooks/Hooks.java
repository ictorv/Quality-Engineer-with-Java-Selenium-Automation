package com.urban.hooks;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.urban.base.BaseClass;
import com.urban.utillities.PropertiesReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks {
	WebDriver driver;
	Properties properties;

	@Before
	public void setup(Scenario scenario) throws Exception {
		String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");

		driver = BaseClass.initilizeBrowser(browser);
		properties = PropertiesReader.getProperties();

		String baseUrl = properties.getProperty("appURL");

		driver.get(baseUrl);
		// Retry only for scenarios tagged with @Retry
		if (scenario.getSourceTagNames().contains("@Retry")) {
			retryScenario(() -> {
				// Any setup logic here if needed
			}, 2); // Retries before scenario starts
		}

	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
		}
		if (driver != null)
			driver.quit();

	}

//	Retry logic
	public static void retryScenario(Runnable runnable, int maxRetries) {
		int attempt = 0;
		while (attempt <= maxRetries) {
			try {
				runnable.run();
				return;
			} catch (Exception e) {
				attempt++;
				System.out.println("🔁 Scenario retry #" + attempt + ": " + e.getMessage());
				if (attempt > maxRetries) {
					throw new RuntimeException("❌ Scenario setup failed after " + maxRetries + " retries", e);
				}
			}
		}
	}

}
