package com.selenium.rediffUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager 
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentreport;
	public ExtentTest testLogger;
	WebDriver driver;

	public ExtentReportManager() 
	{
		String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String reportFolderPath = System.getProperty("user.dir") + "\\reports\\";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath + "\\rediffReport_"+  folderName+".html");

		sparkReporter.config().setDocumentTitle("Automation Extent Report");
		sparkReporter.config().setReportName("Rediff Test Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");
		extentreport = new ExtentReports();
		extentreport.attachReporter(sparkReporter);
		
		
		extentreport.setSystemInfo("Computer Name","localhost");
		extentreport.setSystemInfo("Environment","QA");
		extentreport.setSystemInfo("Tester Name","Arpan Basak");
		extentreport.setSystemInfo("os","Windows11");
		//extentreport.setSystemInfo("Browser name","chrome");
	}

	public void setBrowserName(String browserName) 
	{
	    extentreport.setSystemInfo("Browser name", browserName);
	}



	public void createTest(String testName) 
	{
		testLogger = extentreport.createTest(testName);
	}

	public void logPass(String message) 
	{
		testLogger.pass(message);
	}

	
	 public void logFail(String message) 
	 { 
		 testLogger.fail(message); 
				 
	 }


	public void flushReports() {
		extentreport.flush();
	}
}
