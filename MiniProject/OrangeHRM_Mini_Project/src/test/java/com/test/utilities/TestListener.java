package com.test.utilities;

/**
 * TestNG Listener class for managing test lifecycle events and ExtentReports integration.
 * Automatically logs test status (pass/fail/skip), captures screenshots on failure,
 * and attaches them to the Extent Report for enhanced debugging and traceability.
 */

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportsManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportsManager.getTest().log(Status.PASS, result.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver();
        String screenshotPath = ScreenShot.captureScreenshot(driver, result.getName() + "_Failure");

        ExtentReportsManager.getTest().log(Status.FAIL, result.getName() + " failed");
        ExtentReportsManager.getTest().log(Status.FAIL, result.getThrowable());

        try {
            ExtentReportsManager.getTest().fail("Screenshot of failure",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            ExtentReportsManager.getTest().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportsManager.getTest().log(Status.SKIP, result.getName() + " skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportsManager.flushReport();
    }
}
