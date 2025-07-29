/**
 * File Name: ExtentReportManager.java
 * Description:
 * This class manages the generation of ExtentReports for test execution using TestNG listeners.
 * It logs test results (pass, fail, skip) and captures screenshots on failure for detailed reporting.

 * Purpose:
 * Provides structured and visually rich test reports with execution details, timestamps, and screenshots.

 * Key Features:
 * - Initializes ExtentReports with browser-specific filenames and metadata.
 * - Logs test outcomes with status and execution time.
 * - Captures and attaches screenshots for failed tests.
 * - Flushes the report after all tests are completed.

 * Methods:
 * - onStart(ITestContext): Sets up the report configuration and metadata.
 * - onTestSuccess(ITestResult): Logs passed test details.
 * - onTestFailure(ITestResult): Logs failed test details and attaches a screenshot.
 * - onTestSkipped(ITestResult): Logs skipped test details.
 * - onFinish(ITestContext): Finalizes and writes the report.
 * - setDriver(WebDriver): Sets the WebDriver instance for screenshot capture.
 */

package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	private static WebDriver driver;

	public void onStart(ITestContext context) {

		String browserName = context.getCurrentXmlTest().getParameter("browser");

		String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "/reports/" + browserName + "_TestReport_" + timestamp + ".html";

		sparkReporter = new ExtentSparkReporter(reportPath);

		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Deleting Skill Sets");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "Eshika_Das");
		extent.setSystemInfo("os", "Windows10");

		extent.setSystemInfo("Browser Name", browserName);
		extent.setSystemInfo("Test Execution Time", timestamp);

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName());
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		test.log(Status.PASS, "Test case PASSED is: " + result.getName() + " Execution Time: " + timestamp);

	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable() + " Execution Time: " + timestamp);

		String screenshotPath = captureScreenshot(result.getName());
		test.addScreenCaptureFromPath(screenshotPath);

	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName() + " Execution Time: " + timestamp);

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
