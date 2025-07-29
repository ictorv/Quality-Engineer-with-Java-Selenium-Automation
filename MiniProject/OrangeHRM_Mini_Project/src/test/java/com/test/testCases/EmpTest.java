package com.test.testCases;

/**
 * TestNG-based test class for executing end-to-end Employee Management scenarios.
 * Covers login, employee creation, editing, and logout.
 * Integrates ExtentReports for logging and uses Excel for test data.
 */


import org.openqa.selenium.WebDriver;
//import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.test.utilities.DriverSetup;
import com.test.pages.MainDashboard;
import com.test.pages.EmpDetailsPage;
import com.test.pages.EmpValidation;
import com.test.pages.LoginPage;
import com.test.pages.LogOut;
import com.test.pages.PIMPage;
import com.test.utilities.ConfigReader;
import com.test.data.ExcelDataSheet;
import com.test.utilities.ExtentReportsManager;
import com.test.utilities.ScreenShot;
import com.test.utilities.WaitUtil;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.*;

//import com.aventstack.extentreports.Status;
import com.test.utilities.*;
//import com.test.pages.*;

public class EmpTest {

    private WebDriver driver;
    private final String excelPath = "src/test/resources/testdata.xlsx";
    private final String sheetName = "Sheet1";

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        ExtentReportsManager.initReport();
        driver = DriverSetup.getDriver(browser);
        TestListener.setDriver(driver); // Set driver for listener
        ScreenShot.captureScreenshot(driver, "Browser_Launched");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testAddEmployee() {
        ExtentReportsManager.createTest("Add Employee Test").log(Status.INFO, "Starting Add Employee Test");

        String firstName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 0);
        String middleName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 1);
        String lastName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 2);
        String empUsername = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 3);
        String empPassword = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 4);
        String empConfirmPassword = empPassword;
        String fullName = firstName + " " + middleName + " " + lastName;

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.loginToApp(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        ExtentReportsManager.getTest().log(Status.PASS, "Logged in successfully");

        MainDashboard dashboard = new MainDashboard(driver);
        dashboard.goToEmployeeListPage();
        ExtentReportsManager.getTest().log(Status.PASS, "Navigated to Employee List Page");

        EmpValidation employeeValidator = new EmpValidation(driver);
        employeeValidator.deleteIfEmployeeExists(fullName);
        ExtentReportsManager.getTest().log(Status.PASS, "Checked and deleted existing employee if present");

        dashboard.goToAddEmployeePage();
        ExtentReportsManager.getTest().log(Status.PASS, "Navigated to Add Employee Page");

        PIMPage pimPage = new PIMPage(driver);
        pimPage.enterEmployeeName(firstName, middleName, lastName);
        ExtentReportsManager.getTest().log(Status.PASS, "Entered employee name");

        pimPage.clickCreateLoginDetails();
        ExtentReportsManager.getTest().log(Status.PASS, "Enabled Create Login Details toggle");

        pimPage.enterLoginDetails(empUsername, empPassword, empConfirmPassword);
        ExtentReportsManager.getTest().log(Status.PASS, "Entered login credentials");

        pimPage.clickSave();
        ExtentReportsManager.getTest().log(Status.PASS, "Clicked Save to add employee");
    }

    @Test(priority = 2)
    public void testEditEmployeeDetails() {
        ExtentReportsManager.createTest("Edit Employee Details Test").log(Status.INFO, "Starting Edit Employee Test");

        String gender = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 7);
        String maritalStatus = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 8);

        EmpDetailsPage empDetailsPage = new EmpDetailsPage(driver);
        empDetailsPage.selectNationality();
        ExtentReportsManager.getTest().log(Status.PASS, "Selected nationality");

        empDetailsPage.selectGender(gender);
        ExtentReportsManager.getTest().log(Status.PASS, "Selected gender");

        empDetailsPage.selectMaritalStatus(maritalStatus);
        ExtentReportsManager.getTest().log(Status.PASS, "Selected marital status");

        empDetailsPage.clickSave();
        ExtentReportsManager.getTest().log(Status.PASS, "Saved updated employee details");
    }

    @Test(priority = 3)
    public void testLogout() {
        ExtentReportsManager.createTest("Logout Test").log(Status.INFO, "Starting Logout Test");

        LogOut logoutPage = new LogOut(driver);
        logoutPage.logout();
        ExtentReportsManager.getTest().log(Status.PASS, "Logout action performed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            WaitUtil.applyImplicitWait(driver, 10);
            DriverSetup.quitDriver(driver);
        }
    }
}
