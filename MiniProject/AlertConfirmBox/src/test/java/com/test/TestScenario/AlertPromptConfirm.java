package com.test.TestScenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.BrowserConfiguration.DriverConfig;
import com.test.BrowserConfiguration.ExtentReportManager;
import com.test.HandleAlertConfirmPrompt.HandleAlertWithOkCancel;
import com.test.HandleAlertConfirmPrompt.HandleAlertWithOk;
import com.test.HandleAlertConfirmPrompt.HandleAlertWithTextBox;
import com.test.screenshot.Screenshot;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import excelTest.ExcelTest;
public class AlertPromptConfirm {
	
	// Declare WebDriver and other helper classes
    WebDriver driver;
    DriverConfig dc;
    ExcelTest et;
    List<String> OutputData = new ArrayList<>();
    String operation = "";
    ExtentReportManager erm;

    // Constants for Excel file paths and sheet name
    final String excelFilePathToRead = "src\\test\\java\\testDataFiles\\testDataExcel.xlsx";
    final String sheetName = "Sheet1";
    final String excelFilePathToWrite = "src\\test\\java\\testDataFiles\\testDataExcel.xlsx";
    Object[][] excelData;

    // Setup method, runs before the class — initializes Excel and WebDriver
    @BeforeClass
    @Parameters("browserName")
    public void setup(String browserName) {
        try {
            et = new ExcelTest(excelFilePathToRead, sheetName); // Load Excel sheet
            et.excelDataFetch(); // Fetch data
            excelData = et.getExcel(); // Store into a 2D array

            dc = new DriverConfig(browserName); // Browser setup
            driver = dc.driverInitialization(); // Launch browser
            dc.SwitchingToAlert(); // Navigate to alert testing page
        } catch (Exception e) {
            System.out.println("Setup failed: " + e.getMessage());
        }
    }

    // Test method for handling simple alert with "OK" button
    @Test(priority = 1)
    public void testAlertWithOk() {
        HandleAlertWithOk hawk = new HandleAlertWithOk(driver);
        OutputData.add(hawk.handleAlert());
    }

    // Test method for handling alert with "OK" and "Cancel"
    @Test(priority = 2)
    public void testAlertWithOkCancel() {
        HandleAlertWithOkCancel hawkc = new HandleAlertWithOkCancel(driver);
        OutputData.add(hawkc.handleAlertOKAndCancel());
    }

    // Test method for handling alert with a textbox (prompt)
    @Test(priority = 3)
    public void testAlertWithTextBox() {
        String name = excelData[1][1].toString(); // Retrieve input from Excel
        HandleAlertWithTextBox hawtb = new HandleAlertWithTextBox(driver, name);
        OutputData.add(hawtb.handlePrompt());
    }

    // Runs once before any test — initialize reporting
    @BeforeTest
    public void startReporter() {
        erm = new ExtentReportManager();
        erm.createTest("Handling Alert Boxes"); // Log test case name
    }

    // After each test — update result in report
    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            String screenshotPath = Screenshot.ScreenShot(driver, result.getName());
            erm.testLogger.fail("Test Case Failed Snapshot is below " + erm.testLogger.addScreenCaptureFromPath("." + screenshotPath));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            erm.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.SKIP) {
            erm.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
    }

    // After all test methods — save results to Excel and close browser
    @AfterClass
    public void tearDown() {
        try {
            et.excelDataWrite(OutputData, excelFilePathToWrite);
            dc.driverShutDown();
            System.out.println("Driver shutdown completed.");
        } catch (Exception e) {
            System.out.println("Teardown failed: " + e.getMessage());
        }
    }

    // After the entire suite — flush report to disk
    @AfterTest
    public void flushReport() {
        erm.flushReports();
    }

}
