package test.Scenario;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import test.object.FilterPage;
import test.object.HomePage;
import test.object.ResultPage;
import test.userDefinedLibraries.DriverSetup;
import test.userDefinedLibraries.ExtentReportManager;
import test.userDefinedLibraries.FetchData;
import test.userDefinedLibraries.Screenshot;

public class TestNG {

	
	
	WebDriver driver;
	ExtentReportManager reportManager;
	
	@BeforeClass
	@Parameters("browser")
	public void driverInstantiation(String browsers) {
		
		driver = DriverSetup.getDriver(browsers);
	}
	
	@BeforeTest
	public void startReporter() {
		try {
			FetchData.intializeTheFile();
			FetchData.extractFromExcel();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		reportManager=new ExtentReportManager();
		reportManager.createTest("Amazon Mobile Search Filter Test");
	}
	
	@Test(priority=1)
	public void navigatingToHomePage() {
		HomePage home = new HomePage(driver);
		home.getHomePage();
		
	}
	
	@Test(priority=2)
	public void navigatingToResult() {
		ResultPage result = new ResultPage(driver);
		result.filter();
	}
	
	@Test(priority=3)
	public void filter() {
		FilterPage filter = new FilterPage(driver);
		filter.validateTheSortBy();
		filter.validateTheResult();
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
	       if(result.getStatus() == ITestResult.FAILURE) {
	    	 //MarkupHelper is used to display the output in different colors
	    	   reportManager.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	    	   reportManager.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	    	   Screenshot.getScreenshot(driver,"failure_Screenshot");
	    	   Screenshot.getScreenshot(driver,"failure_Screenshot");
	    	   String screenshotPath = Screenshot.getScreenshot(driver,"failure_Screenshot");
	    	   reportManager.testLogger.fail("Test Case Failed Snapshot is below",
	    	       MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());	    	  
	       }
	       else if(result.getStatus() == ITestResult.SUCCESS) {
	    	   reportManager.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	       }
	       else if(result.getStatus() == ITestResult.SKIP){
	    	   reportManager.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
	       }
	   }
	
	
	@AfterTest
	public void flushReport(){
		reportManager.flushReports();
	}
	
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
		FetchData.closeWorkBook();
	}
}
