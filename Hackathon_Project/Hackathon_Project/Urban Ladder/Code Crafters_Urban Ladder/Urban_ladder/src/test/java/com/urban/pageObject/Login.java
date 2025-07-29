package com.urban.pageObject;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.urban.utillities.WaitUtils;
 
public class Login extends BasePage{
    public Login(WebDriver driver) {
		super(driver);
	}
    WebDriverWait wait;
 
    @FindBy(xpath = "//div[@id='password-credentials']//input[@id='spree_user_email']")
    WebElement emailField;
 
    @FindBy(xpath = "//div[@class='password-wrap']//input[@id='spree_user_password']")
    WebElement passwordField;
 
    @FindBy(xpath = "//*[@id=\"ul_site_login\"]")
    WebElement loginBtn;
    
    @FindBy(xpath = "//div[contains(text(),'The email or password you entered is incorrect. Pl')]")
    WebElement errorMessage;
    
    @FindBy(xpath = "//label[normalize-space()='This field is required.']")
    WebElement passwordError;
    
    @FindBy(xpath = "//label[@for='spree_user_email']")
    WebElement emailError;
 
 
    public void enterEmail(String email) {
//        wait.until(ExpectedConditions.elementToBeClickable(emailField)).sendKeys(email);
        WaitUtils.waitForClickability(driver, emailField, 10);
        emailField.sendKeys(email);
    }
 
    public void enterPassword(String password) {
//        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
        WaitUtils.waitForClickability(driver, passwordField, 10);
        passwordField.sendKeys(password);
    }
 
    public void submitLogin() {
//        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        WaitUtils.waitForClickability(driver, loginBtn, 10);
        loginBtn.click();
    }
    
    public String getErrorMessage() {
        return errorMessage.getText();
    }
    
    public String getPasswordErrorMessage() {
//    	return wait.until(ExpectedConditions.visibilityOf(passwordError)).getText();
    	WaitUtils.waitForVisibility(driver, passwordError, 10);
    	return passwordError.getText();
    }
    
    public String getEmailErrorMessage() {
//    	wait.until(ExpectedConditions.visibilityOf(emailError));
//    	return emailError.getText();
    	WaitUtils.waitForVisibility(driver, emailError, 10);
    	return emailError.getText();
    }
    
    public void clearEmailField() {
//        wait.until(ExpectedConditions.elementToBeClickable(emailField)).clear();
        WaitUtils.waitForClickability(driver, emailField, 10);
        emailField.clear();
    }
 
    public void clearPasswordField() {
//        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).clear();
        WaitUtils.waitForClickability(driver, passwordField, 10);
        passwordField.clear();
    }
 
}