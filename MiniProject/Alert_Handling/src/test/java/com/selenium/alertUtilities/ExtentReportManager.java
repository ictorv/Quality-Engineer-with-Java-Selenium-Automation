package com.selenium.alertUtilities;

import com.selenium.tests.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/*
 * -----------------------------------------------------------------------------
 * File Name   : ExtentReportManager.java
 * Package     : com.selenium.alertUtilities
 * Author      : Adinath Khose
 * 
 * Description :
 * This class implements the TestNG `ITestListener` interface to generate 
 * detailed ExtentReports for Selenium test execution. It logs test results 
 * (pass, fail, skip), captures screenshots on failure, and writes test 
 * outcomes to an Excel sheet using `DataDrivenUtils`.
 * 
 * Features:
 * - Initializes ExtentReports with a timestamped HTML file.
 * - Logs test success, failure, and skipped status with detailed messages.
 * - Captures screenshots on test failure and attaches them to the report.
 * - Writes test result data and status to an Excel file.
 * - Applies conditional formatting (green/red) to Excel cells based on result.
 * 
 * Dependencies:
 * - TestNG (for ITestListener interface)
 * - ExtentReports (for report generation)
 * - Apache POI (for Excel operations via DataDrivenUtils)
 * - Selenium WebDriver (for screenshot capture)
 * 
 * Usage:
 * - Automatically invoked by TestNG during test execution lifecycle.
 * - Ensure `ScreenShot.screenShotTC()` and `DataDrivenUtils` are properly implemented.
 * - Configure `config.properties` and Excel file paths as needed.
 * 
 * -----------------------------------------------------------------------------
 */


public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test;
	public String filePath=System.getProperty("user.dir")+"\\reports\\miniProjectReport";
	
	
	public void onStart(ITestContext context) {
		DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy h-m-s");
		Date date = new Date();
		String reportpath = filePath+formatter.format(date)+".html";
		
		sparkReporter = new ExtentSparkReporter(reportpath);
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Alert Handling Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QEA");
		extent.setSystemInfo("Tester Name", "Adinath Khose");
		extent.setSystemInfo("OS", "Windows10");
		
	}
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is: "+result.getName());
		
		//Writing data into excel
		String[] message = ((String) result.getAttribute("successMessage")).split(":");
		int r = Integer.parseInt(message[0]);
		int c = Integer.parseInt(message[1]);
		DataDrivenUtils.writeDataIntoExcel(r, c, message[2]);
		DataDrivenUtils.writeDataIntoExcel( r, c+1, "PASS");
		DataDrivenUtils.fillGreenColor(r, c+1);
		
	}
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is: "+result.getName());
		test.log(Status.FAIL, "Test case FAILED cause: "+result.getThrowable());
		
		//Writing data into excel
		String[] message = (result.getThrowable().getMessage()).split(":");
		int r = Integer.parseInt(message[0]);
		int c = Integer.parseInt(message[1]);
		DataDrivenUtils.writeDataIntoExcel(r, c, message[2]);
		DataDrivenUtils.writeDataIntoExcel( r, c+1, "FAIL");
		DataDrivenUtils.fillRedColor(r, c+1);
		
		try {
			String screenshotPath = ScreenShot.screenShotTC(TestMain.driver, result.getName());
			test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPED is: "+result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
