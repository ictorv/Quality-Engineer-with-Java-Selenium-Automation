/**
 * Page Class: AdminPage.java
 * 
 * Purpose:
 * - Encapsulates UI interactions related to the Admin module in the OrangeHRM application.
 * - Implements Page Object Model (POM) for modular and maintainable test automation.
 * - Handles navigation to various admin pages dynamically.
 * - Supports user logout functionality with validation checks.
 * - Captures screenshots at critical interaction points for debugging.
 *
 * Methods:
 * - navigateToAdmin(): Navigates to the Admin module within the application.
 * - navigateToPage(String pageName, String specificOption): Dynamically navigates to a sub-section within Admin.
 * - logout(): Performs logout and verifies redirection to the login page.
 */

package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ScreenShot;
import utils.WaitUtil;

public class AdminPage {

	WebDriver driver;
	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//*[text()='Admin']") 
	WebElement adminBtn;
	
	@FindBy(xpath = "//span[@class='oxd-topbar-body-nav-tab-item']")
	List<WebElement> navigationBarOptions;
	
	@FindBy(className="oxd-topbar-body-nav-tab-link")
	List<WebElement> specificOptions;
	
	@FindBy(xpath="//i[contains(@class,'oxd-userdropdown-icon')]") 
	WebElement profileBtn;
	
	@FindBy(className="oxd-userdropdown-link")
	List<WebElement> dropdownOptions;
	
	@FindBy(className ="orangehrm-login-branding")
	WebElement logo;
	
	public void navigateToAdmin() {
		WaitUtil.waitForOneElement(driver, adminBtn);
		adminBtn.click();
	}
	
	
	public void navigateToPage(String pageName,String specficOption) throws IOException {
		WaitUtil.waitForMultipleElement(driver, navigationBarOptions);
		for(WebElement i:navigationBarOptions) {
			if(i.getText().equalsIgnoreCase(pageName)) {
				i.click();
				
				WaitUtil.waitForMultipleElement(driver, specificOptions);
				ScreenShot.screenShotTC(driver, pageName+" Options");
				for(WebElement j:specificOptions) {
					if (j.getText().equalsIgnoreCase(specficOption)) {
						j.click();
						break;
					}
				}
				break;
			}
		}
		ScreenShot.screenShotTC(driver,specficOption);
	}
	
	public boolean logout() throws IOException {
		WaitUtil.waitForOneElement(driver, profileBtn);
		profileBtn.click();
		WaitUtil.waitForMultipleElement(driver, navigationBarOptions);
		ScreenShot.screenShotTC(driver,"Logout Option");
		for(WebElement i : dropdownOptions) {
			
			if(i.getText().equalsIgnoreCase("Logout")) {
				i.click();
			}
		}
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			boolean isPresent = logo.isDisplayed();
			ScreenShot.screenShotTC(driver,"Logged Out");
			return isPresent;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}

	
}
