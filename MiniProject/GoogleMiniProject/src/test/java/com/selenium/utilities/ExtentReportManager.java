package com.selenium.utilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest testLogger;
	WebDriver driver;
	//public String filePath=System.getProperty("user.dir")+"\\reports\\miniProjectReport";
	
	public ExtentReportManager() {
		String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String reportFolderPath = System.getProperty("user.dir") + "\\reports\\";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath + "\\GoogleSearch_"+  folderName+".html");

		sparkReporter.config().setDocumentTitle("Automation Extent Report");
		sparkReporter.config().setReportName("Bing Search Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
	}
	public void createTest(String testName) {
		// TODO Auto-generated method stub
		testLogger = extent.createTest(testName);
	}


	public void logPass(String message) {
		testLogger.pass(message);
	}

	/*
	 * public void logFail(String message) { testLogger.fail(message); }
	 */

	public void flushReports() {
		extent.flush();
	}


}
