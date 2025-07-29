package com.selenium.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.selenium.objectRepositories.FilterPage;
import com.selenium.objectRepositories.LandingPage;
import com.selenium.objectRepositories.RhapsodyHomePage;
import com.selenium.objectRepositories.SearchPage;
import com.selenium.utilities.DataDrivenUtils;
import com.selenium.utilities.DriverSetup;
import com.selenium.utilities.ExtentReportManager;
import com.selenium.utilities.ScreenShot;

public class TestMain {
	public WebDriver driver;
	public FilterPage fp;
	public LandingPage lp;
	public SearchPage sp;
	public RhapsodyHomePage rhp;
	public static List<String> dataList = new ArrayList<>();
	ExtentReportManager erm;
	

	/**
	 * Initializes the WebDriver and page objects before any test methods are run.
	 * 
	 * This method is annotated with @BeforeClass, so it runs once before all tests in the class.
	 * It also uses @Parameters to accept a browser type from the testng.xml configuration.
	 * If no browser is specified, it defaults to "chrome".
	 *
	 * The method performs the following:
	 * - Initializes the WebDriver instance using the specified browser.
	 * - Instantiates page object classes: FilterPage, LandingPage, SearchPage, and RhapsodyHomePage.
	 *
	 * @param browser the name of the browser to use for testing (e.g., "chrome", "firefox")
	 */

	@BeforeClass
	@Parameters({"browser"})
	public void setupDriver(@Optional("chrome")String browser) {
		driver = DriverSetup.getDriver(browser);
		fp =new FilterPage(driver);
		lp =new LandingPage(driver);
		sp = new SearchPage(driver);
		rhp=new RhapsodyHomePage(driver);
		
	}
	
	/**
	 * Initializes the ExtentReports test reporting before any test execution begins.
	 *
	 * This method is annotated with @BeforeTest, so it runs once before any test methods in the suite.
	 * It performs the following:
	 * - Instantiates the ExtentReportManager.
	 * - Creates a test entry in the report for tracking the "Search Functionality Flow".
	 */
	@BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("Search Functionality Flow");
	   }
	
	
	
	
	
	/**
	 * Validates the complete search functionality flow of the application.
	 *
	 * This test method performs an end-to-end scenario including:
	 * - Handling landing page pop-up
	 * - Navigating to the search page
	 * - Entering a search query and clicking the relevant link
	 * - Interacting with the booking and filter pages
	 * - Selecting random months, destination, departure, and travel duration
	 * - Sorting results and capturing the final search output
	 * - Taking screenshots at key steps for reporting
	 * - Writing the collected data into an Excel sheet for record-keeping
	 *
	 * Each step is logged using ExtentReports for detailed test reporting.
	 *
	 * @throws Exception if any step in the flow fails or encounters an error
	 */

	@Test
	public void test_search_functionality() throws Exception {
		lp.waitAndHandlePopUp();
		erm.logPass("Pop is handled");
		ScreenShot.screenShotTC(driver, "Landing page");
		
		lp.openSearchPage();
		erm.logPass("Search page Opened");
		
		sp.enterQuery();
		erm.logPass("Search query has been sent");
		ScreenShot.screenShotTC(driver, "Search page");
		
		sp.clickLink();
		
		rhp.clickBookNow();
		erm.logPass("Clicked book now");
		ScreenShot.screenShotTC(driver, "Filter Page");
		
		String[] monthList = fp.setMonths();
		erm.logPass("Random 4 months has been selected ");
		
		String dest = fp.setDestionation();
		erm.logPass("Destination has been set");
		
		
		String dept = fp.setDeparture();
		erm.logPass("Departure has been set");
		
		
		String tNight = fp.setTravelNight();
		erm.logPass("Travel Duration has been selected");
		
		
		fp.setSort();
		erm.logPass("Results sorted by low to high order");
		
		ScreenShot.screenShotTC(driver, "Results");
		String result = fp.getSearchResutls();
		
		for(int i=0;i<4;i++) {
			dataList.add(monthList[i]);
		}
		dataList.add(dest);
		dataList.add(dept);
		dataList.add(tNight);
		
		dataList.add(result);
		DataDrivenUtils.writeDataIntoExcel(dataList);
		erm.logPass("Results fetched");
		erm.logPass("Browser Closed");
		
	}
	
	
	
	
	/**
	 * Captures and logs the result of each test method after execution.
	 *
	 * This method is annotated with @AfterMethod, so it runs after every test method.
	 * It uses ExtentReports to log the test status and attach screenshots for failed tests.
	 *
	 * Functionality:
	 * - If the test fails:
	 *   - Logs the failure status with red color using MarkupHelper.
	 *   - Logs the exception that caused the failure.
	 *   - Captures a screenshot and attaches it to the report.
	 * - If the test passes:
	 *   - Logs the success status with green color.
	 * - If the test is skipped:
	 *   - Logs the skip status with orange color.
	 *
	 * @param result the ITestResult object containing the status and details of the test method
	 * @throws Exception if screenshot capture or report logging fails
	 */
	@AfterMethod
	   public void getResult(ITestResult result) throws Exception {
	       if(result.getStatus() == ITestResult.FAILURE) {
	    	 //MarkupHelper is used to display the output in different colors
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	    	   String screenshotPath = ScreenShot.screenShotTC(driver, result.getName());
	    	 //To add it in the extent report 
	    	   erm.testLogger.fail("Test Case Failed Snapshot is below " + erm.testLogger.addScreenCaptureFromPath(screenshotPath));	    	  
	       }
	       else if(result.getStatus() == ITestResult.SUCCESS) {
	    	   erm.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	       }
	       else if(result.getStatus() == ITestResult.SKIP){
	    	   erm.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
	       }
	   }
	
	
	
	
	/**
	 * Closes the browser and terminates the WebDriver session after all test methods in the class have run.
	 *
	 * This method is annotated with @AfterClass, so it executes once after all test methods in the current class.
	 * It ensures that the browser is properly closed and resources are released.
	 */

	@AfterClass
	public void terminateBrowser() {
		DriverSetup.closeDriver();
	}
	
	
	/**
	 * Flushes and finalizes the ExtentReports after all tests in the suite have completed.
	 *
	 * This method is annotated with @AfterTest, so it runs once after all test methods in the test suite.
	 * It ensures that all logs and test results are written to the report file.
	 */

	@AfterTest
	public void flushReport() {
		erm.flushReports();
	}

}
