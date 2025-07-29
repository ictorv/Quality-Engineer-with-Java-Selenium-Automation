package userDefinedLibraries;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import driverConfiguration.DriverSetup;
import testObjectRepository.DemoQaFormWindow;

public class ExtentReportManager implements ITestListener {

	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest tests;

	public void onStart(ITestContext context) {

		// Generate unique time stamp report name
		String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		
		// getting the current browser name
	    String browserName = context.getCurrentXmlTest().getParameter("browser");

		// setting report file storing path
		sparkReporter = new ExtentSparkReporter("reports/"+browserName+"_testReports_" + folderName + ".html");

		// Report appearance configuration
		sparkReporter.config().setDocumentTitle("Automation MiniProject Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Add data information to the report
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "Tharun");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser Name", "Chrome or Edge");

	}

	public void onTestSuccess(ITestResult result) {

		tests = extent.createTest(result.getName());
		tests.log(Status.PASS, "Test case passed is :" + result.getName());

		// Retrieve WebDriver from test context
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

		if (driver != null) {
			// Capture and attach screenshot to the report
			String screenshotPath = TakeScreenShot.screenShotTCPass(driver, result.getName());
			try {
				tests.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				tests.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
			}
			// If this specific test ran, validate and close modal if visible
			if ("testFormSubmissionWithJSE".equals(result.getName())) {
				DemoQaFormWindow formPage = new DemoQaFormWindow(driver);
				try {
					if (formPage.getModalTitle().equals("Thanks for submitting the form")) {
						formPage.closeFormModal();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		} else {
			tests.log(Status.WARNING, "WebDriver was null. Screenshot not captured.");
		}
	}

	public void onTestFailure(ITestResult result) {

		tests = extent.createTest(result.getName());
		tests.log(Status.FAIL, "Test case failed is :" + result.getName());
		tests.log(Status.FAIL, "Test case failed cause is :" + result.getThrowable());

		// Retrieve WebDriver from test context
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

		if (driver != null) {
			// Scroll up to full view for screenshot
			DriverSetup driverSetup = new DriverSetup();
			driverSetup.windowscroll(driver, -9999);
			// Capture and attach screenshot to the report
			DemoQaFormWindow formPage = new DemoQaFormWindow(driver);
			String screenshotPath = TakeScreenShot.screenShotTCFail(driver, result.getName());
			tests.addScreenCaptureFromPath(screenshotPath);
			formPage.clearFormFields();
		} else {
			tests.log(Status.WARNING, "WebDriver was null. Screenshot not captured.");
		}

	}

	// Execute when test case is skipped
	public void onTestSkipped(ITestResult result) {

		tests = extent.createTest(result.getName());
		tests.log(Status.SKIP, "Test case skipped is :" + result.getName());

	}

	// Execute when all tests have run
	public void onFinish(ITestContext context) {

		extent.flush();

	}

}
