package com.TestScenario;

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

import com.FlightSearchAutomation.IRCTCFlightBooking;
import com.TestUtil.ExcelUtil3;
import com.TestUtil.ExtentReportManager;
import com.TestUtil.ScreenShot;
import com.UserDefinedLibraries.DriverSetup;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestMain {

    WebDriver driver;
    IRCTCFlightBooking irctc;  // Page object for flight booking functionality
    ExcelUtil3 et;  // Utility for handling Excel operations
    String filePath = "src/test/resources/Book1.xlsx"; // Path to the Excel data file
    String sheetName = "Sheet1"; // Sheet name to read data from
    ExtentReportManager erm; // Reporting utility for test logging
    
 // Setup method executed before the class starts
    // Initializes Excel utility, reads data, launches browser, and initializes IRCTCFlightBooking
    
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        et = new ExcelUtil3(filePath, sheetName);
        et.excelDataFetch();
        String url = et.getListArray().get(0);
        driver = DriverSetup.getDriver(browser, url);
        irctc = new IRCTCFlightBooking(driver);
    }
 // Initializes Extent Report before test execution
    @BeforeTest
	public void startReporter()
	   {
		erm = new ExtentReportManager();
		erm.createTest("FlightSearchAutomationProject");
	   }
 // Method executed after each test method to log pass/fail/skip and take a screenshot on failure
    @AfterMethod
	   public void getResult(ITestResult result) throws IOException {
	       if(result.getStatus() == ITestResult.FAILURE) {
	    	 //MarkupHelper is used to display the output in different colors
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
	    	   erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
	    	   String screenshotPath = ScreenShot.screenShotTC(driver, result.getName());
	    	 //To add it in the extent report
	    	   erm.testLogger.fail("Test Case Failed Snapshot is below " + erm.testLogger.addScreenCaptureFromPath("."+screenshotPath));	    	  
	       }
	       else if(result.getStatus() == ITestResult.SUCCESS) {
	    	   erm.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	       }
	       else if(result.getStatus() == ITestResult.SKIP){
	    	   erm.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
	       }
	   }
 // The main test method that performs the end-to-end flight search operation
    @Test
    public void testFlightSearch() {
        var data = et.getListArray();
        String originCity = data.get(1);
        String destinationCity = data.get(2);
        String date = data.get(3);
        String type = data.get(4);
        // Step-by-step execution of flight search process
        irctc.loadHomePage();
        erm.logPass("HomePage Successfully opened");
        
        irctc.selectFromCity(originCity.split(" ")[0], originCity);
        erm.logPass("Origin city Successfully selected");
        irctc.selectToCity(destinationCity.split(" ")[0], destinationCity);
        erm.logPass("Destination city Successfully selected");
        irctc.selectTodayDate(date);
        erm.logPass("Today's Date Successfully selected");
        irctc.selectTravelClass(type);
        erm.logPass("Travel Class Successfully selected");
     // Try clicking search and handle any exception
        try {
            irctc.clickSearch();
        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }
     // Take screenshot after search results are displayed
        irctc.afterSearch();
    }
 // Flush Extent Report after test execution is complete
    @AfterTest
    public void flushReport() {
	 erm.flushReports();
    }
 // Cleanup method to close the browser session after test class is done
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
