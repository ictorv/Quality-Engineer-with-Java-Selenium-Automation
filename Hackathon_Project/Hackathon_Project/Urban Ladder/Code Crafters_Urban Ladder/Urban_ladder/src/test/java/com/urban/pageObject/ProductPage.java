package com.urban.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "add-to-cart-button")
	WebElement addToCartButton;
	@FindBy(xpath = "//div[@class = 'product-details']//div[@class = 'product-title']//a")
	WebElement itemNameInCart;
	@FindBy(xpath = "//div[@class='order-amt upfront-payment']")
	WebElement itemPrice;

	public void addToCart() {
		addToCartButton.click();
	}

	public String getItemNameInCart() {
		return itemNameInCart.getText();
	}

	public String getItemPrice() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		return itemPrice.getText();
	}

}
