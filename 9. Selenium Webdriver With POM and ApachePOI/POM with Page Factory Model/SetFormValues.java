package com.selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class SetFormValues {
    
    private WebDriver driver;

//Do not change the constructor
    public SetFormValues(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="demo")
    WebElement demo;
    public void setName(String name) {
         // Click on the element to trigger the alert prompt
         // Switch to the alert context
         // Send the provided name to the alert input field
         // Accept the alert to confirm the name input
        demo.click();
        Alert alert=driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    @FindBy(id="amount")
    WebElement amount;
    public void setAmount(String amt) {
        //locate and fill the input box of the 'Amount' with value passed as parameter
        amount.sendKeys(amt);
        
    }

    @FindBy(id="year")
    WebElement year;
    public void setYear(String yrs) {
        //locate and fill the input box of the 'Year' with value passed as parameter
        year.sendKeys(yrs);
        
    }

    @FindBy(id="roi")
    WebElement roi;
    public void setROI(String interest) {
        //locate and fill the input box of the 'ROI' with value passed as parameter
        roi.sendKeys(interest);
        
    }

    @FindBy(name="button")
    WebElement btn;
    public void setCalculate() {
        //locate and submit the calcuate button
        btn.click();
        
    }

    public void setAlert() {
         //handle and accept the Alert
         Alert alert=driver.switchTo().alert();
         alert.accept();
        
    }

   
}