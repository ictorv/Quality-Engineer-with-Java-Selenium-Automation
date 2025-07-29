package com.test.TestCase;

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

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.test.ExcelUtil.ExcelReading;
import com.test.objRepo.ERail_HomePage;
import com.test.objRepo.ERail_ResultsPage;
import com.test.utilities.DriverSetup;
import com.test.utilities.ExtentReport;
import com.test.utilities.Screenshot;

public class ERail_TestCase {
	public WebDriver driver;
	static DriverSetup objDriver;
	ExtentReport extentReport;
	ExcelReading excelReader;
	String filePath = "src\\test\\java\\com\\test\\data\\ERailData.xlsx";
	String sheetName = "DataSheet";
	Object[][] excelData;

	@BeforeClass
	@Parameters("BrowserSearch")
	public void driverConfiguration(String browser) {
		objDriver = new DriverSetup();
		driver = objDriver.driverActivation(browser);
		System.out.println("Opening the URL in the browser");
	}
	
	public void testDataReading() {
		excelReader = new ExcelReading(filePath,sheetName);
		excelData = excelReader.testDataRead(filePath, sheetName);
		System.out.println("No. of data rows present: " + excelData.length);
		System.out.println("No. of data columns present: " + excelData[0].length);
	}
		ExtentTest test;

	
	@BeforeTest
	public void startReporting() {
		extentReport = new ExtentReport();
		extentReport.createTest("Train Search Automation Flow");
	}
	
	
   @Test
   public void ERail_Main() throws IOException{
	   ERail_TestCase objTestMain = new ERail_TestCase();
	   Screenshot.screenshotTaken(driver, "Before_Searching");
	   objTestMain.testDataReading();
	   ERail_HomePage homePageObj = new ERail_HomePage(driver);
	   ERail_ResultsPage resultPageObj = new ERail_ResultsPage(driver);
	   
	   String mon = objTestMain.excelData[1][2].toString();
	   
	   double tempYear = Double.parseDouble(objTestMain.excelData[1][3].toString());
	   int year = (int) tempYear;
	   
	   
	   double tempDate = Double.parseDouble(objTestMain.excelData[1][4].toString());
	   int date = (int) tempDate;
	   
	   //Entering the code and clicking on the source station from the suggestions 
	   homePageObj.enterSourceStation(objTestMain.excelData[1][0].toString());
	   homePageObj.clickSourceStation(objTestMain.excelData[2][0].toString());
	   extentReport.logPass("Entered source station code and selected source station.");
	   
	   //Entering the code and clicking the destination station from the suggestions
	   homePageObj.enterDestinationStation(objTestMain.excelData[1][1].toString());
	   homePageObj.clickDestinationStation(objTestMain.excelData[2][1].toString());
	   extentReport.logPass("Entered destination station code and selected destination station");
	   
	   //Clicking on the calender icon
	   homePageObj.clickonCalender();
	   
	   //Providing with the date in the calender with the Month and Year
	   homePageObj.selectDate(mon + "-" + year, Integer.toString(date));
	   extentReport.logPass("Picked the specified date form the calender");
	   
	   //Selecting the Quota from the dropdown 
	   homePageObj.selectQuota(objTestMain.excelData[1][5].toString());
	   extentReport.logPass("Selected the specified quota");
	   
	   //Selecting the class from the dropdown
	   homePageObj.selectClass(objTestMain.excelData[1][6].toString());
	   extentReport.logPass("Selected the specified class");
	   
	   //Clicking on the "Get Trains" button to fetch the results
	   homePageObj.clickGetTrains();
	   extentReport.logPass("Clicked on the Get Trains Button");
	   
	   //Fetching the results after searching the results of the Trains list
	   resultPageObj.resultTrains(objTestMain.excelData[3][0].toString(), objTestMain.excelData[3][1].toString());
	   extentReport.logPass("Validating Results");
	   
	   //Fetching the count of the Trains after validating the search results
	   resultPageObj.setCount();
	   extentReport.logPass("Printed Results to ExcelData");
	   extentReport.logPass("Test completed successfully");
	   
	   //Screenshot to be taken after the results of the search being done
	   Screenshot.screenshotTaken(driver, "After_Searching_Results");
   	}
   
   	@AfterMethod
   	public void getResult(ITestResult result) throws IOException{
   		if(result.getStatus() == ITestResult.FAILURE) {
   			extentReport.testLogger.log(Status.FAIL,MarkupHelper.createLabel(result.getName() + "- Test Case Failed", ExtentColor.RED));
   			extentReport.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + "- Test Case Failed", ExtentColor.RED));
   			String screenshotPath = Screenshot.screenshotTaken(driver, "Failure_SS_@" + result.getName());
   			extentReport.testLogger.fail("Test Case Failed Snapshot is below " + extentReport.testLogger.addScreenCaptureFromPath("." + screenshotPath));
   		}
   		else if(result.getStatus() == ITestResult.SUCCESS) {
   			extentReport.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED ", ExtentColor.GREEN));
   		}
   		else if(result.getStatus() == ITestResult.SKIP) {
   			extentReport.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED ", ExtentColor.ORANGE));
   		}
   	}
   	
   	@AfterClass
   	public void shutDown() {
   		objDriver.driverTearDown(driver);
   	}
   	
   	@AfterTest
   	public void flushReport() {
   		extentReport.flushReports();
   	}
}
