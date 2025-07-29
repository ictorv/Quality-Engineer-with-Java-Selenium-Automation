package com.test.objects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.Utilitylibraries.OrangeHRM.ScreenShot;

public class LoginPage {
	
	 WebDriver driver;
	    WebDriverWait wait;

	    // Using Page Factory to locate elements
	    @FindBy(name = "username")
	    private WebElement usernameField;

	    @FindBy(xpath = "//input[@type='password']")
	    private WebElement passwordField;

	    @FindBy(xpath = "//button[@type='submit']")
	    private WebElement loginButton;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);  // Initializing elements
	    }

	    public void login(String username, String password) {
	        driver.get("https://opensource-demo.orangehrmlive.com");
	        usernameField.sendKeys(username);
	        passwordField.sendKeys(password);

	        try {
	            ScreenShot.screenShotTC(driver, "Login Page");
	        } catch (IOException e) {
	            System.out.println("Screenshot error: " + e.getMessage());
	        }

	        loginButton.click();

	        // Verify login success
	      
	        
	    }
	    
	    public String verification(String expected_url) {
	    	String curr_Url = driver.getCurrentUrl();
	    	
	    	  
		        	if ((curr_Url.contains("dashboard"))&&(curr_Url.equals(expected_url))) {
			            return "Verification successful!";
			        } else {
			            return "Verification failed!";
			        }
	    }
}
		        	
		        
	    
	


	/*import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import Basics2.ScreenShot;

	import java.io.IOException;
	import java.time.Duration;
	 
	public class LoginPage {
	    WebDriver driver;
	    WebDriverWait wait;
	 
	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	 
	    public void login(String username, String password) {
	        driver.get("https://opensource-demo.orangehrmlive.com");
	        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
	        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
	        try {{
	        	ScreenShot.screenShotTC(driver, "Login Page");
	        }
	        catch(IOException e){
	        	System.out.println(e.getMessage());
	        }
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
	    }
	}*/
