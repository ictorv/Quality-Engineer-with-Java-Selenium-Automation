package com.selenium.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/*
 * -----------------------------------------------------------------------------
 * File Name   : ExtentReportManager.java
 * Package     : com.selenium.alertUtilities
 * Created Date: 18th June, 2025
 * Author      : Lava Prasad G V
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


public class ExtentReportManager {
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest testLogger;
	public String filePath=System.getProperty("user.dir")+"\\reports\\CruiseReport";
	public WebDriver driver;
	
	public ExtentReportManager() {
		DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy h-m-s");
		Date date = new Date();
		String reportpath = filePath+formatter.format(date)+".html";
		
		sparkReporter = new ExtentSparkReporter(reportpath);
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Rhapsody of the Seas Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QEA");
		extent.setSystemInfo("Tester Name", "Lava Prasad");
		extent.setSystemInfo("OS", "Windows10");
		
	}
	
	public void createTest(String testName) {
		testLogger = extent.createTest(testName);
	}

	public void logPass(String message) {
		testLogger.pass(message);
	}

	
	public void flushReports() {
		extent.flush();
	}

}
