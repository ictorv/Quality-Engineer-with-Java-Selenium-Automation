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

public class LandingPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName="a") 
	List<WebElement> alltag;
	
	@FindBy(id = "sb_form_q")
	WebElement search;	
	
	@FindBy(xpath="//li[@role='option']")
	List<WebElement> eleList;
	
	@FindBy(className = "sa_as")
	WebElement searchbox;
	
	public int getNumberOfTotalLinks() {
		System.out.println("Number of all links on Bing: " + alltag.size());
		return alltag.size();
	}
	
	public void printAllLinks() {
		try {
       	 for (int i = 0; i < alltag.size(); i++) {
       	
				//WebElement element = alltag.get(i);
				System.out.println(alltag.get(i).getDomAttribute("href"));
				System.out.println(alltag.get(i).getText());
       	 }
			
       } catch (StaleElementReferenceException e) {
           System.out.println("Element became stale, re-fetching...");
          
       }
	}
	
	public void typeCognizant() {
		search.sendKeys("Cognizant");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("sa_ul")));
	}
	public int getOptionsCount() {
		return eleList.size();
	}
	
	public void printSearchSuggestions() {
		for(int i=0;i<eleList.size();i++) {
        	System.out.println(eleList.get(i).getDomAttribute("query"));
        }
	}
	
	public WebElement getOptionsFrame() {
		return searchbox;
	}
	

}
