package com.selenium.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.alertUtilities.DataDrivenUtils;
import com.selenium.alertUtilities.DriverSetup;
import com.selenium.alertUtilities.ScreenShot;
import com.selenium.objectRepository.ForgotPasswordPage;
import com.selenium.objectRepository.LoginPage;


public class TestMain {
	
	public static WebDriver driver;
	static DriverSetup objDriver;
	public static LoginPage homePgObj;
	public static ForgotPasswordPage forgotPasswordObj;
	
	@BeforeClass
	@Parameters({"browser"})
	public void driverConfig(String browser) {
		//Creating the driver instance
		objDriver = new DriverSetup();
		
		//opening the browser
		driver = objDriver.getDriver(browser);
		
		//creating objects for the web pages used in the workflow
		homePgObj = new LoginPage(driver);
		forgotPasswordObj = new ForgotPasswordPage(driver);
		
		//Taking the home page Screenshot
		try {
			ScreenShot.screenShotTC(driver, "Landing Page");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("Browser is opened");
	}
	
	@Test(priority=1)
	public void testEmptySignIn() {
		
		//Click the sign IN option to get alert
		homePgObj.clickSignIn();
		
		//fetching the alert text to verify message
		String alertText = homePgObj.getSignInAlertText();
		
		//verify the alert text
		boolean status = homePgObj.verifyAlert(alertText);
		
		//close the alert
		homePgObj.closeAlert();
	
		//passing the status to excel through listener
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("successMessage", "1:3:Appropriate sign in alert message is displayed");
		
		//Assertion
		Assert.assertTrue(status,"1:3:Invalid sign in alert message is displayed");
	}
	
	@Test(priority=2)
	public void testEmptyForgotPasswordFields() {
		
		//click forgot password option
		homePgObj.clickForgotPassword();
		try {
			ScreenShot.screenShotTC(driver, "ForgotPasswordPage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		//click next button on forgot password page
		forgotPasswordObj.clickNext();
		
		//fetch alert text to verify it
		String forgotAlertText = forgotPasswordObj.getAlertText();
		
		//verify the alert text
		boolean status = forgotPasswordObj.verifyAlert(forgotAlertText);
		
		//close the alert
		forgotPasswordObj.closeAlert();
		
		//navigate back to the home page
		forgotPasswordObj.navigateBack();
		
		//passing the status to excel through listener
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("successMessage", "2:3:Appropriate forgot password alert message is displayed");
		
		//Assertion
		Assert.assertTrue(status,"2:3:Invalid forgot password alert message is displayed");
	}
	
	@Test(priority=3)
	public void testPrivacyPolicyTab() {
		//click privacy policy present at bottom of page
		homePgObj.clickPrivacyPolicy();
		
		//take screenshot of new tab
		try {
			ScreenShot.screenShotTC(driver, "Privacy policy page");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		//verify the new tab
		boolean status = homePgObj.verifyNewTab();

		//passing the status to excel through listener
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("successMessage", "3:3:Privacy policy opened in new tab");
		
		//Assertion
		Assert.assertTrue(status,"3:3:Privacy policy not opened in new tab");
		
	}

	@AfterClass
	public void tearDown() {
		//close the browser
		objDriver.closeDriver();
		System.out.println("Closed the browser");
	}
}
