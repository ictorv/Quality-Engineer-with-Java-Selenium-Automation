package com.selenium.objectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RhapsodyHomePage {
	public WebDriver driver;
	WebDriverWait wait;
	
	
	public RhapsodyHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
//		this.wait = new WebDriverWait(driver,Duration.ofSeconds(15));
	}
	
	@FindBy(xpath = "//*[@id='promoHeroCTAButton']")
	WebElement bookNow;
	
	public void clickBookNow() {
		System.out.println("=>Clicking BookNow");
		bookNow.click();
	}

}
