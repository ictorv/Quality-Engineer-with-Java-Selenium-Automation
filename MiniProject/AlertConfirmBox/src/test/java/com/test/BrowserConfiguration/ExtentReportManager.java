package com.test.BrowserConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager {
	
	// Reporter for generating the visual HTML report
    public ExtentSparkReporter sparkReporter;

    // Main report object that manages all report data
    public ExtentReports extentreport;

    // Logger to write test steps and results into the report
    public ExtentTest testLogger;

    // WebDriver reference (declared but not used here)
    WebDriver driver;

    // Constructor: Initializes and configures the HTML report
    public ExtentReportManager() {
        // Generate a timestamped folder name for unique report filenames
        String folderName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());

        // Define where the report HTML file will be saved
        String reportFolderPath = System.getProperty("user.dir") + "\\reports\\";

        // Create the Spark HTML report file with a timestamped name
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
            reportFolderPath + "\\AlertConfirmPromptHandling" + folderName + ".html"
        );

        // Configure visual and metadata details of the report
        sparkReporter.config().setDocumentTitle("Automation Extent Report");
        sparkReporter.config().setReportName("Alert Confirm Prompt Testing");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");

        // Create and link the ExtentReports manager to the Spark report
        extentreport = new ExtentReports();
        extentreport.attachReporter(sparkReporter);
    }

    // Method to create a test logger entry with a given test name
    public void createTest(String testName) {
        testLogger = extentreport.createTest(testName);
    }

    // Method to log a passed step in the report
    public void logPass(String message) {
        testLogger.pass(message);
    }

    /*
     * Method to log a failed step in the report (commented out for now)
     * public void logFail(String message) {
     *     testLogger.fail(message);
     * }
     */

    // Finalize and save the report to disk
    public void flushReports() {
        extentreport.flush();
    }

}
