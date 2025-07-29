/**
 * Page Class: QualificationPage.java
 * 
 * Purpose:
 * - Manages qualification skills in the OrangeHRM application.
 * - Implements the Page Object Model (POM) for structured automation.
 * - Supports adding and deleting qualification skills dynamically.
 * - Captures screenshots during critical interactions for debugging.
 *
 * Methods:
 * - findAndDeleteElement(boolean flag, String skillName): Searches for a skill and deletes it if found.
 * - addAndDeleteElement(String skillName): Adds a skill and deletes it immediately after verification.
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

public class QualificationPage {

	WebDriver driver;
	public QualificationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	
}
	
	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addBtn;
	
	@FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding') and contains(@class,'orangehrm-vertical-padding')]")
	WebElement noElementExcep;
	
	@FindBy(xpath="//label[text()='Name']/following::input")
	WebElement inputName;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='oxd-table-cell oxd-padding-cell' and @style='flex: 2 1 0%;']")
	List<WebElement> skillNames;
	
	@FindBy(xpath="//i[contains(@class,'bi-trash')]")
	List<WebElement> deleteCategory;
	
	@FindBy(xpath="//button[text()=' Yes, Delete ']")
	WebElement confirmDltBtn;
	
	
public boolean findAndDeleteElement(boolean flag, String skillname) throws IOException {
	
		WaitUtil.waitForOneElement(driver,noElementExcep);
		if(noElementExcep.getText().equals("No Records Found")) {
			return flag;
		}
		
		WaitUtil.waitForMultipleElement(driver, skillNames);
		for(int i=0;i<skillNames.size();i++) {
			if(skillNames.get(i).getText().equalsIgnoreCase(skillname)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", skillNames.get(i));
				ScreenShot.screenShotTC(driver, "Skills Before Deletion");
				deleteCategory.get(i).click();
				confirmDltBtn.click();
				flag=true;
				
				WaitUtil.waitForOneElement(driver,noElementExcep);
				ScreenShot.screenShotTC(driver, "Location After Deletion");
				break;
			}
		}
		return flag;
	}

public boolean addAndDeleteElement(String skillname) throws IOException {
	WaitUtil.waitForMultipleElement(driver, skillNames);
	boolean flag = false;
	boolean objective = false;
	if(findAndDeleteElement(flag,skillname)) {
		objective = true;
	}
	else {
		addBtn.click();
		WaitUtil.waitForOneElement(driver, inputName);
		inputName.sendKeys(skillname);
		ScreenShot.screenShotTC(driver, "Skills Addition");
		submitBtn.click();
		WaitUtil.waitForMultipleElement(driver, skillNames);
		for(int i=0;i<skillNames.size();i++) {
			if(skillNames.get(i).getText().equals(skillname)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", skillNames.get(i));
				ScreenShot.screenShotTC(driver, "Skills Before Deletion");
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
