/**
 * Page Class: OrganizationPage.java
 * 
 * Purpose:
 * - Manages organization location-related actions within the OrangeHRM application.
 * - Implements Page Object Model (POM) for structured automation.
 * - Supports adding and deleting locations dynamically.
 * - Captures screenshots during each significant UI interaction.
 *
 * Methods:
 * - findAndDeleteElement(boolean flag, String locationName): Searches for a location and deletes it if found.
 * - addAndDeleteElement(String locationName, String countryName): Adds a new location entry and then deletes it after validation.
 */


package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ScreenShot;
import utils.WaitUtil;

public class OrganizationPage {

	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addBtn;
	
	@FindBy(xpath="//label[text()='Name']/following::input[1]")
	WebElement nameInput;
	
	@FindBy(xpath="//div[contains(@class,'orangehrm-horizontal-padding') and contains(@class,'orangehrm-vertical-padding')]")
	WebElement noElementExcep;
	
	@FindBy(className="oxd-select-text-input")
	WebElement countryBtn;
	
	@FindBy(className="oxd-select-option")
	List<WebElement> countryOptions;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='oxd-table-row oxd-table-row--with-border']/div[@class='oxd-table-cell oxd-padding-cell'][2]")
	List<WebElement> nameList;
	
	@FindBy(xpath="//i[contains(@class,'bi-trash')]")
	List<WebElement> deleteCategory;
	
	@FindBy(xpath="//button[text()=' Yes, Delete ']")
	WebElement confirmDltBtn;
	
	
public boolean findAndDeleteElement(boolean flag, String locationName) throws IOException {
		
	
		WaitUtil.waitForOneElement(driver,noElementExcep);
		if(noElementExcep.getText().equals("No Records Found")) {
			return flag;
		}
		WaitUtil.waitForMultipleElement(driver, nameList);
		for(int i=0;i<nameList.size();i++) {
			if(nameList.get(i).getText().equalsIgnoreCase(locationName)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", nameList.get(i));
				ScreenShot.screenShotTC(driver, "Location Before Deletion");
				deleteCategory.get(i).click();
				confirmDltBtn.click();
				WaitUtil.waitForOneElement(driver,noElementExcep);
				ScreenShot.screenShotTC(driver, "Location After Deletion");
				flag=true;
				break;
			}
		}
		return flag;
	}



public boolean addAndDeleteElement(String locationName,String countryName) throws IOException {
	WaitUtil.waitForOneElement(driver, addBtn);
	boolean flag = false;
	boolean objective = false;
	if(findAndDeleteElement(flag,locationName)) {
		objective = true;
	}
	else {
		addBtn.click();
		WaitUtil.waitForOneElement(driver, nameInput);
		nameInput.sendKeys(locationName);
		countryBtn.click();
		WaitUtil.waitForMultipleElement(driver, countryOptions);
		for(WebElement coun :countryOptions) {
			if(coun.getText().equalsIgnoreCase(countryName)) {
				coun.click();
				break;
			}
		}
		ScreenShot.screenShotTC(driver, "Location Addition");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
		ScreenShot.screenShotTC(driver, "Location Addition");
		submitBtn.click();
		WaitUtil.waitForMultipleElement(driver, nameList);
		for(int i=0;i<nameList.size();i++) {
			if(nameList.get(i).getText().equalsIgnoreCase(locationName)) {
				ScreenShot.screenShotTC(driver, "Location Before Deletion");
				deleteCategory.get(i).click();
				confirmDltBtn.click();
				objective = true;
				WaitUtil.waitForOneElement(driver,noElementExcep);
				ScreenShot.screenShotTC(driver, "Location After Deletion");
				break;
			}
		}
	}
	return objective;
}

	
	
	
	
}
