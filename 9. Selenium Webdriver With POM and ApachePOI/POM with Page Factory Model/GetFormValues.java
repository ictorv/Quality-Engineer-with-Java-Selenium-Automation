package com.selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GetFormValues {
    
	public WebDriver driver;
	
//Do not change the constructor
	
	public GetFormValues(WebDriver driver){
		this.driver= driver;
	}
	
	 public String getEmiAmount() {
	        // Find the element displaying the EMI amount message
	        // // Return the text content of the EMI amount message element
	        return driver.findElement(By.id("msg")).getText();
	    }
}
