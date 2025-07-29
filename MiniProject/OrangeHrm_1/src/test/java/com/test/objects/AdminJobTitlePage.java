package com.test.objects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.Utilitylibraries.OrangeHRM.ScreenShot;

public class AdminJobTitlePage {
	 WebDriver driver;
	 WebDriverWait wait;

	    @FindBy(xpath = "//span[text()='Admin']")
	    private WebElement adminTab;

	    @FindBy(xpath = "//nav[@class='oxd-topbar-body-nav']/ul/li[2]")
	    private WebElement jobMenu;

	    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a")
	    private WebElement jobTitleLink;

	    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']//div[2]")
	    private List<WebElement> jobTitles;

	    @FindBy(xpath = "//button[text()=' Add ']")
	    private WebElement addJobButton;

	    @FindBy(xpath = "//label[text()='Job Title']/following::input[1]")
	    private WebElement jobTitleInput;

	    @FindBy(xpath = "//button[@type='submit']")
	    private WebElement saveButton;

	    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	    private WebElement userDropdown;

	    @FindBy(xpath = "//a[text()='Logout']")
	    private WebElement logoutButton;

	    public AdminJobTitlePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);  // Initialize Page Factory
	    }

	    public void goToJobTitles() {
	        wait.until(ExpectedConditions.urlContains("dashboard"));
	        takeScreenshot("Dashboard");

	        adminTab.click();
	        jobMenu.click();
	        takeScreenshot("Job");

	        System.out.println(jobTitleLink.getText());
	        jobTitleLink.click();
	    }

	    public boolean isJobTitlePresent(String jobName) {
	    	boolean result = false; 
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                By.xpath("//div[@class='oxd-table-body']//div[@role='row']//div[2]")));
	        for (WebElement title : jobTitles) {
	        	System.out.println("Job Titles name: "+title.getText());
	            if (title.getText().equalsIgnoreCase(jobName)) {
	                result = true;
	                continue;
	                
	            }
	        }
	        return result;
	    }

	    public void addJobTitle(String jobName) {
	        addJobButton.click();
	        takeScreenshot("Adding Job title");

	        wait.until(ExpectedConditions.visibilityOf(jobTitleInput)).sendKeys(jobName);
	        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
	    }

	    public void logout() {
	        userDropdown.click();
	        wait.until(ExpectedConditions.visibilityOf(logoutButton)).click();
	    }

	    private void takeScreenshot(String pageName) {
	        try {
	            ScreenShot.screenShotTC(driver, pageName);
	        } catch (IOException e) {
	            System.out.println("Screenshot error: " + e.getMessage());
	        }
	    }
	}


	/*import org.openqa.selenium.*;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import Basics2.ScreenShot;

	import java.io.IOException;
	import java.time.Duration;
	import java.util.List;
	 
	public class AdminJobTitlePage {
	    WebDriver driver;
	    WebDriverWait wait;
	 
	    public AdminJobTitlePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }
	 
	    public void goToJobTitles() {
	        wait.until(ExpectedConditions.urlContains("dashboard"));
	        
	        try {
	        	ScreenShot.screenShotTC(driver, "Dashboard");
	        }
	        catch(IOException e){
	        	System.out.println(e.getMessage());
	        }
	        driver.findElement(By.xpath("//span[text()='Admin']")).click();
	        WebElement job = driver.findElement(By.xpath("//nav[@class='oxd-topbar-body-nav']/ul/li[2]"));
	        job.click();
	        try {
	        	ScreenShot.screenShotTC(driver, "Job");
	        }
	        catch(IOException e){
	        	System.out.println(e.getMessage());
	        }
	        WebElement jobTitle = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a"));
	        System.out.println(jobTitle.getText());
	        jobTitle.click();
	    }
	 
	    public boolean isJobTitlePresent(String jobName) {
	        List<WebElement> jobTitles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                By.xpath("//div[@class='oxd-table-body']//div[@role='row']//div[2]")));
	        for (WebElement title : jobTitles) {
	            if (title.getText().equalsIgnoreCase(jobName)) {
	                return true;
	            }
	        }
	        return false;
	    }
	 
	    public void addJobTitle(String jobName) {
	        driver.findElement(By.xpath("//button[text()=' Add ']")).click();
	        try {
	        	ScreenShot.screenShotTC(driver, "Adding Job title");
	        }
	        catch(IOException e){
	        	System.out.println(e.getMessage());
	        }
	        WebElement jobTitleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//label[text()='Job Title']/following::input[1]")));
	        jobTitleInput.sendKeys(jobName);
	 
	        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[@type='submit']")));
	saveButton.click();
	    }
	 
	    public void logout() {
	        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//a[text()='Logout']"))).click();
	    }
	}*/
