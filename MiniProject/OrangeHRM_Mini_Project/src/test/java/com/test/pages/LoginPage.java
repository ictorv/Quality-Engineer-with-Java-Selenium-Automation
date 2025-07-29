package com.test.pages;

/**
 * Page Object class for handling login functionality in the OrangeHRM application.
 * Automates entering credentials and submitting the login form.
 * Captures screenshots at each step for reporting and debugging.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.utilities.ConfigReader;
import com.test.utilities.ScreenShot;
import com.test.utilities.WaitUtil;

public class LoginPage {
	
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    // Navigate to login page using base URL from config
    public void openLoginPage() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        ScreenShot.captureScreenshot(driver, "LoginPage_Opened");
    }

    // Enter credentials and click login
    public void loginToApp(String username, String password) {
        WaitUtil.waitForVisibility(driver, usernameField).clear();
        usernameField.sendKeys(username);
        ScreenShot.captureScreenshot(driver, "Username_Entered");

        WaitUtil.waitForVisibility(driver, passwordField).clear();
        passwordField.sendKeys(password);
        ScreenShot.captureScreenshot(driver, "Password_Entered");

        WaitUtil.waitForClickability(driver, loginButton).click();
        ScreenShot.captureScreenshot(driver, "Login_Clicked");
    }
}



