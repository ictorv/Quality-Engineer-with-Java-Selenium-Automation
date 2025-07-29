package com.urban.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.urban.utillities.WaitUtils;

public class BasePage {

	public static WebDriver driver;
	public WebDriverWait wait;
	public static Actions action;
	public JavascriptExecutor js;
	public static WebElement popup;

	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		BasePage.action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public static void popupHandle() {
		try 
		{
//		WaitUtils.waitForVisibility(driver, //a[@class='close-reveal-modal hide-mobile'], 5);
	     popup=driver.findElement(By.xpath("//a[@class='close-reveal-modal hide-mobile']"));
		 popup.click();
		} catch (Exception ignored) {
			System.out.println("SignUp popup handled failed");
		}
	}
}
