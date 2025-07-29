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

public class ImagesPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public ImagesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "b-scopeListItem-images")
	WebElement images;


	
	public void navigateToImagesSection() {
		images.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='dg_b isvctrl']")));
	}
	
	

}
