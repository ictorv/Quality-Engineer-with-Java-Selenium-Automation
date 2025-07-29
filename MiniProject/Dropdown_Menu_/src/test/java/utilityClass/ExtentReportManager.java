package utilityClass;


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
import testMain.Rediff_Main_Test;

//Implements ITestListener to hook into TestNG test lifecycle events
public class ExtentReportManager implements ITestListener{
	
	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest tests;
	
	// Called before any test starts
	public void onStart(ITestContext context) {
		
		String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		String browserName = context.getCurrentXmlTest().getParameter("browser");
		
		 // Configure the HTML report file path and settings
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\testReports " +browserName+"_"+folderName+".html");
		
		sparkReporter.config().setDocumentTitle("Automation MiniProject Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		// Initialize ExtentReports and attach the reporter
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		// Set basic system info to be displayed in the report
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Tester Name", "Satvik");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser Name", "Chrome or Edge");
		
	}
	
	// Called when a test method passes
	public void onTestSuccess(ITestResult result) {	
		tests = extent.createTest(result.getName());
		tests.log(Status.PASS, "Test case passed is :"+result.getName());
	}
	
	// Called when a test method fails
	public void onTestFailure(ITestResult result) {
		
		tests = extent.createTest(result.getName());
		tests.log(Status.FAIL, "Test case failed is :"+result.getName());
		tests.log(Status.FAIL, "Test case failed cause is :"+result.getThrowable());
		 
		// Capture screenshot on failure
	    WebDriver driver = Rediff_Main_Test.getDriver();
	    String screenshotPath = ScreenShot.takeScreenshot(driver, result.getName());
	    tests.addScreenCaptureFromPath(screenshotPath);
	}
	
	// Called when a test method is skipped
	public void onTestSkipped(ITestResult result) {
		tests = extent.createTest(result.getName());
		tests.log(Status.SKIP, "Test case skipped is :"+result.getName());
	}
	
	// Called once all tests are finished
	public void onFinish(ITestContext context) {
		// Write all information to the report file
		extent.flush();
		
	}


}
