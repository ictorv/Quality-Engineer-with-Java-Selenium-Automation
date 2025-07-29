package com.selenium.objectRepository;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/*
 * -----------------------------------------------------------------------------
 * File Name   : ForgotPasswordPage.java
 * Package     : com.selenium.objectRepository
 * Created Date: 18th June, 2025
 * Author      : Somnath Patil
 * 
 * Description :
 * This class represents the Page Object Model (POM) for the "Forgot Password" 
 * functionality of a web application. It encapsulates the web elements and 
 * actions related to the forgot password page, including handling alerts.
 * 
 * Features:
 * - Interacts with the email input field and "Next" button.
 * - Waits for and retrieves alert messages.
 * - Verifies alert content for validation.
 * - Accepts alerts and navigates back to the previous page.
 * 
 * Dependencies:
 * - Selenium WebDriver
 * - Selenium PageFactory for element initialization
 * - WebDriverWait and ExpectedConditions for alert handling
 * 
 * Usage:
 * - Instantiate this class by passing a WebDriver instance.
 * - Use `clickNext()` to trigger the alert.
 * - Use `getAlertText()` and `verifyAlert()` to validate alert behavior.
 * - Use `closeAlert()` to accept the alert.
 * - Use `navigateBack()` to return to the previous page.
 * 
 * -----------------------------------------------------------------------------
 */

public class ForgotPasswordPage {
	WebDriver driver;
	Alert alert;
	WebDriverWait wait;

	public ForgotPasswordPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="txtlogin")
	WebElement emailText;
	
	@FindBy(name="next")
	WebElement nextBtn;
	
	public void clickNext() {
		nextBtn.click();
		System.out.println("Clicked next button");
	}
	
	public String getAlertText() {
		alert = wait.until(ExpectedConditions.alertIsPresent());
		System.out.println("Alert is displayed");
		return alert.getText();
	}
	
	public boolean verifyAlert(String alertText) {
		if(alertText.toLowerCase().contains("enter your email")) {
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
		System.out.println("Alert closed");
	}
	public void navigateBack() {
		driver.navigate().back();
		System.out.println("Navigated back to home page");
	}
}
