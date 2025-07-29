package com.test.pages;

/**
 * Page Object class for handling Add Employee functionality under the PIM module.
 * Automates filling employee details, creating login credentials, and saving the form.
 * Updates Excel with generated usernames and captures screenshots.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Random;
import org.openqa.selenium.By;

import com.test.data.ExcelDataSheet;
import com.test.utilities.ScreenShot;
import com.test.utilities.WaitUtil;

public class PIMPage {
	
    private WebDriver driver;
    private static String excelPath = "src/test/resources/testdata.xlsx";
    private static String sheetName = "Sheet1"; // EmployeeData

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    
    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "middleName")
    private WebElement middleNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")
    private WebElement employeeId;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label")
    private WebElement createLoginDetailsToggle;

    @FindBy(xpath = "//label[text()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement empUsernameField;

    @FindBy(xpath = "//label[text()='Password']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement empPasswordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement empConfirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Enter employee names into respective fields
    public void enterEmployeeName(String firstName, String middleName, String lastName) {
        WaitUtil.waitForVisibility(driver, firstNameField).sendKeys(firstName);
        middleNameField.sendKeys(middleName);
        lastNameField.sendKeys(lastName);
        ScreenShot.captureScreenshot(driver, "Employee_Name_Entered");
    }

    // Extract the auto-generated employee ID
    public String extractId() {
        WaitUtil.waitForVisibility(driver, employeeId);
        String empId = employeeId.getAttribute("value");
        System.out.println("Generated Employee ID: " + empId);
        ScreenShot.captureScreenshot(driver, "Employee_ID_Extracted");
        return empId;
    }

    // Click on the "Create Login Details" toggle
    public void clickCreateLoginDetails() {
        WaitUtil.waitForClickability(driver, createLoginDetailsToggle).click();
        ScreenShot.captureScreenshot(driver, "Login_Details_Toggled");
    }
    
    // Enter employee login credentials
    public void enterLoginDetails(String username, String password, String confirmPassword) {
        String newUsername = username;
        boolean usernameAccepted = false;

        empUsernameField.clear();
        empUsernameField.sendKeys(newUsername);
        

        // Wait briefly to allow error message to appear if any
        WaitUtil.applyImplicitWait(driver, 5);

        // Check for presence of the error message element
        if (driver.findElements(By.xpath("//label[contains(text(),'Username')]/parent::div/parent::div/span")).size() > 0) {
            // Append random number and retry
            newUsername = username + new Random().nextInt(9999);
        } else {
            usernameAccepted = true;
        }
        if (!usernameAccepted) {
            empUsernameField.clear();
            empUsernameField.sendKeys(newUsername);
        }

        empPasswordField.sendKeys(password);
        empConfirmPasswordField.sendKeys(confirmPassword);
        ScreenShot.captureScreenshot(driver, "Login_Details_Entered");

        // Update new username in Excel
        ExcelDataSheet.setCellData(excelPath, sheetName, 1, 3, newUsername);
    }
    
    // Click the Save button to store employee details
    public void clickSave() {
        WaitUtil.waitForClickability(driver, saveButton).click();
        ScreenShot.captureScreenshot(driver, "Employee_Details_Saved");
    }
}
