/**
 * Page Class: JobPage.java
 * 
 * Purpose:
 * - Manages job category interactions in the OrangeHRM application.
 * - Implements Page Object Model (POM) to structure and maintain automation.
 * - Supports adding and deleting job categories dynamically.
 * - Captures screenshots at key points for debugging purposes.
 *
 * Methods:
 * - findAndDeleteElement(boolean flag, String categoryName): Checks for existing job category and deletes it if found.
 * - addAndDeleteElement(String categoryName): Adds a job category and deletes it immediately after verification.
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

public class JobPage {

	
	WebDriver driver;
	public JobPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addBtn;
	
	@FindBy(xpath="//div[contains(@class,'orangehrm-horizontal-padding') and contains(@class,'orangehrm-vertical-padding')]")
	WebElement noElementExcep;
	
	@FindBy(xpath="//label[contains(@class,'oxd-label')]/following::input[contains(@class,'oxd-input')]")
	WebElement inputName;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='oxd-table-cell oxd-padding-cell' and @style='flex-basis: 80%;']")
	List<WebElement> categoryName;
	
	@FindBy(xpath="//i[contains(@class,'bi-trash')]")
	List<WebElement> deleteCategory;
	
	
	@FindBy(xpath="//button[text()=' Yes, Delete ']")
	WebElement confirmDltBtn;
	
	
	public boolean findAndDeleteElement(boolean flag, String categoryname) throws IOException {
		
		WaitUtil.waitForOneElement(driver,noElementExcep);
		if(noElementExcep.getText().equals("No Records Found")) {
			return flag;
		}
		for(int i=0;i<categoryName.size();i++) {
			if(categoryName.get(i).getText().equalsIgnoreCase(categoryname)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", categoryName.get(i));
				ScreenShot.screenShotTC(driver, "Job Before Deletion");
				deleteCategory.get(i).click();
				confirmDltBtn.click();
				WaitUtil.waitForOneElement(driver,noElementExcep);
				ScreenShot.screenShotTC(driver, "Job After Deletion");
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public boolean addAndDeleteElement(String categoryname) throws IOException {
		WaitUtil.waitForOneElement(driver, addBtn);
		boolean objective = false;
		boolean flag = false;
		if(findAndDeleteElement(flag,categoryname)) {
			objective = true;
		}
		else {
			addBtn.click();
			WaitUtil.waitForOneElement(driver, inputName);
			inputName.sendKeys(categoryname);
			ScreenShot.screenShotTC(driver, "Job Category Adding");
			submitBtn.click();
			WaitUtil.waitForMultipleElement(driver, categoryName);
			for(int i=0;i<categoryName.size();i++) {
				if(categoryName.get(i).getText().equalsIgnoreCase(categoryname)) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);",categoryName.get(i));
					ScreenShot.screenShotTC(driver, "Job Before Deletion");
					deleteCategory.get(i).click();
					confirmDltBtn.click();
					objective = true;
					WaitUtil.waitForOneElement(driver,noElementExcep);
					ScreenShot.screenShotTC(driver, "Job After Deletion");
					break;
				}
			}
			
			
		}
		return objective;
	}
}
