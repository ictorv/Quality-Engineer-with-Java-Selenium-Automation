// Source code is decompiled from a .class file using FernFlower decompiler.
package com.urban.base;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.urban.utillities.DriverSetup;

public class BaseClass2 {
	public static WebDriver driver;
	static Logger logger;

	@BeforeClass
	@Parameters({ "browser" })
	public static void setup(String browser) throws IOException {
		driver = DriverSetup.initializeBrowser(browser);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10L));
	}

	@AfterClass
	public static void teardown() {
		driver.quit();
	}

	public static Logger getLogger() {
		logger = LogManager.getLogger();
		return logger;
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
