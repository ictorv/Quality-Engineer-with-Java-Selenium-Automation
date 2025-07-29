
package com.selenium.mainTest;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.selenium.pages.ChildWindow;
import com.selenium.pages.ParentWindow;
import com.selenium.rediffUtilities.DriverSetup;
import com.selenium.rediffUtilities.ExtentReportManager;
import com.selenium.rediffUtilities.ScreenShot;
import com.selenium.rediffUtilities.WaitUtils;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.selenium.data.ExcelUtils;
import com.selenium.data.Constants;

public class TestMain 
{

    public WebDriver driver;
    DriverSetup objDriver;
    ExcelUtils eu;
    Object[][] mapper;
    ExtentReportManager erm;
    
    // Reading Data From Excel Sheet
    @BeforeTest
    public void testDataRead() 
    {
        eu = new ExcelUtils(Constants.EXCEL_FILE);
        int rows = eu.getRowCount(Constants.SHEET_Validation);
        int cols = eu.getColumnCount(Constants.SHEET_Validation);

        mapper = new Object[rows][cols];

        for (int i = 1; i < rows; i++) 
        {
            for (int j = 0; j < cols - 1; j++) 
            {
                mapper[i][j] = eu.getCellData(Constants.SHEET_Validation, i, j);
            }
        }
    }

    // Browser Configuration
    @BeforeClass
    @Parameters("browser")
    public void driverConfig(String browser) 
    {
        objDriver = new DriverSetup();
        driver = objDriver.driverInstantiate(browser);
        System.out.println("Opened the URL in the " + browser + " browser");
    }

    // Instantiate Extent Report Manager
    @BeforeTest
    @Parameters("browser")
    public void startReporter(String browser) 
    {
        erm = new ExtentReportManager();
        erm.setBrowserName(browser);
        erm.createTest("Rediff.com Validation");
    }

    // Running all the test cases
    @Test
    public void radiff() throws InterruptedException, IOException 
    {
        int i = 1, j = 1;
        try 
        {
            String parent_expectedLink = mapper[i][j].toString();
            String child_expectedLink = mapper[i + 1][j].toString();
            String child_expectedTitle = mapper[i + 2][j].toString();

            // Home Page opening
            ParentWindow pw = new ParentWindow(driver, eu, erm);
            ScreenShot.screenShotTC(driver, "01_Homepage");
            erm.logPass("Home page open");

            // Clicking on create account
            pw.createAcccountClick();
            erm.logPass("Clicked on Create Account");
            ScreenShot.screenShotTC(driver, "02_CreateAccount_page");

            // Validating the create account is opened or not
            pw.createAccountValidation(parent_expectedLink, Constants.SHEET_Validation, "CreateAccountValidation");

            // Finding total available links in the website & Printing it on Excel
            pw.noOfLinks(Constants.SHEET_LINKS);
            erm.logPass("Number of links available");
            WaitUtils.waitForDuration(driver, 2);

            // Clicking on Terms and Condition
            pw.termsAndCondition();
            erm.logPass("Clicked on Terms and Condition");
            ScreenShot.screenShotTC(driver, "03_T&C");

            // Switching to Child Window and Validating
            pw.switchToChildWindow(child_expectedLink, Constants.SHEET_Validation, "ChildWindowLinkValidation");

            ChildWindow cw = new ChildWindow(driver, eu, erm);
            WaitUtils.waitForDuration(driver, 2);

            // Validating Child Window Title
            cw.childWindowValidation(child_expectedTitle, Constants.SHEET_Validation, "ChildWindowTitleValidation");
            ScreenShot.screenShotTC(driver, "04_Child_page");

            // Closing Child window
            cw.closeChildWindow();
            erm.logPass("Closed child window");

            WaitUtils.waitForDuration(driver, 2);

            // Focus back to Parent Window
            cw.switchToParentWindow(pw.parentHandle);
            erm.logPass("Switched back to parent window");

        } 
        catch (Exception e) 
        {
            // Log the failure in Extent Report
            erm.testLogger.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
            e.printStackTrace();

            // Rethrow to ensure TestNG marks the test as failed
            throw new RuntimeException("Test failed due to exception", e);
        }
    }


    // Report from Extent Report Manager
    @AfterMethod
    public void getResult(ITestResult result) 
    {
        if (result.getStatus() == ITestResult.FAILURE) 
        {
            erm.testLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
        } 
        else if (result.getStatus() == ITestResult.SUCCESS) 
        {
            erm.testLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } 
        else if (result.getStatus() == ITestResult.SKIP) 
        {
            erm.testLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
    }

    // Close the browser
    @AfterClass
    public void tearDown() 
    {
        if (driver != null)
        {
            objDriver.driverTearDown();
        }
        eu.closeFile();
    }

    // Close the Extent Flush Report Manager
    @AfterTest
    @Parameters("browser")
    public void flushReport(String browser) 
    {
        System.out.println("Total Failed Validations in "+ browser +" : " + eu.failedValidationCount);
        erm.testLogger.info("Total Failed Validations: " + eu.failedValidationCount);

        erm.flushReports();
    }
}
