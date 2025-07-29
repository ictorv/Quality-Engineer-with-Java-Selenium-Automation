package com.selenium.objectRepositories;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CognizantSearchPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CognizantSearchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ol[@id='b_results']/li//h2/child::a") 
	List<WebElement> listOfLinks;
	
	@FindBy(id = "sb_form_q")
	WebElement search;
	
	public void clickSearch() {
		search.sendKeys(Keys.ENTER);
	}
	
	public int getNumberOfTotalLinks() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ol[@id='b_results']/li//h2/child::a")));
		System.out.println("Count in ALL:"+listOfLinks.size());
		return listOfLinks.size();
	}
	
	

}
