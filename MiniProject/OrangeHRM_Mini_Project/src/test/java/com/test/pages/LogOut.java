package com.test.pages;

/**
 * Page Object class for automating logout from the OrangeHRM application.
 * Interacts with the user dropdown and logout button.
 * Captures screenshots for traceability.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.utilities.ScreenShot;
import com.test.utilities.WaitUtil;

public class LogOut {
	
    private WebDriver driver;

    public LogOut(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Locators

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement userProfileDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutButton;

    // Perform logout action
    public void logout() {
        WaitUtil.waitForClickability(driver, userProfileDropdown).click();
        ScreenShot.captureScreenshot(driver, "UserProfile_Dropdown_Opened");

        WaitUtil.waitForClickability(driver, logoutButton).click();
        ScreenShot.captureScreenshot(driver, "User_LoggedOut");
    }
}
