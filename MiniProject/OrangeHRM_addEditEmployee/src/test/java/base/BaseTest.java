package base;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.ExcelWriterUtil;
import utils.ExtentManager;
import utils.FetchProperties;
import utils.ScreenshotUtil;



public class BaseTest {
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected FetchProperties properties = new FetchProperties();


	
	@BeforeSuite
	public void setupOutputFile() {
		//create and/or check for testdata folder and screenshots folder
		new File("testdata").mkdirs();
		new File("screenshots").mkdirs();
		ExcelWriterUtil.prepareOutputSheet(properties.getPATH("EXCEL_PATH"),"EmployeeResult");
	}
	
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(@Optional("chrome") String browser){
		driver = DriverFactory.getDriver(browser);  //different browsers for different testcases
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getPATH("URL").trim());
		extent = ExtentManager.getInstance();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
	    try {
	        // Check if browser is still open
	        if (driver != null) {
	            driver.getTitle(); // This will throw an exception if browser is closed
	        }

	        if (result.getStatus() == ITestResult.FAILURE) {
	            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
	            test.fail(result.getThrowable(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        } else if (result.getStatus() == ITestResult.SUCCESS) {
	            test.pass("Test passed");
	        } else {
	            test.skip("Test skipped");
	        }
	    } catch (Exception e) {
	        // If browser is closed or any exception occurs
	        test.fail("Test failed due to browser closure or unexpected error: " + e.getMessage());
	    } finally {
	        DriverFactory.quitDriver();
	    }
	}
	
	@AfterSuite
	public void tearDownSuite() {
		ExtentManager.flush();
	}
	
}
