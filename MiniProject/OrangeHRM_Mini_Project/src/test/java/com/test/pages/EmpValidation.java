package com.test.pages;

/**
 * Page Object class for validating and removing existing employees.
 * Searches for an employee by name and deletes if found.
 * Ensures clean test setup by avoiding duplicate entries.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.utilities.WaitUtil;

public class EmpValidation {
	
    private WebDriver driver;

    // Constructor: Initialize PageFactory elements
    public EmpValidation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//label[text()='Employee Name']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@placeholder='Type for hints...']")
    private WebElement employeeNameInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[contains(@class, 'oxd-icon-button')]/i[contains(@class, 'bi-trash')]")
    private WebElement deleteIcon;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-modal-footer')]/button/i[contains(@class, 'oxd-icon bi-trash oxd-button-icon')]")
    private WebElement confirmDeleteButton;

    // Action Method: Delete if employee exists
    public void deleteIfEmployeeExists(String employeeName) {
        try {
            // Wait for the input box before typing
            WaitUtil.waitForVisibility(driver, employeeNameInput).sendKeys(employeeName);

            // Click the search button after input
            WaitUtil.waitForClickability(driver, searchButton).click();
            searchButton.click();
            
            WaitUtil.applyImplicitWait(driver, 10);

            // If the delete icon appears, proceed with deletion
            if (WaitUtil.waitForVisibility(driver, deleteIcon).isDisplayed()) {
                System.out.println("[INFO] Employee already exists. Removing...");

                WaitUtil.waitForClickability(driver, deleteIcon).click();
                WaitUtil.waitForClickability(driver, confirmDeleteButton).click();

                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("No existing employee found.");
            }

        } catch (Exception e) {
            System.out.println("Employee not found or elements not loaded. Skipping deletion.");
            System.out.println(e.getMessage());
        }
    }
}
