package com.test.objectRepository;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.data.ExcelDataSheet;

public class rediffCreateAccountPage {
	WebDriver driver;
	String createAccountLink;
	public static String parentWindow;
	public String columnName = "Total Links in Create Account Page";
	public static ExcelDataSheet es;
	
	//Constructor
	public rediffCreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath="//a") List<WebElement> total_links;
	@FindBy(xpath="//a[text()='terms and conditions']") WebElement link_tc;
	
	//Actions
	public void linkCount() {
		//Counts the number of links in create account page
		String noOfLinks = Integer.toString(total_links.size());
		try {
			es.setCellData(1, columnName, noOfLinks);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Total number of links in create account page: " + noOfLinks);
	}
	
	public void checkIfCAOpened() {
		//Checks if the create account page is opened or not
		createAccountLink = es.getCellData(2, 1);
		if(driver.getCurrentUrl().equals(createAccountLink)) {
			System.out.println("Create Account Page is Opened");
		}else {
			System.out.println("Create Account Page was failed to opened");
		}
		//Assert condition - Checks if current URL is equal to expected URL
		Assert.assertEquals(driver.getCurrentUrl(), createAccountLink);
	}
	
	public void clickTC() {
		//Clicks the Terms and Conditions Link
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", link_tc);
	}
	
	public void checkIfTCOpened() {
		//Checks if Terms and conditions window is opened
		Set<String> windowHandles = driver.getWindowHandles();
		if(windowHandles.size() > 1) {
			System.out.println("Terms and Conditions is opened");
		}else {
			System.out.println("Terms and Conditions was failed to open");
		}
		
	}
	
	public void switchWindowToTC() {
		//Switches from parent window to terms and conditions window
		Set<String> windowHandles = driver.getWindowHandles();
		parentWindow = driver.getWindowHandle();
		for (String handle : windowHandles) {
		    if (!handle.equals(parentWindow)) {
		        driver.switchTo().window(handle); // Switches to Terms and Conditions window
		        System.out.println("Switched to Terms and Conditions window.");
		        break;
		    }
		}
	}
	
}
