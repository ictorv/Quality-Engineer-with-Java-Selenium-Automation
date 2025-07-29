package com.selenium.objectRepositories;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;
	String baseUrl="https://www.royalcaribbean.com/alaska-cruises?country=USD";
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
	
	@FindBy(xpath = "//*[@id='root']/div[3]/div/div[2]/div[1]")
	WebElement popUpClose;
	
	@FindBy(xpath = "//*[@id='rciSearchHeaderIcon']")
	WebElement searchBtn;
	
	
	public void waitAndHandlePopUp() {
		System.out.println("Waiting for Banner....");
		popUpClose.click();
		System.out.println("=>Banner Handled");
	}
	
	public void openSearchPage() {
		System.out.println("=>Clicking Search Icon");
		searchBtn.click();
		System.out.println("=>Opening Search page");
	}

}
