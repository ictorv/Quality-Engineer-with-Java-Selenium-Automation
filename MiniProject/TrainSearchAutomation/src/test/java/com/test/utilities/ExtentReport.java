package com.test.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*
 * This utility class manages the setup and usage of ExtentReports for generating detailed HTML reports of automated
 * test executions. It configures the report layout,initializes the reporting engine,and provide methods to log steps 
 * and flush the report.
 */

public class ExtentReport {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReport;
	public ExtentTest testLogger;
	WebDriver driver;
	
	public ExtentReport() {
		String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String reportFolPath = System.getProperty("user.dir") + "\\reports\\";
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolPath + "\\ERailReport_" + folderName + ".html");
		
		sparkReporter.config().setDocumentTitle("Automation Extent Report");
		sparkReporter.config().setReportName("ERail Test Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
	}
	
	public void createTest(String testName) {
		testLogger = extentReport.createTest(testName);
	}
	
	public void logPass(String message) {
		testLogger.pass(message);
	}
	
	public void flushReports() {
		extentReport.flush();
	}

}
