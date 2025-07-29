package com.test.pages;

/**
 * Page Object class for navigating the main dashboard of OrangeHRM.
 * Provides methods to access PIM module, Add Employee, and Employee List pages.
 * Centralizes top-level navigation actions.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.utilities.ScreenShot;
import com.test.utilities.WaitUtil;

public class MainDashboard {
	
    private WebDriver driver;

    public MainDashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(linkText = "Add Employee")
    private WebElement addEmployeeMenu;

    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
    private WebElement pimModuleLink;

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListLink;

    // Navigate from dashboard to Add Employee screen
    public void goToAddEmployeePage() {
        WaitUtil.waitForClickability(driver, pimMenu).click();
        ScreenShot.captureScreenshot(driver, "Navigated_To_PIM");

        WaitUtil.waitForClickability(driver, addEmployeeMenu).click();
        ScreenShot.captureScreenshot(driver, "Navigated_To_AddEmployee");
    }

    // Navigate from dashboard to Employee List screen
    public void goToEmployeeListPage() {
        WaitUtil.waitForClickability(driver, pimMenu).click();
        ScreenShot.captureScreenshot(driver, "Navigated_To_PIM");       
    }
}
