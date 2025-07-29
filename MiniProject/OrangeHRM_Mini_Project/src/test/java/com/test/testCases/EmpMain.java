package com.test.testCases;

/**
 * Main method-based test runner for executing the Employee workflow.
 * Performs login, validation, creation, editing, and logout for both Chrome and Edge.
 * Reads data from Excel and uses page objects for modular automation.
 */


import org.openqa.selenium.WebDriver;
import com.test.utilities.DriverSetup;
import com.test.pages.MainDashboard;
import com.test.pages.EmpDetailsPage;
import com.test.pages.EmpValidation;
import com.test.pages.LoginPage;
import com.test.pages.LogOut;
import com.test.pages.PIMPage;
import com.test.utilities.ConfigReader;
import com.test.data.ExcelDataSheet;
import com.test.utilities.WaitUtil;

public class EmpMain {
	
    private static String excelPath = "src/test/resources/testdata.xlsx";
    private static String sheetName = "Sheet1"; // EmployeeData

    public static void main(String[] args) {
        WebDriver driver = null;

        // For Chrome browser
        try {
            String firstName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 0);
            String middleName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 1);
            String lastName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 2);
            String empUsername = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 3);
            String empPassword = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 4);
            String empConfirmPassword = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 4);
            String gender = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 7);
            String maritalStatus = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 8);
            String fullName = firstName + " " + middleName + " " + lastName;

            driver = DriverSetup.getDriver(ConfigReader.getProperty("browser"));
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.openLoginPage();
            loginPage.loginToApp(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

            MainDashboard dashboard = new MainDashboard(driver);
            dashboard.goToEmployeeListPage();

            EmpValidation employeeValidator = new EmpValidation(driver);
            employeeValidator.deleteIfEmployeeExists(fullName);

            dashboard.goToAddEmployeePage();

            PIMPage pimPage = new PIMPage(driver);
            pimPage.enterEmployeeName(firstName, middleName, lastName);
            pimPage.clickCreateLoginDetails();
            pimPage.enterLoginDetails(empUsername, empPassword, empConfirmPassword);
            pimPage.clickSave();

            EmpDetailsPage empDetailsPage = new EmpDetailsPage(driver);

            empDetailsPage.selectNationality();
            empDetailsPage.selectGender(gender);
            empDetailsPage.selectMaritalStatus(maritalStatus);
            empDetailsPage.clickSave();

            System.out.println("Employee details added and edited successfully.");

            LogOut logoutPage = new LogOut(driver);
            logoutPage.logout();
            System.out.println("Test completed successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                WaitUtil.applyImplicitWait(driver, 5);
                DriverSetup.quitDriver(driver);
            }
        }

        // For Edge browser
        try {
            String firstName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 0);
            String middleName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 1);
            String lastName = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 2);
            String empUsername = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 3);
            String empPassword = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 4);
            String empConfirmPassword = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 4);
            String gender = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 7);
            String maritalStatus = ExcelDataSheet.getCellData(excelPath, sheetName, 1, 8);
            String fullName = firstName + " " + middleName + " " + lastName;

            driver = DriverSetup.getDriver(ConfigReader.getProperty("alternateBrowser"));
            driver.manage().window().maximize();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.openLoginPage();
            loginPage.loginToApp(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

            MainDashboard dashboard = new MainDashboard(driver);
            dashboard.goToEmployeeListPage();

            EmpValidation employeeValidator = new EmpValidation(driver);
            employeeValidator.deleteIfEmployeeExists(fullName);

            dashboard.goToAddEmployeePage();

            PIMPage pimPage = new PIMPage(driver);
            pimPage.enterEmployeeName(firstName, middleName, lastName);
            pimPage.clickCreateLoginDetails();
            pimPage.enterLoginDetails(empUsername, empPassword, empConfirmPassword);
            pimPage.clickSave();

            EmpDetailsPage empDetailsPage = new EmpDetailsPage(driver);

            // empDetailsPage.setDateOfBirth(dob);
            empDetailsPage.selectNationality();
            empDetailsPage.selectGender(gender);
            empDetailsPage.selectMaritalStatus(maritalStatus);
            empDetailsPage.clickSave();

            System.out.println("Employee details added and edited successfully.");

            LogOut logoutPage = new LogOut(driver);
            logoutPage.logout();
            System.out.println("Test completed successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                WaitUtil.applyImplicitWait(driver, 5);
                DriverSetup.quitDriver(driver);
            }
        }
    }
}