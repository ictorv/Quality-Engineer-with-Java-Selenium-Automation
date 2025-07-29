package com.urban.pageObject;
 
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.urban.utillities.ExcelUtil;
import com.urban.utillities.WaitUtils;
 
public class BeingAtHome extends BasePage{
 
	public BeingAtHome(WebDriver driver) {
		super(driver);
	}
 
	@FindBy(xpath = ("//li[@data-group = 'category']//div[@class = 'gname']"))
	WebElement categoryBox;
 
	@FindBy(xpath = ("//li[@data-group = 'category']//div[2]//div[@class = 'filter-header']//span[text()='Primary Category']"))
	WebElement categoryHeading;
 
	@FindBy(xpath = ("//li[@data-group = 'category']//div[@data-filter-name = 'primary_category']//ul//li"))
	List<WebElement> categoryList;
 
	public void category() {
 
		wait.until(ExpectedConditions.visibilityOf(categoryBox));
		action.moveToElement(categoryBox).perform();
	}
 
	public List<WebElement> categoryItems() {
		int r=1;
//		wait.until(ExpectedConditions.visibilityOf(categoryList.get(0)));
		WaitUtils.waitForVisibility(driver, categoryHeading, 10);
		for (WebElement item : categoryList) {
			String itemName = item.getText();
			System.out.println(itemName);
			ExcelUtil.writeDataIntoExcel("BeingAtHome", r, 1, itemName);
			r++;
		}
//		System.out.println();
//		System.out.println("Total items : " + categoryList.size());
//		ExcelUtil.writeDataIntoExcel("BeingAtHome", r, 1,  String.valueOf(categoryList.size()));
		
		return categoryList;
		
	}
 
}
 