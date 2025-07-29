package com.urban.pageObject;
 
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.urban.utillities.WaitUtils;
 
public class BookshelfPage extends BasePage{
	public BookshelfPage(WebDriver driver) {
		super(driver);
	}
 
	@FindBy(xpath="//li[@data-group='price']")
	WebElement price;
	
	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
	WebElement priceSlider;
 
	@FindBy(xpath = "//ul[@class='grouplist clearfix']/li[2]")
	WebElement openStorageHover;
 
	@FindBy(id ="filters_storage_type_Open")
	WebElement openStorageCheckbox;
 
	@FindBy(id = "filters_availability_In_Stock_Only")
	WebElement inStockCheckbox;
 
	@FindBy(xpath = "//span[@class='name']")
	List<WebElement> productNames;
 
	@FindBy(xpath = "//div[@class='price-number']/span")
	List<WebElement> productPrices;
	
 
	public void setPriceFilter()  {
		action.moveToElement(price).perform();
		WaitUtils.waitForVisibility(driver, priceSlider, 10);
		action.dragAndDropBy(priceSlider, -232, 0).build().perform();
	}
 
	public void selectOpenStorageType() {
		action.moveToElement(openStorageHover).build().perform();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOf(openStorageCheckbox));
//		WaitUtils.waitForVisibility(driver, openStorageCheckbox, 10);
		js.executeScript("arguments[0].click()", openStorageCheckbox);
	}
 
	public void filterInStock() {
		WaitUtils.waitForClickability(driver, inStockCheckbox, 10);
		js.executeScript("arguments[0].click()", inStockCheckbox);
//		inStockCheckbox.click();
	}
 
	public LinkedHashMap<String, String> getTopProducts(int count) throws InterruptedException {
		Thread.sleep(5000);
		LinkedHashMap<String, String> products = new LinkedHashMap<>();
		for (int i = 0; i < count ; i++) {
			products.put(productNames.get(i).getText(), productPrices.get(i).getText());
		}
		System.out.println(products);
		return products;
	}
}