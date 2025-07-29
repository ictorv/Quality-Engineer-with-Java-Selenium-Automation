package com.TestUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.openqa.selenium.WebDriver;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Class to manage ExtentReports setup and logging
public class ExtentReportManager {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentreport;
	public ExtentTest testLogger;
	WebDriver driver;
 
	public ExtentReportManager() {
		String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String reportFolderPath = System.getProperty("user.dir") + "\\reports\\";
 
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath + "\\DropdownMenu_"+  folderName+".html");
		 // Configure the appearance and metadata of the report
		sparkReporter.config().setDocumentTitle("Automation Extent Report");
		sparkReporter.config().setReportName("DropdownMenu");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");
		extentreport = new ExtentReports();
		extentreport.attachReporter(sparkReporter);
		
	}
	// Create a new test instance in the report with the given test name
	public void createTest(String testName) {
		testLogger = extentreport.createTest(testName);
	}
 
	public void logPass(String message) {
		testLogger.pass(message);
	}
 
	/*
	 * public void logFail(String message) { testLogger.fail(message); }
	 */
	 // Flush all the logged information to the final report file
	public void flushReports() {
		extentreport.flush();
	}
}