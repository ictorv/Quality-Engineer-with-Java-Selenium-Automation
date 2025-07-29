package com.selenium.objectRepositories;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public NewsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='title' and contains(text(),'Cognizant')]") 
	List<WebElement> listOfNews;	
	
	@FindBy(id = "b-scopeListItem-news")
	WebElement news;
	
	public void navigateToNews() {
		news.click();
		System.out.println("Clicked on News");
	}	
	public void switchToNewTab() {
		String currentTab = driver.getWindowHandle();
        wait.until(dummyDriver-> dummyDriver.getWindowHandles().size()>1);
        for(String switchTab:driver.getWindowHandles()){
            if(!currentTab.equals(switchTab)){
                driver.switchTo().window(switchTab);
                System.out.println("News opened in new tab ");
                break;
            }
        }
	}
	
	public int countNumberOfNews() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='algocore' and @id='algocore']")));
		System.out.println("Count of the News:"+listOfNews.size());
		return listOfNews.size();
		
	}
	
	

}
