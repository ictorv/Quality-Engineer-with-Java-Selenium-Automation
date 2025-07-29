package com.testing.Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {

	WebDriver driver;

	// POM
	@FindBy(xpath = "(//*[@class='elementor-widget-wrap elementor-element-populated'])[3]")
	WebElement isResultVisble;
	@FindBy(xpath = "//a[@href='#boosted-tab-0']")
	WebElement allButton;

	public ResultPage(WebDriver driver) {
		this.driver = driver;
		// Implementing the PageFactory
		PageFactory.initElements(driver, this);
	}

	// Scroll down to reach the element "Enquire Now"
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", allButton);
	}

	// waiting until the element "all" is clicked to get list of projects
	public void waitTime() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(2));
		wt.until(ExpectedConditions.elementToBeClickable(isResultVisble));
	}

}
