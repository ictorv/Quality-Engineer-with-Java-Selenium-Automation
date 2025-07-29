
package UserDefinedLibraries;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentReportManager class handles the setup and management of ExtentReports for test logging.
 * It creates a timestamped HTML report using ExtentSparkReporter and provides methods to log test steps,
 * create test nodes, and flush the report after execution.
 * 
 * Features:
 * - Timestamped report generation
 * - Customizable report title, name, and theme
 * - Logging of test steps with pass status
 * - Integration with Selenium WebDriver (optional)
 * 
 * Dependencies:
 * - ExtentReports (com.aventstack.extentreports)
 * - Selenium WebDriver (optional for screenshot integration)
 */


public class ExtentReportManager {
	
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extentreport;
		public ExtentTest testLogger;
		WebDriver driver;

		public ExtentReportManager() {
			String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
			String reportFolderPath = System.getProperty("user.dir") + "\\reports\\";

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath + "\\FlipkartReport_"+  folderName+".html");

			sparkReporter.config().setDocumentTitle("Automation Extent Report");
			sparkReporter.config().setReportName("Zigly Test Report");
			sparkReporter.config().setTheme(Theme.STANDARD);
			sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");
			extentreport = new ExtentReports();
			extentreport.attachReporter(sparkReporter);
		}

		public void createTest(String testName) {
			testLogger = extentreport.createTest(testName);
		}

		public void logPass(String message) {
			testLogger.pass(message);
		}

		public void flushReports() {
			extentreport.flush();
		}

}
