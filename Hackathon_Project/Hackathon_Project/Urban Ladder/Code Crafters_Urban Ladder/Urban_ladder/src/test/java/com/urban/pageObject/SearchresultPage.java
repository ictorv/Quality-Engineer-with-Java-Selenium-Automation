package com.urban.pageObject;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.urban.utillities.WaitUtils;

public class SearchresultPage extends BasePage {

	public SearchresultPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//ul[@class='productlist small-block-grid-3']/li[1]//span[@class='name']")
	WebElement FirstItemChair;

	public void clickFirstItem() {
		WaitUtils.waitForVisibility(driver, FirstItemChair, 10);
		FirstItemChair.click();
	}

	public String getFirstItemName() {
		String chairName = FirstItemChair.getText();
		return chairName;
	}
	
	public void switchWindow() {
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String win : allWindows) {
			if (!win.equals(parentWindow)) {
				driver.switchTo().window(win);
				break;
			}
		}
	}
}

//ul[@class='productlist small-block-grid-3']/li[1]//span[@class='name']
//span[normalize-space()='Willow Accent Chair in Multi Coloured Colour']
