package com.selenium.objectRepositories;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	public WebDriver driver;
	String query="Rhapsody of the Seas";
	WebDriverWait wait;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	}
	
	@FindBy(xpath = "//*[@id='rciSearchInput']")
	WebElement queryField;
	
	@FindBy(xpath = "//*[@id='rciSearchInputIcon']")
	WebElement searchIcon;
	
	@FindBy(linkText = "Rhapsody of the Seas | Cruise Ships | Royal Caribbean Cruises")
	WebElement link;
	
	public void enterQuery() {
		System.out.println("=>Entering Search Query");
		queryField.sendKeys(query);
		System.out.println("=> Clicking Search icon");
		searchIcon.click();
	}
	
	public void clickLink() {
		System.out.println("=>Wait for the Page to load");
		wait.until(ExpectedConditions.elementToBeClickable(link));
		System.out.println("=>Page load complete");
		System.out.println("=>Click the link: Rhapsody of the Seas | Cruise Ships | Royal Caribbean Cruises");
		link.click();
	}

}
