package com.test.utilities;

/**
 * Utility class for initializing and managing ExtentReports.
 * Creates timestamped HTML reports with customizable themes.
 * Provides methods to create and log test steps.
 */


import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter spark;

    // Initialize Extent report with timestamped output path and theme
    public static ExtentReports initReport() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/reports/TestReport_" + timeStamp + ".html";

        spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("Employee Automation Report");
        spark.config().setDocumentTitle("Test Execution Report");
        spark.config().setTheme(Theme.DARK);
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }

    // Create a new test node in the Extent report
    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    // Get the current active test instance
    public static ExtentTest getTest() {
        return test;
    }

    // Finalize and write the report to disk
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

