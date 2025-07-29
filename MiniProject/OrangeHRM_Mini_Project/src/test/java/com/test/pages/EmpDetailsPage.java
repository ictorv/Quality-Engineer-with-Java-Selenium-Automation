package com.test.pages;

/**
 * Page Object class for editing employee personal details in the OrangeHRM application.
 * Automates selection of nationality, gender, and marital status from dropdowns and radio buttons.
 * Used after employee creation to update profile information.
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.utilities.WaitUtil;

public class EmpDetailsPage {

    private WebDriver driver;

    public EmpDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[text()='Nationality']/following::div[contains(@class,'oxd-select-text')][1]/div[1]")
    private WebElement nationalityDropdown;

    @FindBy(xpath = "//input[@type='radio' and @value='1']/following-sibling::span")
    private WebElement genderMaleRadio;

    @FindBy(xpath = "//input[@type='radio' and @value='2']/following-sibling::span")
    private WebElement genderFemaleRadio;

    @FindBy(xpath = "//label[text()='Marital Status']//following::div[contains(@class, 'oxd-select-text-input')][1]")
    private WebElement maritalStatusDropdown;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Separate option locators
    private By indianNationalityOption = By.xpath("//div[@role='listbox']/div[@role='option']/span[text()='Indian']");
    private By maritalStatusSingleOption = By.xpath("//div[@role='listbox']/div[@role='option']/span[text()='Single']");
    private By maritalStatusMarriedOption = By.xpath("//div[@role='listbox']/div[@role='option']/span[text()='Married']");
    private By maritalStatusOtherOption = By.xpath("//div[@role='listbox']/div[@role='option']/span[text()='Other']");

    // Select nationality from dropdown
    public void selectNationality() {
        WaitUtil.waitForVisibility(driver, nationalityDropdown).click();
        WaitUtil.waitForClickability(driver, indianNationalityOption).click();
    }

    // Select gender radio button
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            WaitUtil.waitForClickability(driver, genderMaleRadio).click();
        } else if (gender.equalsIgnoreCase("Female")) {
            WaitUtil.waitForClickability(driver, genderFemaleRadio).click();
        }
    }

    // Select marital status from dropdown
    public void selectMaritalStatus(String status) {
        WaitUtil.waitForVisibility(driver, maritalStatusDropdown).click();
        WebElement selectedOption;
        if (status.equalsIgnoreCase("single")) {
            selectedOption = WaitUtil.waitForClickability(driver, maritalStatusSingleOption);
        } else if (status.equalsIgnoreCase("married")) {
            selectedOption = WaitUtil.waitForClickability(driver, maritalStatusMarriedOption);
        } else {
            selectedOption = WaitUtil.waitForClickability(driver, maritalStatusOtherOption);
        }
        selectedOption.click();
    }

    // Click the Save button to submit the form
    public void clickSave() {
        WaitUtil.waitForClickability(driver, saveButton).click();
    }
}