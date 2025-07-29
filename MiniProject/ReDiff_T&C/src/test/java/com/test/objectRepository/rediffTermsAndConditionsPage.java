package com.test.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.Utilities.ExtentReportManager;
import com.test.data.ExcelDataSheet;

public class rediffTermsAndConditionsPage {
	WebDriver driver;
	String expected_tcTitle;
	public static ExcelDataSheet es;
	public static ExtentReportManager em;
	
	//Constructor
	public rediffTermsAndConditionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	public void checkTitle() {
		//Checks the title of terms and conditions window
		String actual_tcTitle = driver.getTitle();
		expected_tcTitle = es.getCellData(1, 2);
		if(actual_tcTitle.equals(expected_tcTitle)) {
			System.out.println("The Title for Terms and Conditions page is valid");
		}else {
			System.out.println("The Title for Terms and Conditions Page in Invalid");
		}
		//Assert condition - Checks if the title matches with the expected title
		Assert.assertEquals(expected_tcTitle, actual_tcTitle);
	}
	
	public void closeWindow() {
		//Closes the terms and conditions window
		String parentWindow = rediffCreateAccountPage.parentWindow;
		driver.close();
		System.out.println("Closed Terms and Conditions Window");
		driver.switchTo().window(parentWindow);
		System.out.println("Switched back to Create Account window.");
	}
}
