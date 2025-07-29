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

public class VideosPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public VideosPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='mc_vtvc_title b_promtxt' and contains(@title,'Cognizant') or contains(@title,'cognizant')]") 
	List<WebElement> listOfVideo;	

	
	@FindBy(id = "b-scopeListItem-video")
	WebElement videos;
	
	public void navigateToVideoSection() {
		videos.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='b_slidebar b_sldrp']")));
	}
	
	public int countNumberOfVideos() {
		System.out.println("Count of the Videos:"+listOfVideo.size());
		return listOfVideo.size();
		
	}
	
	

}
