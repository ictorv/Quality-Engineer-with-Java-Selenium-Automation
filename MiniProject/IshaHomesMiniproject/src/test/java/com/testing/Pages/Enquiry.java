package com.testing.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Enquiry {

	WebDriver driver;

	public Enquiry(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// POM
	@FindBy(xpath = "//div[@class='mobile-content-isha']/div[2]/a")
	WebElement enquiry;

	@FindBy(xpath = "//*[@id=\"elementor-popup-modal-32248\"]/div/div[2]//section[2]/div//section/div//div/ul/li[4]")
	WebElement otherEnquiry;

	@FindBy(xpath = "//div[@class='elementor-widget-container']//*[contains(text(),'@')]")
	WebElement email;

	// Click on Enquire
	public void clickEnquiry() {
		enquiry.click();
	}

	// Click on other Enquire to fetch email
	public void clikcOtherEnquiry() {
		otherEnquiry.click();
	}

	// Email is fetched and returned
	public String getEmail() {
		String emailStr = email.getText();
		// System.out.println(emailStr+"->email");
		return emailStr;
	}
}
