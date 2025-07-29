
package com.test.Utilities;
 
import org.testng.ITestListener;
 
import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;
 
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;

import org.testng.ITestResult;
 
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter; 

	public ExtentReports extent;  

	public ExtentTest test; 

	private static WebDriver driver;
 
	public void onStart(ITestContext context) {

		 String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

	     String reportPath = System.getProperty("user.dir") + "/reports/TestReport_" + timestamp + ".html";

	     sparkReporter = new ExtentSparkReporter(reportPath);

		sparkReporter.config().setDocumentTitle("Automation Report"); 

		sparkReporter.config().setReportName("EBay"); 

		sparkReporter.config().setTheme(Theme.DARK);

		extent=new ExtentReports();

		extent.attachReporter(sparkReporter);

		String browserName = context.getCurrentXmlTest().getParameter("browser");

		extent.setSystemInfo("Computer Name","localhost");

		extent.setSystemInfo("Environment","QA");

		extent.setSystemInfo("Tester Name","Janhavi Tripathi");

		extent.setSystemInfo("os","Windows11");

		extent.setSystemInfo("Browser Name", browserName);

        extent.setSystemInfo("Test Execution Time", timestamp);

	}
 
 
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName());

		  String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		test.log(Status.PASS, "Test case PASSED is:" + result.getName()); 

		test.log(Status.INFO, "Execution Time: " + timestamp);

	}
 
	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());

		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());

		  String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 

		test.log(Status.INFO, "Execution Time: " + timestamp);

		 String screenshotPath = captureScreenshot(result.getName());

	     test.addScreenCaptureFromPath(screenshotPath);

	}
 
	public void onTestSkipped(ITestResult result) {
 
		test = extent.createTest(result.getName());

		  String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());

		test.log(Status.INFO, "Execution Time: " + timestamp);

	}
 
	

	public void onFinish(ITestContext context) {

		extent.flush();

	}


	private String captureScreenshot(String fileName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String filepath = System.getProperty("user.dir") + "\\ScreenShots\\" + fileName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
        try {

            File dest = new File(filepath);

            org.openqa.selenium.io.FileHandler.copy(src, dest);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return filepath;

    }
 
    public static void setDriver(WebDriver webDriver) {

        driver = webDriver;

    }

}

 