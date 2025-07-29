package hooks;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utilities.DriverSetup;


public class Hooks {
	
	public static WebDriver driver;
	public static String url = "https://emicalculator.net/";
	public static String browser;
	
	
	
	@BeforeAll
	public static void setUp() throws IOException {
		browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
		DriverSetup.url = url;
		driver = DriverSetup.driverInstantiate(browser);
		System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");

	}
	
	
	@AfterAll
	public static void tearDown() {//nario scenario) {
//		if(scenario.isFailed()) {
//			byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//			Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
//		}
		driver.quit();
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario) {
		if(scenario.isFailed() && driver != null) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	};
	
	
}
 