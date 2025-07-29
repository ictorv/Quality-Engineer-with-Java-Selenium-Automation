package com.selenium.objectRepository;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * -----------------------------------------------------------------------------
 * File Name   : LoginPage.java
 * Package     : com.selenium.objectRepository
 * Author      : Adinath Khose
 * 
 * Description :
 * This class represents the Page Object Model (POM) for the login page of a 
 * web application. It encapsulates the web elements and actions related to 
 * login functionality, alert handling, and navigation to auxiliary pages like 
 * "Forgot Password" and "Privacy Policy".
 * 
 * Features:
 * - Clicks the login button and handles alert popups.
 * - Validates alert messages for login errors.
 * - Navigates to the "Forgot Password" page.
 * - Opens and verifies the "Privacy Policy" link in a new browser tab.
 * 
 * Dependencies:
 * - Selenium WebDriver
 * - Selenium PageFactory for element initialization
 * - WebDriverWait and ExpectedConditions for alert handling
 * - JavaScriptExecutor for scrolling into view
 * 
 * Usage:
 * - Instantiate this class by passing a WebDriver instance.
 * - Use `clickSignIn()` to trigger login.
 * - Use `getSignInAlertText()` and `verifyAlert()` to validate alert behavior.
 * - Use `clickForgotPassword()` to navigate to the forgot password page.
 * - Use `clickPrivacyPolicy()` to open the privacy policy in a new tab.
 * - Use `verifyNewTab()` to confirm tab switch and close it.
 * 
 * -----------------------------------------------------------------------------
 */


public class LoginPage {
	WebDriver driver;
	Alert alert;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Log In']") 
	WebElement loginBtn;
	
	@FindBy(xpath = "//a[normalize-space()='Forgot password?']")
	WebElement forgotPassword;	//text() and normalize-space
	
	@FindBy(xpath = "//a[normalize-space()='Privacy Policy']")
	WebElement privacyPolicy;
	
	public void clickSignIn() {
		loginBtn.click();
		System.out.println("Clicked the sign in button");
	}
	
	public String getSignInAlertText() {
		alert = wait.until(ExpectedConditions.alertIsPresent());
		System.out.println("Alert is displayed");
		return alert.getText();
	}
	
	public boolean verifyAlert(String alertText) {
		if(alertText.toLowerCase().contains("enter a valid user name")) {
			System.out.println("Appropriate alert is displayed");
			return true;
		}
		else {
			System.out.println("Appropriate alert is not displayed");
			return false;
		}
		
	}
	public void closeAlert() {
		alert.accept();	
		System.out.println("Alert is closed");
	}
	
	public void clickForgotPassword() {
		forgotPassword.click();
		System.out.println("Clicked forgot password");
	}
	
	public void clickPrivacyPolicy() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",privacyPolicy);
		//privacyPolicy.click();
		privacyPolicy.sendKeys(Keys.ENTER);
		
		System.out.println("Clicked privacy policy");
	}
	
	public boolean verifyNewTab() {
		String mainWindow = driver.getWindowHandle();
		Set<String> windowList = driver.getWindowHandles();
		for(String window: windowList) {
			if(!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				System.out.println("New tab opened with title: "+driver.getTitle());
				
				driver.close();
				return true;
			}
		}
		return false;
	}

	
	
}
